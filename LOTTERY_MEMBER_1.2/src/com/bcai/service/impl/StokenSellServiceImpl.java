package com.bcai.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BuyDebrisRecordDao;
import com.bcai.dao.DebrisRecordDao;
import com.bcai.dao.MemberStokenDao;
import com.bcai.dao.StokenSellDao;
import com.bcai.domain.BuyDebrisRecord;
import com.bcai.domain.DebrisRecord;
import com.bcai.domain.MemberStoken;
import com.bcai.domain.StokenSell;
import com.bcai.service.StokenSellService;
import com.bcai.web.vo.Message;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.WebUtils;

@Service
public class StokenSellServiceImpl  extends AbstractServiceImpl<StokenSell, Long> implements StokenSellService  {
    
	@Autowired
	private StokenSellDao stokenSellDao;
	
	@Autowired
	private MemberStokenDao memberStokenDao;
	
	@Autowired
	private DebrisRecordDao debrisRecordDao;
	
	@Autowired
	private BuyDebrisRecordDao buyDebrisRecordDao;
	
	@Override
	public GenericDaoImpl<StokenSell, Long> getDao() {		
		return stokenSellDao;
	}

	@Override
	public Message sellDebris(double price, double count, String mbName) {
		String msSql = "from MemberStoken where mbName= '"+mbName+"'";
		MemberStoken memberStoken = memberStokenDao.findByFieldSyn(msSql);
		if(memberStoken.getTokenRemain()+memberStoken.getTraDebrisRemain() < count){
			return new Message(0,"碎片余额不足。");
		}
		StokenSell stokenSell = new StokenSell();
		stokenSell.setMbName(mbName);
		stokenSell.setState(0);
		stokenSell.setSelledCount(0.00);
		stokenSell.setTokenSellCount(count);
		DecimalFormat df1 = new DecimalFormat("###.000");
		stokenSell.setTokenSellPrice(Double.valueOf(df1.format(price)));
		stokenSell.setTokenSellTime(new Date());
		stokenSellDao.save(stokenSell);
		stokenSellDao.flush();
		double tradDebri = memberStoken.getTraDebrisRemain();
		double debri = 0.0;
		if(count <= tradDebri){
			memberStoken.setTraDebrisRemain(memberStoken.getTraDebrisRemain()-count);
		}else{
			debri = (count-tradDebri);
			memberStoken.setTraDebrisRemain(0);
		    memberStoken.setTokenRemain(memberStoken.getTokenRemain()-debri);
		    List<BuyDebrisRecord> buyDebrisRecords = buyDebrisRecordDao.findBuyDeb((int)debri, memberStoken.getMbName());
		    for(BuyDebrisRecord buyDebrisRecord:buyDebrisRecords){
			    buyDebrisRecordDao.delete(buyDebrisRecord);
				buyDebrisRecordDao.flush();
			}
			buyDebrisRecordDao.flush();
		}
		
		memberStokenDao.update(memberStoken);
		memberStokenDao.flush();	
		
		DebrisRecord deBrisRecord = new DebrisRecord();
		deBrisRecord.setAmoney(count);
		deBrisRecord.setType(4);
		deBrisRecord.setMbName(memberStoken.getMbName());
		deBrisRecord.setBalance(memberStoken.getTraDebrisRemain()+memberStoken.getTokenRemain());
		deBrisRecord.setOccDate(new Date());
		deBrisRecord.setDescription("卖出碎片："+count+"个");
		debrisRecordDao.save(deBrisRecord);
	    
		return new Message(1,WebUtils.getMesString("bm.service.a25"));
	}

	@Override
	public Page<StokenSell> findStokenSell(Page<StokenSell> pageData) {
		return stokenSellDao.findGroupSell(pageData);
	}

	@Override
	public boolean roevleDeale() {
		List<StokenSell> stokenSells = stokenSellDao.findStokenSellReake();
		if(stokenSells == null || stokenSells.size() == 0){
			return false;
		}
		StokenSell stokenSell = stokenSells.get(0);
		stokenSell.setState(0);
		stokenSell.setTokenSellPrice(26);
		stokenSell.setTokenSellRemark("system");
		stokenSellDao.update(stokenSell);
		
		return true;
	}

	@Override
	public boolean roevleByPrice(double price) {
		List<StokenSell> stokenSells = stokenSellDao.findRoevleByPrice(price);
		if(stokenSells == null){
			return false;
		}
		StokenSell stokenSell = stokenSells.get(0);
		MemberStoken memberStoken = memberStokenDao.findByFieldSyn("from MemberStoken where mbName = '"+stokenSell.getMbName()+"'");
		memberStoken.setTraDebrisRemain(memberStoken.getTraDebrisRemain()+stokenSell.getTokenSellCount());
		memberStokenDao.update(memberStoken);
		
		
		DebrisRecord debrisRecord = new DebrisRecord();
		debrisRecord.setAmoney(stokenSell.getTokenSellCount());
		debrisRecord.setBalance(memberStoken.getTokenRemain()+memberStoken.getTraDebrisRemain());
		debrisRecord.setDescription("撤销碎片价格为："+price+"的卖单");
		debrisRecord.setMbName(stokenSell.getMbName());
		debrisRecord.setOccDate(new Date());
		debrisRecord.setType(6);
		debrisRecordDao.save(debrisRecord);
		
		stokenSellDao.delete(stokenSell.getId());
		
		
		
		
		
		return true;
	}


}
