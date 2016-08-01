package com.bcai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.HtcAmountDao;
import com.bcai.dao.StokenBuyDao;
import com.bcai.dao.StokenDealRecordDao;
import com.bcai.dao.StokenSellDao;
import com.bcai.domain.HtcAmount;
import com.bcai.domain.StokenBuy;
import com.bcai.domain.StokenDealRecord;
import com.bcai.domain.StokenSell;
import com.bcai.service.HtcAmountService;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.MarketInfo;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.DateUtils;

@Service
public class HtcAmountServiceImpl extends AbstractServiceImpl<HtcAmount,Long> implements HtcAmountService {
 
	@Autowired
	private HtcAmountDao htcAmountDao;
	
	@Autowired
	private StokenBuyDao stokenBuyDao;
	
	@Autowired
	private StokenSellDao stokenSellDao;
	
	@Autowired
	private StokenDealRecordDao stokenDealRecordDao;
	
	
	
	
	@Override
	public GenericDaoImpl<HtcAmount, Long> getDao() {		
		return htcAmountDao;
	}



	@Override
	public MarketInfo countMarket() {
		MarketInfo marketInfo = new MarketInfo();
		HtcAmount htcAmount = htcAmountDao.get(BcaiUtils.BTCAMOUNT_ID);
		//当前价格
		marketInfo.setNewestPrice(htcAmount.getPrice());
		marketInfo.setLatelySellPrice(htcAmount.getOpenPrice());
		StokenBuy stokenBuy = stokenBuyDao.findLatelyBuy();
		if(stokenBuy == null){
			marketInfo.setLatelyBuyPrice(0);
		}else{
			marketInfo.setLatelyBuyPrice(stokenBuy.getTokenBuyPrice());			
		}
//		StokenSell stokenSell = stokenSellDao.findLatelySell();
//		if(stokenSell == null){
//			marketInfo.setLatelySellPrice(0);
//		}else{
//			marketInfo.setLatelySellPrice(stokenSell.getTokenSellPrice());			
//		}
		StokenDealRecord bottomTokenDealRecord = stokenDealRecordDao.todayBottomRecord(new Date());
		StokenDealRecord highTokenDealRecord = stokenDealRecordDao.todayHighRecord(new Date());
		if(bottomTokenDealRecord == null){
			marketInfo.setTodayBottomPrice(0);
		}else{
			marketInfo.setTodayBottomPrice(bottomTokenDealRecord.getPrice());
		}
		
		if(highTokenDealRecord == null){
			marketInfo.setTodayHighPrice(0);
		}else{
			marketInfo.setTodayHighPrice(highTokenDealRecord.getPrice());
		}
		
		double dealNum = stokenDealRecordDao.countDealNum();
		marketInfo.setVolume(dealNum);
		
		return marketInfo;
	}



	@Override
	public List<MarketInfo> weekMarket() {
		long timeMi = System.currentTimeMillis();
		List<MarketInfo> marks = new ArrayList<MarketInfo>();
		long day = 24*60*60*1000;
		for(int i=0;i<7;i++){
			timeMi=timeMi-((i)*day);
			MarketInfo mk = new MarketInfo();	
			Date de = new Date(timeMi);
			StokenDealRecord bottomTokenDealRecord = stokenDealRecordDao.todayBottomRecord(de);
			StokenDealRecord highTokenDealRecord = stokenDealRecordDao.todayHighRecord(de);
			if(bottomTokenDealRecord == null){
				mk.setTodayBottomPrice(0);
			}else{
				mk.setTodayBottomPrice(bottomTokenDealRecord.getPrice());
			}
			
			if(highTokenDealRecord == null){
				mk.setTodayHighPrice(0);
			}else{
				mk.setTodayHighPrice(highTokenDealRecord.getPrice());
			}
			mk.setTimestep(DateUtils.DateToStr(DateUtils.yyyy_MM_dd, de));
			marks.add(mk);
		}
		return marks;
	}
    
	
}
