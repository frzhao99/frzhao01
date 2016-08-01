package com.bcai.service.impl;

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
import com.bcai.dao.HtcAmountDao;
import com.bcai.dao.MemberBonusListDao;
import com.bcai.dao.MemberDao;
import com.bcai.dao.MemberStokenDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.dao.StokenBuyDao;
import com.bcai.dao.StokenDealRecordDao;
import com.bcai.dao.StokenSellDao;
import com.bcai.dao.TodayIndexDao;
import com.bcai.domain.BonusByDate;
import com.bcai.domain.BuyDebrisRecord;
import com.bcai.domain.DebrisRecord;
import com.bcai.domain.HtcAmount;
import com.bcai.domain.Member;
import com.bcai.domain.MemberBonusList;
import com.bcai.domain.MemberStoken;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.domain.StokenBuy;
import com.bcai.domain.StokenDealRecord;
import com.bcai.domain.StokenSell;
import com.bcai.domain.TodayIndex;
import com.bcai.service.MemberWalletListService;
import com.bcai.service.StokenDealRecordService;
import com.bcai.web.BcaiUtils;
import com.symbio.commons.AppLog;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.DateUtils;

@Service("stokenDealRecordServiceImpl")
public class StokenDealRecordServiceImpl extends
		AbstractServiceImpl<StokenDealRecord, Long> implements
		StokenDealRecordService {

	@Autowired
	private StokenDealRecordDao stokenDealRecordDao;

	@Autowired
	private StokenBuyDao stokenBuyDao;

	@Autowired
	private StokenSellDao stokenSellDao;

	@Autowired
	private HtcAmountDao htcAmountDao;

	@Autowired
	private MemberStokenDao memberStokenDao;
	
	@Autowired
	private MemberWalletListDao memberWalletListDao;

	@Autowired
	private MemberWalletDao memberWalletDao;

	@Autowired
	private MemberBonusListDao memberBonusListDao;
	
	@Autowired
	private BonusByDateDao bonusByDateDao;
	
	@Autowired
	private DebrisRecordDao debrisRecordDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BuyDebrisRecordDao buyDebrisRecordDao;
	
	@Autowired
	private MemberWalletListService memberWalletListService;
	
	@Autowired
	private TodayIndexDao todayIndexDao;


	
    
	private static Logger logger = Logger.getLogger(StokenDealRecordServiceImpl.class);  

	@Override
	public GenericDaoImpl<StokenDealRecord, Long> getDao() {
		return stokenDealRecordDao;
	}

	@Override
	public boolean dealStoken() {
		Calendar c=new GregorianCalendar();
		int hour= c.get(Calendar.HOUR_OF_DAY);
		int minu = c.get(Calendar.MINUTE);
    	
		
		if(hour >= 22){
    		return false;
    	}
		
		if(hour>=0 && hour<9){
			return false;
		}
		
		
		
		
		AppLog.info("执行匹配成交", logger);
		HtcAmount htcAmount = htcAmountDao.get(BcaiUtils.BTCAMOUNT_ID);
		StokenSell stokenSell = null;
		String rmSQL = "";
		rmSQL = "from StokenSell where (state = ? or state = ?) and tokenSellPrice < ? order by tokenSellPrice desc";
		List<StokenSell> stokenSells = stokenSellDao.findList(rmSQL,new Object[] {0,1,htcAmount.getPrice()});
		if(stokenSells.size() > 0){
			htcAmount.setPrice(stokenSells.get(0).getTokenSellPrice());
			htcAmountDao.update(htcAmount);
			htcAmountDao.flush();
			return true;
		}
		
		stokenSells = stokenSellDao
				.findStokenSellsByPrice(htcAmount.getPrice());
		if (stokenSells.size() == 0) {
			rmSQL = "from StokenSell where (state = ? or state = ?) and tokenSellPrice <= ? order by tokenSellPrice desc";
			stokenSells = stokenSellDao.findList(rmSQL,new Object[] {0,1,htcAmount.getPrice()});
			if(stokenSells.size() == 0){
				rmSQL = "from StokenSell where (state = ? or state = ?) and tokenSellPrice > ? order by tokenSellPrice asc";
				stokenSells = stokenSellDao.findList(rmSQL,new Object[] {0,1,htcAmount.getPrice()});
				if (stokenSells.size() == 0) {
					return false;
				} else {
					htcAmount.setPrice(stokenSells.get(0).getTokenSellPrice());
					htcAmountDao.update(htcAmount);
					htcAmountDao.flush();					
					return true;
				}
			}else{
				htcAmount.setPrice(stokenSells.get(0).getTokenSellPrice());
				htcAmountDao.update(htcAmount);
				htcAmountDao.flush();				
				return true;
			}
		}else{
			stokenSell =  stokenSells.get(0);
		}
		
		rmSQL = "from StokenBuy where (state = ? or state = ?) and tokenBuyPrice >= ? order by tokenBuyPrice desc,tokenBuyTime asc";
		List<StokenBuy> stokenBuys = stokenBuyDao.findList(rmSQL, new Object[] {
				0, 1, htcAmount.getPrice() });
		StokenBuy stokenBuy = null;
		if (stokenBuys.size() == 0) {
			return false;

		} else {
			stokenBuy = stokenBuys.get(0);
		}
  
		double buyPrice = htcAmount.getPrice();
		double buyNum = stokenBuy.getTokenBuyCount() - stokenBuy.getDealCount();
		
		
		double sellNum = stokenSell.getTokenSellCount()
				- stokenSell.getSelledCount();
		double delNum = 0;
		if (buyNum >= sellNum) {
			delNum = sellNum;
			stokenSell.setSelledCount(stokenSell.getTokenSellCount());
			stokenSell.setState(2);
			stokenSellDao.update(stokenSell);
			stokenSellDao.flush();
			double dealMoney = stokenBuy.getDealAmount()+delNum*buyPrice;
			stokenBuy.setDealCount(stokenBuy.getDealCount() + delNum);
			stokenBuy.setDealAmount(dealMoney);
			if(buyNum == sellNum){
				stokenBuy.setState(2);
				double diffMoney = (stokenBuy.getDealCount()*stokenBuy.getTokenBuyPrice())-dealMoney;
				if(diffMoney>0){
					checkdeal(diffMoney,stokenBuy.getMbName());
				}
				
			}else{
				stokenBuy.setState(1);
			}
			stokenBuyDao.update(stokenBuy);
			stokenBuyDao.flush();
		} else {
			delNum = buyNum;
			stokenSell.setSelledCount(stokenSell.getSelledCount() + delNum);
			stokenSell.setState(1);
			stokenSellDao.update(stokenSell);
			stokenSellDao.flush();
			double dealMoney = stokenBuy.getDealAmount()+delNum*buyPrice;
			stokenBuy.setDealCount(stokenBuy.getDealCount() + delNum);
			stokenBuy.setDealAmount(dealMoney);
			stokenBuy.setIsCheck("no");
			stokenBuy.setState(2);
			double diffMoney = (stokenBuy.getDealCount()*stokenBuy.getTokenBuyPrice())-dealMoney;
			if(diffMoney>0){
				checkdeal(diffMoney,stokenBuy.getMbName());
			}
			stokenBuyDao.update(stokenBuy);
			stokenBuyDao.flush();
		}
		StokenDealRecord stokenDealRecord = new StokenDealRecord();
		stokenDealRecord.setDealNum(delNum);
		stokenDealRecord.setDealTime(new Date());
		stokenDealRecord.setPrice(buyPrice);
		stokenDealRecord.setStokenBuyId(stokenBuy.getId());
		stokenDealRecord.setStokenSellId(stokenSell.getId());
		stokenDealRecordDao.save(stokenDealRecord);
		stokenDealRecordDao.flush();
		
		String msSql = "from MemberStoken where mbName= '"+stokenBuy.getMbName()+"'";
		MemberStoken buyMemberStoken = memberStokenDao.findByFieldSyn(msSql);
		buyMemberStoken.setTokenRemain(buyMemberStoken.getTokenRemain()
				+ delNum);
		memberStokenDao.update(buyMemberStoken);	
		
	   
		MemberWallet memberWallet = memberWalletDao.findMemberWalletById(stokenSell.getMbName());
		memberWallet.setWallet(memberWallet.getWallet()+delNum*buyPrice*0.9);
		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setAmoney(delNum*buyPrice*0.9);
		memberWalletList.setBalance(memberWallet.getWallet());
		memberWalletList.setCreateTime(new Date());
		memberWalletList.setCurrencyType(0);
		memberWalletList.setDescription("卖出碎片");
		memberWalletList.setMbName(stokenSell.getMbName());
		memberWalletList.setType(6);
		//memberWalletListService.modifyMemberWalletList(memberWalletList, -1, null);
		memberWalletListDao.save(memberWalletList);
		memberWalletListDao.flush();
		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();
		
		for(int k =0;k < delNum;k++){
			BuyDebrisRecord buyDebrisRecord = new BuyDebrisRecord();
			buyDebrisRecord.setBuyDate(new Date());
			buyDebrisRecord.setBuyPrice(buyPrice);
			buyDebrisRecord.setMbName(stokenBuy.getMbName());
			buyDebrisRecord.setIsUse("yes");
			buyDebrisRecordDao.save(buyDebrisRecord);
			buyDebrisRecordDao.flush();
		}
		
		
				
		DebrisRecord deBrisRecord = new DebrisRecord();
		deBrisRecord.setAmoney(delNum);
		deBrisRecord.setType(5);
		deBrisRecord.setMbName(stokenBuy.getMbName());
		deBrisRecord.setBalance(buyMemberStoken.getTraDebrisRemain()+buyMemberStoken.getTokenRemain());
		deBrisRecord.setOccDate(new Date());
		deBrisRecord.setDescription("买入碎片："+delNum+"个");
		debrisRecordDao.save(deBrisRecord);
		debrisRecordDao.flush();
		
		
		
		tradeBonus(delNum*buyPrice,stokenSell.getMbName()); 
		return true;

	}
	
	private void checkdeal(double money,String mbName){
		 MemberWallet memberWallet = memberWalletDao.findMemberWalletById(mbName);
		 memberWallet.setWallet(memberWallet.getWallet()+money);
		 MemberWalletList memberWalletList = new MemberWalletList();
		 memberWalletList.setAmoney(money);
		 memberWalletList.setBalance(memberWallet.getWallet());
		 memberWalletList.setCreateTime(new Date());
		 memberWalletList.setCurrencyType(0);
		 memberWalletList.setDescription("返还购买碎片多余BM币");
		 memberWalletList.setMbName(mbName);
		 memberWalletList.setType(10);
		 memberWalletListDao.save(memberWalletList);
		 memberWalletListDao.flush();
		 memberWalletDao.update(memberWallet);
		 memberWalletDao.flush();
		// memberWalletListService.modifyMemberWalletList(memberWalletList, -1, null);
		 
	}
	
	@Override
	public void tradeBonus(double amony,String mbName){
		int i = 1;
		Member member  = memberDao.findByField("mbName", mbName);
		Member commandMember = memberDao.findByField("mbName", member.getRecommendMbName());
		
		do{
			if (commandMember.getRecommendMbName().equals("BMsystem")) {
				return;
			}
			
			if(commandMember.getUsClass() >= i){
				
				double money = amony*0.01;
				MemberWallet memberWallet = memberWalletDao.findMemberWalletById(commandMember.getMbName());
				memberWallet.setWallet(memberWallet.getWallet()+money);
				MemberWalletList memberWalletList = new MemberWalletList();
				memberWalletList.setAmoney(money);
				memberWalletList.setBalance(memberWallet.getWallet());
				memberWalletList.setCreateTime(new Date());
				memberWalletList.setCurrencyType(0);
				memberWalletList.setDescription("交易奖金，因：" + member.getMbName());
				memberWalletList.setMbName(commandMember.getMbName());
				memberWalletList.setType(0);
				memberWalletListDao.save(memberWalletList);
				memberWalletListDao.flush();
				memberWalletDao.update(memberWallet);
				memberWalletDao.flush();
				//memberWalletListService.modifyMemberWalletList(memberWalletList, -1, null);

				MemberBonusList memberBonusList = new MemberBonusList();
				memberBonusList.setMbName(commandMember.getMbName());
				memberBonusList.setBonusBringMoney(money);
				memberBonusList.setBonusBringTime(new Date());
				memberBonusList.setBonusRemark("因会员：" + member.getMbName() + "产生");
				memberBonusList.setBonusSort(3);
				memberBonusList.setEpRemain(0.0);
				memberBonusListDao.save(memberBonusList);
				memberBonusListDao.flush();

				
				
				String sDate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
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
				
				bonusByDate.setDividendBonus(bonusByDate.getDividendBonus()+money);
				bonusByDate.setCountBonus(bonusByDate.getCountBonus()+money);
				bonusByDateDao.update(bonusByDate);
				bonusByDateDao.flush();	
			}
			
			commandMember = memberDao.findByField("mbName", commandMember.getRecommendMbName());
			i++;
		}while(i < 6);
		
	}
    
	
	@Override
	public void countEvenDate(String Date) {
		Date newDate = DateUtils.strToDate(DateUtils.yyyy_MM_dd, Date);
		double dealNum = stokenDealRecordDao.countDealNumByDate(newDate);
		StokenDealRecord mstokenDel = stokenDealRecordDao.todayHighRecord(newDate);
		StokenDealRecord bstokenDel = stokenDealRecordDao.todayBottomRecord(newDate);
		StokenDealRecord cstokenDel = stokenDealRecordDao.todayCloseRecord(newDate);
		TodayIndex todayIndex = new TodayIndex();
		todayIndex.setCreateTime(newDate);
		if(mstokenDel == null){
			todayIndex.setMaxMoney(0);
		}else{
			todayIndex.setMaxMoney(mstokenDel.getPrice());
		}
		if(bstokenDel == null){
			todayIndex.setMinMoney(0);
		}else{
			todayIndex.setMinMoney(bstokenDel.getPrice());
		}
		if(todayIndex.getMaxMoney()>32){
			todayIndex.setOpeningMoney(todayIndex.getMaxMoney()-3);
		}else if(todayIndex.getMaxMoney()<=32){
			todayIndex.setOpeningMoney(todayIndex.getMaxMoney()-2);
		}
		if(cstokenDel == null){
			todayIndex.setColseMoney(0);
		}else{
			todayIndex.setColseMoney(cstokenDel.getPrice());
		}
		todayIndex.setDelNum(dealNum);
		
		todayIndexDao.save(todayIndex);
	}
}
