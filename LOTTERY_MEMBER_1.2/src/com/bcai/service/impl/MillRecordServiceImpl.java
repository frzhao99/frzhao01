package com.bcai.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BonusByDateDao;
import com.bcai.dao.HtcAmountDao;
import com.bcai.dao.MemberBonusListDao;
import com.bcai.dao.MemberDao;
import com.bcai.dao.MemberStokenDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.dao.MillRecordDao;
import com.bcai.domain.BonusByDate;
import com.bcai.domain.HtcAmount;
import com.bcai.domain.Member;
import com.bcai.domain.MemberBonusList;
import com.bcai.domain.MemberStoken;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.domain.MillRecord;
import com.bcai.service.MemberWalletListService;
import com.bcai.service.MillRecordService;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.Message;
import com.symbio.commons.AppLog;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.DateUtils;
import com.symbio.utils.WebUtils;

@Service("millRecordServiceImpl")
public class MillRecordServiceImpl extends
		AbstractServiceImpl<MillRecord, Long> implements MillRecordService {

	@Autowired
	private MillRecordDao millRecordDao;

	

	@Autowired
	private MemberWalletDao memberWalletDao;

	@Autowired
	private MemberWalletListDao memberWalletListDao;
	@Autowired
	private MemberBonusListDao memberBonusListDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private HtcAmountDao htcAmountdao;

	@Autowired
	private BonusByDateDao bonusByDateDao;

	@Autowired
	private MemberStokenDao memberStokenDao;

	@Autowired
	private MemberWalletListService memberWalletListService;
	
	private static Logger logger = Logger.getLogger(MillRecordServiceImpl.class);  

	@Override
	public GenericDaoImpl<MillRecord, Long> getDao() {
		return millRecordDao;
	}

	@Override
	public int countMill(String mbName) {

		String rmSQL = "from MillRecord where modelNum = ? and mbName = ?";
		List<MillRecord> millRecords = millRecordDao.findList(rmSQL,
				new Object[] { "yes", mbName });

		return millRecords.size();
	}

	@Override
	public boolean sellBonus() {

		String rmSQL = "from MillRecord where isUse = ?";
		List<MillRecord> millRecords = millRecordDao.findList(rmSQL,
				new Object[] { "yes" });
		
		if (millRecords.size() == 0) {
			return false;
		}
		MillRecord millRecord = millRecords.get(0);

		// 推荐奖金
		Member member = memberDao.findByField("mbName", millRecord.getMbName());

		Member commandMember = memberDao.findByField("mbName",
				member.getRecommendMbName());
		if (commandMember.getRecommendMbName().equals("BMsystem")) {
			millRecord.setIsUse("no");
			millRecordDao.update(millRecord);
			millRecordDao.flush();
			return true;
		}

		double money = millRecord.getBuyPrice() * BcaiUtils.sellBonus;
		MemberWallet memberWallet = memberWalletDao.findMemberWalletById(commandMember.getMbName());
		memberWallet.setWallet(memberWallet.getWallet()+money);
		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setAmoney(money);
		memberWalletList.setBalance(memberWallet.getWallet());
		memberWalletList.setCreateTime(new Date());
		memberWalletList.setCurrencyType(0);
		memberWalletList.setDescription("分享奖金，因：" + member.getMbName());
		memberWalletList.setMbName(commandMember.getMbName());
		memberWalletList.setType(0);
		memberWalletListDao.save(memberWalletList);
		memberWalletListDao.flush();
		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();
		//memberWalletListService.modifyMemberWalletList(memberWalletList, -1,
//				null);

		MemberBonusList memberBonusList = new MemberBonusList();
		memberBonusList.setMbName(commandMember.getMbName());
		memberBonusList.setBonusBringMoney(money);
		memberBonusList.setBonusBringTime(new Date());
		memberBonusList.setBonusRemark("因会员：" + member.getMbName() + "产生");
		memberBonusList.setBonusSort(1);
		memberBonusList.setEpRemain(0);
		memberBonusListDao.save(memberBonusList);
		memberBonusListDao.flush();

		String sDate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
		String byDSQL = "from BonusByDate where mbName = ? and stbringDate = ?";
		BonusByDate bonusByDate = bonusByDateDao.getBonusByDate(
				commandMember.getMbName(), sDate);
		if (bonusByDate == null) {
			bonusByDate = new BonusByDate();
			bonusByDate.setBringDate(new Date());
			bonusByDate.setMbName(commandMember.getMbName());
			bonusByDate.setStbringDate(sDate);
			bonusByDate.setCountBonus(0);
			bonusByDate.setFuliBonus(0);
			bonusByDate.setLiderBonus(0);
			bonusByDate.setOrgBonus(0);
			bonusByDate.setSaleBonus(0);
			bonusByDateDao.save(bonusByDate);
			bonusByDateDao.flush();
			 bonusByDate = bonusByDateDao.getBonusByDate(
						commandMember.getMbName(), sDate);
		}
		
		bonusByDate.setSaleBonus(bonusByDate.getSaleBonus() + money);
		bonusByDate.setCountBonus(bonusByDate.getCountBonus() + money);
		bonusByDateDao.update(bonusByDate);
		bonusByDateDao.flush();

		// 管理奖金
		int i = 1;
		do {
			if (commandMember.getRecommendMbName().equals("BMsystem")) {
				break;
			}
			if (i == 1) {
				money = millRecord.getBuyPrice()
						* BcaiUtils.MANAGE.CLASS_A.getName();
			} else if (i == 2) {
				money = millRecord.getBuyPrice()
						* BcaiUtils.MANAGE.CLASS_B.getName();
			} else if (i >= 3 && i <= 7) {
				money = millRecord.getBuyPrice()
						* BcaiUtils.MANAGE.CLASS_C.getName();
			} else if (i >= 8 && i <= 10) {
				money = millRecord.getBuyPrice()
						* BcaiUtils.MANAGE.CLASS_D.getName();
			}
			if (commandMember.getUsClass() >= i) {
              
                MemberWallet pmemberWallet = memberWalletDao.findMemberWalletById(commandMember.getMbName());
                pmemberWallet.setWallet(pmemberWallet.getWallet()+money);
                
				MemberWalletList pmemberWalletList = new MemberWalletList();
				pmemberWalletList.setAmoney(money);
				pmemberWalletList.setBalance(pmemberWallet.getWallet());
				pmemberWalletList.setCreateTime(new Date());
				pmemberWalletList.setCurrencyType(0);
				pmemberWalletList.setDescription("管理奖，因：" + member.getMbName());
				pmemberWalletList.setMbName(commandMember.getMbName());
				pmemberWalletList.setType(0);
				memberWalletListDao.save(pmemberWalletList);
				memberWalletListDao.flush();
				memberWalletDao.update(pmemberWallet);
				memberWalletDao.flush();
//				memberWalletListService.modifyMemberWalletList(
//						pmemberWalletList, -1, null);

				MemberBonusList pmemberBonusList = new MemberBonusList();
				pmemberBonusList.setMbName(commandMember.getMbName());
				pmemberBonusList.setBonusBringMoney(money);
				pmemberBonusList.setBonusBringTime(new Date());
				pmemberBonusList.setBonusRemark("因会员：" + member.getMbName()
						+ "产生");
				pmemberBonusList.setBonusSort(2);
				pmemberBonusList.setEpRemain(0);
				memberBonusListDao.save(pmemberBonusList);
				memberBonusListDao.flush();

				sDate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
				byDSQL = "from BonusByDate where mbName = ? and stbringDate = ?";
				BonusByDate sbonusByDate = bonusByDateDao.getBonusByDate(
						commandMember.getMbName(), sDate);
				if (sbonusByDate == null) {
					sbonusByDate = new BonusByDate();
					sbonusByDate.setBringDate(new Date());
					sbonusByDate.setMbName(commandMember.getMbName());
					sbonusByDate.setStbringDate(sDate);
					sbonusByDate.setCountBonus(0);
					sbonusByDate.setFuliBonus(0);
					sbonusByDate.setLiderBonus(0);
					sbonusByDate.setOrgBonus(0);
					sbonusByDate.setSaleBonus(0);
					bonusByDateDao.save(sbonusByDate);
					bonusByDateDao.flush();
					sbonusByDate = bonusByDateDao.getBonusByDate(
							commandMember.getMbName(), sDate);
				}
				
				sbonusByDate
						.setLiderBonus(sbonusByDate.getLiderBonus() + money);
				sbonusByDate
						.setCountBonus(sbonusByDate.getCountBonus() + money);
				bonusByDateDao.update(sbonusByDate);

				bonusByDateDao.flush();
			}

			commandMember = memberDao.findByField("mbName",
					commandMember.getRecommendMbName());

			i++;

		} while (i < 11);
		millRecord.setIsUse("no");
		millRecordDao.update(millRecord);
		millRecordDao.flush();
		addLeaderAch(millRecord.getBuyPrice(), member);
		
		return true;

	}

	private void addLeaderAch(double bmMoney, Member member) {
		Member commandMember = member;
		if (commandMember.getRecommendMbName().equals("BMsystem")) {
			return;
		}
		boolean isWh = true;
		do {
			Member nmember = memberDao.get(commandMember.getId());
			nmember.setAchievement(nmember.getAchievement() + bmMoney);
			memberDao.update(nmember);
			memberDao.flush();
			commandMember = memberDao.findByField("mbName",
					commandMember.getParentUsername());
			if (commandMember.getRecommendMbName().equals("BMsystem")) {
				isWh = false;
			}
		} while (isWh);

	}
	
	

	@Override
	public boolean mill() {
       
		List<MillRecord> millRecords = millRecordDao.mills();
		if (millRecords.size() == 0) {
			return false;
		}

		MillRecord millRecord = millRecords.get(0);
		AppLog.info("mill："+millRecord.getId()+" bring", logger);
		if (millRecord.getIsCount().equals("no")) {
			return true;
		}
		if (millRecord.getHoldDay() >= 30) {
			millRecord.setIsCount("no");
			millRecord.setModelNum("yes");
			millRecord.setHoldDay(millRecord.getHoldDay() + 1);
			millRecordDao.update(millRecord);
			millRecordDao.flush();
			return true;
		}

		double money = millRecord.getBuyPrice() * 0.01;
		MemberWallet pmemberWallet = memberWalletDao.findMemberWalletById(millRecord.getMbName());
		pmemberWallet.setWallet(pmemberWallet.getWallet()+money);
		MemberWalletList pmemberWalletList = new MemberWalletList();
		pmemberWalletList.setAmoney(money);
		pmemberWalletList.setBalance(pmemberWallet.getWallet());
		pmemberWalletList.setCreateTime(new Date());
		pmemberWalletList.setCurrencyType(0);
		pmemberWalletList.setDescription("M-" + millRecord.getId() + "-BM产生");
		pmemberWalletList.setMbName(millRecord.getMbName());
		pmemberWalletList.setType(9);
		memberWalletListDao.save(pmemberWalletList);
		memberWalletListDao.flush();
		memberWalletDao.update(pmemberWallet);
		memberWalletDao.flush();
//		memberWalletListService.modifyMemberWalletList(pmemberWalletList, -1,
//				null);

		MemberBonusList pmemberBonusList = new MemberBonusList();
		pmemberBonusList.setMbName(millRecord.getMbName());
		pmemberBonusList.setBonusBringMoney(money);
		pmemberBonusList.setBonusBringTime(new Date());
		pmemberBonusList.setBonusRemark("M-" + millRecord.getId() + "-BM产生");
		pmemberBonusList.setBonusSort(4);
		pmemberBonusList.setEpRemain(0);
		memberBonusListDao.save(pmemberBonusList);
		memberBonusListDao.flush();

		String sDate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
		String byDSQL = "from BonusByDate where mbName = ? and stbringDate = ?";
		BonusByDate bonusByDate = bonusByDateDao.getBonusByDate(
				millRecord.getMbName(), sDate);
		if (bonusByDate == null) {
			bonusByDate = new BonusByDate();
			bonusByDate.setBringDate(new Date());
			bonusByDate.setMbName(millRecord.getMbName());
			bonusByDate.setStbringDate(sDate);
			bonusByDate.setCountBonus(0);
			bonusByDate.setFuliBonus(0);
			bonusByDate.setLiderBonus(0);
			bonusByDate.setOrgBonus(0);
			bonusByDate.setSaleBonus(0);
			bonusByDateDao.save(bonusByDate);
			bonusByDateDao.flush();
			bonusByDate = bonusByDateDao.getBonusByDate(
					millRecord.getMbName(), sDate);
		}
		
		bonusByDate.setFuliBonus(bonusByDate.getFuliBonus() + money);
		bonusByDate.setCountBonus(bonusByDate.getCountBonus() + money);
		bonusByDateDao.update(bonusByDate);
		bonusByDateDao.flush();
		if ((millRecord.getHoldDay() + 1) == 30) {
			millRecord.setModelNum("yes");
		}
		millRecord.setIsCount("no");
		millRecord.setHoldDay(millRecord.getHoldDay() + 1);
		millRecordDao.update(millRecord);
		millRecordDao.flush();
		return true;
	}

	@Override
	public boolean countDayBonus() {

		return false;
	}

	@Override
	public Message recoveryBonus() {
		millRecordDao.recoveryBonus();
		memberStokenDao.reIsMill(0);
		return new Message(1, WebUtils.getMesString("bm.service.a21"));
	}

	@Override
	public void checkMill() {
		// TODO Auto-generated method stub
		millRecordDao.checkMill();
	}

	@Override
	public boolean splitfo() {
		// TODO Auto-generated method stub
		return false;
	}

}
