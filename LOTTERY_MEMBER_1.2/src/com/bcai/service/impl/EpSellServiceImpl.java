package com.bcai.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.CreditListDao;
import com.bcai.dao.EpSellDao;
import com.bcai.dao.MemberDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.domain.CreditList;
import com.bcai.domain.EpSell;
import com.bcai.domain.Member;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.service.EpSellService;
import com.bcai.service.MemberWalletListService;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.WebUtils;

@Service("epSellServiceImpl")
public class EpSellServiceImpl extends AbstractServiceImpl<EpSell, Long>
		implements EpSellService {

	@Autowired
	private EpSellDao epSellDao;

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberWalletDao memberWalletDao;
	
	@Autowired
	private MemberWalletListDao memberWalletListDao;

	@Autowired
	private MemberWalletListService memberWalletListService;
	
	@Autowired
	private CreditListDao creditListDao;

	private static byte[] sellLock = new byte[0]; // 特殊的instance变量

	@Override
	public GenericDaoImpl<EpSell, Long> getDao() {

		return epSellDao;
	}

	@Override
	public void updateEpSell(EpSell epSell) {
		epSellDao.update(epSell);
		epSellDao.flush();

	}

	@Override
	public boolean checkEp() {
		List<EpSell> epSells = epSellDao.findPayMoney();
		if (epSells == null || epSells.size() == 0) {
			return false;
		}
		EpSell epSell = epSells.get(0);
		Date buyDate = epSell.getBuyDate();
		Long nowMin = System.currentTimeMillis();
		Long buyMin = buyDate.getTime();
		Long tabMin = (long) (6 * 60 * 60 * 1000);
		if ((nowMin - buyMin) > tabMin) {
			String mbName = epSell.getBuyMbName();
			epSell.setStatus(0);
			epSell.setBuyDate(null);
			epSell.setBuyMbName(null);
			epSell.setBuyPhone(null);
			epSell.setBuyDate(null);
			Member member = memberDao.findByField("mbName", mbName);
			member.setCredit(member.getCredit() - 1);
			memberDao.update(member);
			memberDao.flush();
			CreditList creditList = new CreditList();
			creditList.setMbName(member.getMbName());
			creditList.setCount(1);
			creditList.setLaterCount(member.getCredit());
			creditList.setReason("购买BM币后，在六小时内未打款或未确认打款");
			creditList.setType(2);
			creditList.setCreateTime(new Date());
			creditListDao.save(creditList);
		}
		epSell.setOrCheckPayMoney("yes");
		epSellDao.update(epSell);
		epSellDao.flush();
		return true;

	}

	@Override
	public boolean checkConfPayEp() {
		List<EpSell> epSells = epSellDao.findCheckConfPay();
		if (epSells == null || epSells.size() == 0) {
			return false;
		}
		EpSell epSell = epSells.get(0);
		Date buyDate = epSell.getTrDate();
		Long nowMin = System.currentTimeMillis();
		Long buyMin = buyDate.getTime();
		Long tabMin = (long) (72 * 60 * 60 * 1000);
		if ((nowMin - buyMin) > tabMin) {
			epSell.setStatus(4);
			Member member = memberDao.findByField("mbName", epSell.getMbName());
			member.setCredit(member.getCredit() - 1);
			memberDao.update(member);
			memberDao.flush();
			CreditList creditList = new CreditList();
			creditList.setMbName(member.getMbName());
			creditList.setCount(1);
			creditList.setLaterCount(member.getCredit());
			creditList.setReason("卖出BM币，超过72小时未确认订单状态");
			creditList.setType(2);
			creditList.setCreateTime(new Date());
			creditListDao.save(creditList);
			
		}
		epSell.setOrCheckConfPay("yes");
		epSellDao.update(epSell);
		epSellDao.flush();
		return true;
	}

	@Override
	public void updateCheckEp() {
		epSellDao.updateEpSellCheckConfPay("no");
		epSellDao.updateEpSellOrCheck("no");
	}

	@Override
	public Message delteOrderIdSyn(Long ovderID) {
		
		EpSell epSell = epSellDao.getSyn(ovderID);
			if (epSell.getStatus() == 0) {
                MemberWallet memberWallet = memberWalletDao.findMemberWalletById(epSell.getMbName());
                memberWallet.setWallet(memberWallet.getWallet()+epSell.getMoney());
                memberWallet.setRuby(memberWallet.getRuby()+epSell.getMoney()*0.4);
                MemberWalletList memberWalletList = new MemberWalletList();
				memberWalletList.setAmoney(epSell.getMoney());
				memberWalletList.setBalance(0);
				memberWalletList.setCreateTime(new Date());
				memberWalletList.setCurrencyType(0);
				memberWalletList.setDescription("取消BM币卖单：" + epSell.getMoney());
				memberWalletList.setMbName(epSell.getMbName());
				memberWalletList.setType(8);
				memberWalletListDao.save(memberWalletList);
				memberWalletListDao.flush();
				memberWalletDao.update(memberWallet);
				memberWalletDao.flush();
				epSellDao.delete(epSell);
				return new Message(1, WebUtils.getMesString("bm.service.a1"));
			} else {

				return new Message(1, WebUtils.getMesString("bm.service.a2"));
			}
		
	}

	@Override
	public Message sellEp(EpSell epSell) {
		MemberWallet memberWallet = memberWalletDao.findMemberWalletById(epSell.getMbName());
		if (memberWallet.getWallet() < epSell.getMoney()) {
		
			return new Message(0, WebUtils.getMesString("bm.service.a3"));
		}
		if(epSell.getMoney() > memberWallet.getWallet()*0.1){
			return new Message(0, "每次只能交易总BM币的10%");
		}
		
		if(memberWallet.getRuby() < epSell.getMoney()*0.4){
			return new Message(0, WebUtils.getMesString("bm.public.a50"));
		}else{
				memberWallet.setRuby(memberWallet.getRuby()- epSell.getMoney()*0.4);
		}
	
		double balance = memberWallet.getWallet() - epSell.getMoney();
		
		

		MemberWalletList mwall = new MemberWalletList();
		mwall.setAmoney(epSell.getMoney());
		mwall.setBalance(balance);
		mwall.setCreateTime(new Date());
		mwall.setCurrencyType(0);
		mwall.setType(4);
		mwall.setDescription("交易中心卖出BM币：" + epSell.getMoney());
		mwall.setMbName(memberWallet.getMbName());
		memberWalletListDao.save(mwall);
		epSellDao.save(epSell);
		memberWallet.setWallet(balance);
		memberWalletDao.update(memberWallet);

		
		return new Message(1,WebUtils.getMesString("bm.service.a4"));
	}

	@Override
	public List<EpSell> findByEpsByMbName(String mbName) {
		String mSql = "from EpSell where mbName = ? and status >= ? and status < ?";
		List<EpSell> epSells = epSellDao.findList(mSql, new Object[]{mbName,0,3});
		return epSells;
	}

	@Override
	public List<EpSell> findByEPsByBuyMbName(String mbName) {
		String mSql = "from EpSell where buyMbName = ? and status = ?";
		List<EpSell> epSells = epSellDao.findList(mSql, new Object[]{mbName,1});
		return epSells;
	}

	@Override
	public boolean revokeEpSell(String epMbname) {
		List<EpSell> epSells = epSellDao.findEpSellByMaName(epMbname);
		if(epSells == null || epSells.size() == 0){
			return false;
		}
		EpSell epSell = epSells.get(0);
		MemberWallet memberWallet = memberWalletDao.findByFieldSyn("from MemberWallet where mbName = '"+epSell.getMbName()+"'");
		memberWallet.setWallet(memberWallet.getWallet()+epSell.getMoney());
		memberWalletDao.update(memberWallet);
		
		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setAmoney(epSell.getMoney());
		memberWalletList.setBalance(memberWallet.getWallet());
		memberWalletList.setCreateTime(new Date());
		memberWalletList.setCurrencyType(0);
		memberWalletList.setDescription("撤销被"+epMbname+"购买的BM币");
		memberWalletList.setMbName(epSell.getMbName());
		memberWalletList.setType(8);
		memberWalletListDao.save(memberWalletList);
		
		epSell.setSellQQ("2");
		epSellDao.update(epSell);
		epSellDao.flush();
		return true;
	}

	@Override
	public boolean cckeEpSell() {
		// TODO Auto-generated method stub
		List<EpSell> epSells = epSellDao.findEpSellByCC();
		if(epSells == null || epSells.size() == 0){
			return false;
		}
		EpSell epSell = epSells.get(0);
		MemberWallet memberWallet = memberWalletDao.findByFieldSyn("from MemberWallet where mbName = '"+epSell.getMbName()+"'");
		memberWallet.setSapphire(memberWallet.getSapphire()+epSell.getMoney());
		memberWalletDao.update(memberWallet);
		epSell.setAdmin_remark("社区冻结2");
		epSell.setStatus(4);
		epSellDao.update(epSell);
		epSellDao.flush();
		
		return true;
	}

}
