package com.bcai.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BonusByDateDao;
import com.bcai.dao.BuyDebrisRecordDao;
import com.bcai.dao.DebrisRecordDao;
import com.bcai.dao.EpSellDao;
import com.bcai.dao.HtcAmountDao;
import com.bcai.dao.MemberBonusListDao;
import com.bcai.dao.MemberDao;
import com.bcai.dao.MemberStokenDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.dao.MillRecordDao;
import com.bcai.dao.StokenSellDao;
import com.bcai.domain.BonusByDate;
import com.bcai.domain.BuyDebrisRecord;
import com.bcai.domain.DebrisRecord;
import com.bcai.domain.HtcAmount;
import com.bcai.domain.Member;
import com.bcai.domain.MemberStoken;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.domain.MillRecord;
import com.bcai.domain.StokenSell;
import com.bcai.service.MemberStokenService;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.MarketInfo;
import com.bcai.web.vo.Message;
import com.symbio.commons.AppLog;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.DateUtils;
import com.symbio.utils.WebUtils;

@Service("memberStokenServiceImpl")
public class MemberStokenServiceImpl extends
		AbstractServiceImpl<MemberStoken, Long> implements MemberStokenService {

	@Autowired
	private MemberStokenDao memberStokenDao;

	@Autowired
	private MillRecordDao millRecordDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private HtcAmountDao htcAmountDao;

	@Autowired
	private MemberWalletDao memberWalletDao;

	@Autowired
	private DebrisRecordDao debrisRecordDao;

	@Autowired
	private BuyDebrisRecordDao buyDebrisRecordDao;

	@Autowired
	private StokenSellDao stokenSellDao;
	
	@Autowired
	private MemberWalletListDao memberWalletListDao;
	
	@Autowired
	private MemberBonusListDao memberBonusListDao;
	
	@Autowired
	private BonusByDateDao bonusByDateDao;
	
	@Autowired
	private EpSellDao epSellDao;

	@Override
	public GenericDaoImpl<MemberStoken, Long> getDao() {
		return memberStokenDao;
	}
	
	private static Logger logger = Logger.getLogger(MillRecordServiceImpl.class);  


	@Override
	public Message resolveMills(Integer millNum, String mbName, Long mid) {
		String msSql = "from MemberStoken where mbName= '" + mbName + "'";
		MemberStoken memberStoken = memberStokenDao.findByFieldSyn(msSql);
		MillRecord millRecord = null;
		int klm = 0;
		if (mid == null) {
			int noRes = millRecordDao.countMill(mbName);
			if (noRes < millNum) {
				return new Message(0, WebUtils.getMesString("bm.service.a6"));
			}
			// String rmSQL =
			// "from MillRecord where modelNum = ? and mbName = ? order by synthesisDate desc";
			// List<MillRecord> millRecords = millRecordDao.findList(rmSQL,
			// new Object[] {"yes",mbName});
			List<MillRecord> millRecords = millRecordDao.decomMills(mbName,
					millNum);
			if (millRecords.size() != millNum) {
				return new Message(0, WebUtils.getMesString("bm.service.a7"));
			}
			for (MillRecord millr : millRecords) {
				millRecordDao.delete(millr.getId());
				millRecordDao.flush();
				klm++;
			}

		} else {
			millRecord = millRecordDao.get(mid);
			millRecordDao.delete(millRecord.getId());
			millRecordDao.flush();
			klm++;
		}

		DebrisRecord deBrisRecord = new DebrisRecord();
		deBrisRecord.setAmoney(klm * 10);
		deBrisRecord.setType(2);
		deBrisRecord.setMbName(memberStoken.getMbName());
		deBrisRecord.setBalance(memberStoken.getTraDebrisRemain() + klm * 10);
		deBrisRecord.setOccDate(new Date());
		deBrisRecord.setDescription("分解矿机");
		debrisRecordDao.save(deBrisRecord);
		debrisRecordDao.flush();

		memberStoken.setMill(memberStoken.getMill() - millNum);
		memberStoken.setTraDebrisRemain(memberStoken.getTraDebrisRemain()
				+ millNum * 10);
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();

		return new Message(1, WebUtils.getMesString("bm.service.a8"));
	}

	@Override
	public Message synthetiseMills(Integer millNum, String mbName) {
		String msSql = "from MemberStoken where mbName= '" + mbName + "'";
		MemberStoken memberStoken = memberStokenDao.findByFieldSyn(msSql);

		Calendar c = new GregorianCalendar();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if (hour >= 1 && hour <= 8) {
			return new Message(0, WebUtils.getMesString("bm.service.a9"));
		}

		int mill = (int) (memberStoken.getTokenRemain() / 10);
		List<BuyDebrisRecord> buyDebrisRecords = buyDebrisRecordDao.findBuyDeb(
				10 * mill, mbName);
		double cbuyPrice = 0.0;
		for (BuyDebrisRecord buyDebrisRecord : buyDebrisRecords) {
			cbuyPrice += buyDebrisRecord.getBuyPrice();
		}
		double millWorth = millRecordDao.countMillWorth(memberStoken
				.getMbName());
		if (millWorth + cbuyPrice > 100000) {
			return new Message(0, WebUtils.getMesString("bm.service.a10"));
		}
		if (memberStoken.getTokenRemain() < 10) {
			return new Message(0, WebUtils.getMesString("bm.service.a11"));
		}

		memberStoken.setMill(memberStoken.getMill() + mill);
		memberStoken.setTokenRemain(memberStoken.getTokenRemain() - mill * 10);
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();
		Member member = memberDao.findByField("mbName", mbName);
		HtcAmount htcAmount = htcAmountDao.getSyn(BcaiUtils.BTCAMOUNT_ID);
		for (int i = 0; i < mill; i++) {
			MillRecord millRecord = new MillRecord();
			if (member.getUsClass() < memberStoken.getMill() - mill + i + 1) {
				millRecord.setIsUse("yes");
			} else {
				millRecord.setIsUse("no");
			}
			buyDebrisRecords = null;
			buyDebrisRecords = buyDebrisRecordDao.findBuyDeb(10, mbName);
			cbuyPrice = 0.0;
			for (BuyDebrisRecord buyDebrisRecord : buyDebrisRecords) {
				cbuyPrice += buyDebrisRecord.getBuyPrice();
				buyDebrisRecordDao.delete(buyDebrisRecord);
				buyDebrisRecordDao.flush();
			}
			int size = buyDebrisRecords.size();
			if (size < 10) {
				cbuyPrice += htcAmount.getPrice() * (10 - size);
			}
			millRecord.setMbName(mbName);
			millRecord.setSynthesisDate(new Date());
			millRecord.setModelNum("no");
			millRecord.setBuyPrice(cbuyPrice);

			millRecord.setIsCount("no");
			millRecordDao.save(millRecord);
			millRecordDao.flush();
		}

		int mMillC = memberStoken.getMill();

		if (member.getUsClass() < mMillC) {
			member.setUsClass(mMillC);
			memberDao.update(member);
			memberDao.flush();
		}

		DebrisRecord deBrisRecord = new DebrisRecord();
		deBrisRecord.setAmoney(mill * 10);
		deBrisRecord.setType(1);
		deBrisRecord.setMbName(memberStoken.getMbName());
		deBrisRecord.setBalance(memberStoken.getTraDebrisRemain()
				+ memberStoken.getTokenRemain());
		deBrisRecord.setOccDate(new Date());
		deBrisRecord.setDescription("合成矿机：" + mill + "台");
		debrisRecordDao.save(deBrisRecord);
		debrisRecordDao.flush();

		return new Message(1, WebUtils.getMesString("bm.service.a12") + mill + WebUtils.getMesString("bm.service.a13"));
	}

	@Override
	public MarketInfo countMills() {
		MarketInfo marketInfo = new MarketInfo();
		marketInfo.setLatelyBuyPrice(memberStokenDao.countMill());
		marketInfo.setLatelySellPrice(memberStokenDao.countDebris());
		marketInfo.setNewestPrice(memberWalletDao.countMemberBmBalance());
		return marketInfo;
	}

	@Override
	public boolean split(double count, String date) {
		List<MemberStoken> memberStokens = memberStokenDao.findMemberStoken();
		if (memberStokens.size() == 0) {
			return false;
		}
		MemberStoken memberStoken = memberStokens.get(0);
		int mill = memberStoken.getMill();
		if (mill == 0) {
			memberStoken.setTokenRemark("yes");
			memberStokenDao.update(memberStoken);
			return true;
		}
		
//		List<EpSell> epSells = epSellDao.findEpSells(memberStoken.getMbName());
//		if(epSells.size()>0){
//			memberStoken.setTokenRemark("yes");
//			memberStokenDao.update(memberStoken);
//			memberStokenDao.flush();
//			return true;
//		}
		
		double buyPrice = millRecordDao.countMillWorthSpit(
				memberStoken.getMbName(), date);
		if (buyPrice == 0) {
			memberStoken.setTokenRemark("yes");
			memberStokenDao.update(memberStoken);
			memberStokenDao.flush();
			return true;
		}
		double buyCPrice = mill * 500;
		double ordebris = mill * 10 * (buyPrice / buyCPrice);
		double debris = mill * 10 * count * (buyPrice / buyCPrice);
		double sumDebris = (mill * 10) + memberStoken.getTokenRemain()
				+ memberStoken.getTraDebrisRemain();
		double tjDebris = (debris - ordebris) + sumDebris;
		memberStoken.setTraDebrisRemain(memberStoken.getTraDebrisRemain()
				+ (debris - ordebris));

		memberStoken.setTokenRemark("yes");
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();

		DebrisRecord deBrisRecord = new DebrisRecord();
		deBrisRecord.setAmoney(debris - ordebris);
		deBrisRecord.setType(3);
		deBrisRecord.setMbName(memberStoken.getMbName());
		deBrisRecord.setBalance(memberStoken.getTraDebrisRemain()
				+ memberStoken.getTokenRemain());
		deBrisRecord.setOccDate(new Date());
		deBrisRecord.setDescription("社区第十五次配送：" + count + "倍");
		debrisRecordDao.save(deBrisRecord);
		debrisRecordDao.flush();

		return true;
	}

	@Override
	public boolean sellStoen(String startDate, String endDate, double sellPrice) {
		// tokenSpare
		List<MemberStoken> memberStokens = memberStokenDao
				.findMemberStokenBySparce();
		if (memberStokens.size() == 0) {
			return false;
		}
		MemberStoken memberStoken = memberStokens.get(0);
		String rmSQL = "from DebrisRecord where mbName = ? and description = ?";
		List<DebrisRecord> debrisRecords = debrisRecordDao.findList(rmSQL,
				new Object[] { memberStoken.getMbName(), "社区第八次配送：" + 1.4 + "倍" });
		if (debrisRecords == null || debrisRecords.size() == 0) {
			memberStoken.setTokenSpare("yes");
			memberStokenDao.update(memberStoken);
			memberStokenDao.flush();
			return true;
		}
		DebrisRecord dbris = debrisRecords.get(0);
		double addDbris = dbris.getAmoney();

		double thirDirs = addDbris - (addDbris * 0.2*2);
		if (memberStoken.getTraDebrisRemain() > thirDirs + 1) {
			if (memberStoken.getTraDebrisRemain() < 1) {
				memberStoken.setTokenSpare("yes");
				memberStokenDao.update(memberStoken);
				memberStokenDao.flush();
				return true;
			}
			sellDebris(sellPrice, memberStoken.getTraDebrisRemain(),
					memberStoken.getMbName());
		} else {
			memberStoken.setTokenSpare("yes");
			memberStokenDao.update(memberStoken);
			memberStokenDao.flush();
		}

		return true;
	}

	private Message sellDebris(double price, double count, String mbName) {

		String msSql = "from MemberStoken where mbName= '" + mbName + "'";

		MemberStoken memberStoken = memberStokenDao.findByFieldSyn(msSql);

		if (memberStoken.getTokenRemain() + memberStoken.getTraDebrisRemain() < count) {
			return new Message(0, WebUtils.getMesString("bm.service.a14"));
		}
		StokenSell stokenSell = new StokenSell();
		stokenSell.setMbName(mbName);
		stokenSell.setState(0);
		stokenSell.setSelledCount(0.00);
		stokenSell.setTokenSellCount(count);
		DecimalFormat df1 = new DecimalFormat("###.000");
		stokenSell.setTokenSellPrice(Double.valueOf(df1.format(price)));
		stokenSell.setTokenSellTime(new Date());
		stokenSell.setTokenSellRemark("system");
		stokenSellDao.save(stokenSell);
		stokenSellDao.flush();
		double tradDebri = memberStoken.getTraDebrisRemain();
		if (count <= tradDebri) {
			memberStoken.setTraDebrisRemain(memberStoken.getTraDebrisRemain()
					- count);
		} else {
			memberStoken.setTraDebrisRemain(0);
			memberStoken.setTokenRemain(memberStoken.getTokenRemain()
					- (count - tradDebri));
		}
		memberStoken.setTokenSpare("yes");
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();

		DebrisRecord deBrisRecord = new DebrisRecord();
		deBrisRecord.setAmoney(count);
		deBrisRecord.setType(4);
		deBrisRecord.setMbName(memberStoken.getMbName());
		deBrisRecord.setBalance(memberStoken.getTraDebrisRemain()
				+ memberStoken.getTokenRemain());
		deBrisRecord.setOccDate(new Date());
		deBrisRecord.setDescription("社区强制卖出碎片：" + count + "个");
		debrisRecordDao.save(deBrisRecord);

		return new Message(1, WebUtils.getMesString("bm.service.a15"));
	}

	@Override
	public double countMillGross(String mbName) {
		return millRecordDao.countMillWorth(mbName);
	}

	@Override
	public boolean synMill() {
		List<MemberStoken> memberStokens = memberStokenDao
				.findMemberStokenByTokenRemin();
		if (memberStokens.size() == 0 || memberStokens.get(0) == null) {
			return false;
		}
		
		MemberStoken memberStoken = memberStokens.get(0);
		
		synMill(memberStoken.getMbName());
		return true;
	}

	public void synMill(String mbName) {
		String msSql = "from MemberStoken where mbName= '" + mbName + "'";

		MemberStoken memberStoken = memberStokenDao.findByFieldSyn(msSql);

		int mill = (int) (memberStoken.getTokenRemain() / 10);
		AppLog.info("synMill："+memberStoken.getMbName()+" : "+mill, logger);
		List<BuyDebrisRecord> buyDebrisRecords = buyDebrisRecordDao.findBuyDeb(
				10 * mill, mbName);
		double cbuyPrice = 0.0;
		for (BuyDebrisRecord buyDebrisRecord : buyDebrisRecords) {
			cbuyPrice += buyDebrisRecord.getBuyPrice();
		}
		double millWorth = millRecordDao.countMillWorth(memberStoken
				.getMbName());
		if (millWorth + cbuyPrice > 100000) {
			return;
		}
		if (memberStoken.getTokenRemain() < 10) {
			return;
		}

		memberStoken.setMill(memberStoken.getMill() + mill);
		memberStoken.setTokenRemain(memberStoken.getTokenRemain() - mill * 10);
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();
		String memberSql = "from Member where mbName= '" + mbName + "'";
		Member member = memberDao.findByFieldSyn(memberSql);
		HtcAmount htcAmount = htcAmountDao.get(BcaiUtils.BTCAMOUNT_ID);
		for (int i = 0; i < mill; i++) {
			MillRecord millRecord = new MillRecord();
			if (member.getUsClass() < memberStoken.getMill() - mill + i + 1) {
				millRecord.setIsUse("yes");
			} else {
				millRecord.setIsUse("no");
			}
			buyDebrisRecords = null;
			buyDebrisRecords = buyDebrisRecordDao.findBuyDeb(10, mbName);
			cbuyPrice = 0.0;
			for (BuyDebrisRecord buyDebrisRecord : buyDebrisRecords) {
				cbuyPrice += buyDebrisRecord.getBuyPrice();
				buyDebrisRecordDao.delete(buyDebrisRecord);
				buyDebrisRecordDao.flush();
			}
			int size = buyDebrisRecords.size();
			if (size < 10) {
				cbuyPrice += htcAmount.getPrice() * (10 - size);
			}
			millRecord.setMbName(mbName);
			millRecord.setSynthesisDate(new Date());
			millRecord.setModelNum("no");
			millRecord.setBuyPrice(cbuyPrice);

			millRecord.setIsCount("no");
			millRecordDao.save(millRecord);
			millRecordDao.flush();
		}

		int mMillC = memberStoken.getMill();

		if (member.getUsClass() < mMillC) {
			member.setUsClass(mMillC);
			memberDao.update(member);
			memberDao.flush();
		}

		DebrisRecord deBrisRecord = new DebrisRecord();
		deBrisRecord.setAmoney(mill * 10);
		deBrisRecord.setType(1);
		deBrisRecord.setMbName(memberStoken.getMbName());
		deBrisRecord.setBalance(memberStoken.getTraDebrisRemain()
				+ memberStoken.getTokenRemain());
		deBrisRecord.setOccDate(new Date());
		deBrisRecord.setDescription("合成矿机：" + mill + "台");
		debrisRecordDao.save(deBrisRecord);
		debrisRecordDao.flush();

	}

	@Override
	public boolean newMill() {
		
		List<MemberStoken> memberStokens = memberStokenDao.findMemberStokenByMill();
		if (memberStokens.size() == 0) {
			return false;
		}
		MemberStoken memberStoken = memberStokens.get(0);
		double millWorth = millRecordDao.countNoDecMillWorth(memberStoken.getMbName());
		if(millWorth <= 1){
			memberStoken.setIsMin(1);
			memberStokenDao.update(memberStoken);  
			return true;
		}
		double money = millWorth * 0.01;
		
		MemberWallet pmemberWallet = memberWalletDao.findMemberWalletById(memberStoken.getMbName());
		pmemberWallet.setWallet(pmemberWallet.getWallet()+money*0.7);
		pmemberWallet.setRewallet(pmemberWallet.getRewallet()+money*0.3);
		MemberWalletList pmemberWalletList = new MemberWalletList();
		MemberWalletList reMeWalletList = new MemberWalletList();
		
		pmemberWalletList.setAmoney(money*0.7);
		pmemberWalletList.setBalance(pmemberWallet.getWallet());
		pmemberWalletList.setCreateTime(new Date());
		pmemberWalletList.setCurrencyType(0);
		pmemberWalletList.setDescription("矿机产生");
		pmemberWalletList.setMbName(memberStoken.getMbName());
		pmemberWalletList.setType(9);
		
		reMeWalletList.setAmoney(money*0.3);
		reMeWalletList.setBalance(pmemberWallet.getRewallet());
		reMeWalletList.setCreateTime(new Date());
		reMeWalletList.setCurrencyType(1);
		reMeWalletList.setDescription("矿机产生");
		reMeWalletList.setMbName(memberStoken.getMbName());
		reMeWalletList.setType(9);
		
		memberWalletListDao.save(pmemberWalletList);
		memberWalletListDao.save(reMeWalletList);
		memberWalletListDao.flush();
		memberWalletDao.update(pmemberWallet);
		memberWalletDao.flush();
		
		
//		memberWalletListService.modifyMemberWalletList(pmemberWalletList, -1,
//				null);

//		MemberBonusList pmemberBonusList = new MemberBonusList();
//		pmemberBonusList.setMbName(memberStoken.getMbName());
//		pmemberBonusList.setBonusBringMoney(money*0.7);
//		pmemberBonusList.setBonusBringTime(new Date());
//		pmemberBonusList.setBonusRemark("矿机产生");
//		pmemberBonusList.setBonusSort(4);
//		pmemberBonusList.setEpRemain(0);
//		memberBonusListDao.save(pmemberBonusList);
//		memberBonusListDao.flush();

		String sDate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
		String byDSQL = "from BonusByDate where mbName = ? and stbringDate = ?";
		BonusByDate bonusByDate = bonusByDateDao.getBonusByDate(
				memberStoken.getMbName(), sDate);
		if (bonusByDate == null) {
			bonusByDate = new BonusByDate();
			bonusByDate.setBringDate(new Date());
			bonusByDate.setMbName(memberStoken.getMbName());
			bonusByDate.setStbringDate(sDate);
			bonusByDate.setCountBonus(0);
			bonusByDate.setFuliBonus(0);
			bonusByDate.setLiderBonus(0);
			bonusByDate.setOrgBonus(0);
			bonusByDate.setSaleBonus(0);
			bonusByDateDao.save(bonusByDate);
			bonusByDateDao.flush();
			bonusByDate = bonusByDateDao.getBonusByDate(
					memberStoken.getMbName(), sDate);
		}
		
		bonusByDate.setFuliBonus(bonusByDate.getFuliBonus() + money*0.7);
		bonusByDate.setCountBonus(bonusByDate.getCountBonus() + money*0.7);
		bonusByDateDao.update(bonusByDate);
		bonusByDateDao.flush();
		memberStoken.setIsMin(1);
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();
		return true;
	}

	@Override
	public boolean revoDebris() {
		// TODO Auto-generated method stub
		List<MemberStoken> memberStokens = memberStokenDao.revoDebris();
		if(memberStokens == null || memberStokens.get(0) == null){
			return false;
		}
		MemberStoken memberStoken  = memberStokens.get(0);
		memberStoken.setTokenRemarkab("asd");
		MemberWallet memberWallet = memberWalletDao.findByFieldSyn("from MemberWallet where mbName = '"+memberStoken.getMbName()+"'");
		double ca = memberStoken.getTokenRemain()*24+memberStoken.getTraDebrisRemain()*24;
		memberWallet.setSapphire(memberWallet.getSapphire()+ca);
		memberStoken.setTokenRemain(0);
		memberStoken.setTraDebrisRemain(0);
		memberStokenDao.update(memberStoken);
		memberWalletDao.update(memberWallet);
		return true;
	}

	@Override
	public boolean splitfo() {
		// TODO Auto-generated method stub
		List<MillRecord> millRecords = millRecordDao.splitfragment();
		if(millRecords == null || millRecords.get(0) == null){
			return false;
		}
		MillRecord millRecord = millRecords.get(0);
		MemberStoken memberStoken = memberStokenDao.findByFieldSyn("from MemberStoken where mbName = '"+millRecord.getMbName()+"'");
		memberStoken.setMill(memberStoken.getMill()-1);
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();
		MemberWallet memberWallet = memberWalletDao.findByFieldSyn("from MemberWallet where mbName = '"+memberStoken.getMbName()+"'");
		memberWallet.setSapphire(memberWallet.getSapphire()+10*24);
		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();
		millRecordDao.delete(millRecord.getId());
		millRecordDao.flush();
		
		return true;
	}

}
