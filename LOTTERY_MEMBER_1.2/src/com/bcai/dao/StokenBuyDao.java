package com.bcai.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;








import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.MemberStoken;
import com.bcai.domain.StokenBuy;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class StokenBuyDao extends GenericDaoImpl<StokenBuy, Long>{
	
	
	public Page<StokenBuy> findStokenBuy(Page<StokenBuy> pageData){
		List<StokenBuy> stokenBuys = new ArrayList<StokenBuy>();
		int statIndex = pageData.getPagination().getCurrentlyPageFirstResoultIndex();
		int endIndex =pageData.getPagination().getPageSize();
		List list = this.find("select token_buy_time,mb_name,token_buy_price,token_buy_count,all_buy_count  from all_stoken_buy limit "+statIndex+","+endIndex+"");
		List topData = this.find("select count(*) from all_stoken_buy");
		int topCount = Integer.valueOf(topData.get(0).toString());
		for(int i = 0;i < list.size(); i++){
			StokenBuy stokenBuy = new StokenBuy();
			Object[] data = (Object[])list.get(i);			
			stokenBuy.setTokenBuyTime((Date)data[0]);
			stokenBuy.setMbName((String)data[1]);
			stokenBuy.setTokenBuyPrice((Double)data[2]);
			stokenBuy.setTokenBuyCount((Double)data[3]);
			stokenBuy.setAllBuyCount((Double)data[4]);
			stokenBuys.add(stokenBuy);
		}
		pageData.getPagination().setTotalCount(topCount);
		pageData.setResult(stokenBuys);
		
		return pageData;
	}
	
	public Page<StokenBuy> findGroupBuy(Page<StokenBuy> pageData){
		
		List<StokenBuy> stokenBuys = new ArrayList<StokenBuy>();
		int statIndex = pageData.getPagination().getCurrentlyPageFirstResoultIndex();
		int endIndex =pageData.getPagination().getPageSize();
		List list = this.find("SELECT a.token_buy_price,a.token_buy_count,a.deal_count from group_buy_count as a order by a.token_buy_price desc limit "+statIndex+","+endIndex+"");
		List topData = this.find("select count(*) from group_buy_count");
		int topCount = Integer.valueOf(topData.get(0).toString());
		for(int i = 0;i < list.size(); i++){
			StokenBuy stokenBuy = new StokenBuy();
			Object[] data = (Object[])list.get(i);			
			
			stokenBuy.setTokenBuyPrice((Double)data[0]);
			stokenBuy.setTokenBuyCount((Double)data[1]);
			stokenBuy.setDealCount((Double)data[2]);
		
			stokenBuys.add(stokenBuy);
		}
		pageData.getPagination().setTotalCount(topCount);
		pageData.setResult(stokenBuys);
		
		return pageData;
		
	}
	
	public StokenBuy findLatelyBuy(){
		String rmSQL = "from StokenBuy order by tokenBuyTime desc";
		Query query = this.getSession().createQuery(rmSQL);
		query.setMaxResults(1);
		List<StokenBuy> stokenBuys = query.list();		
		if(stokenBuys.size() == 0){
			return null;
		}
		return stokenBuys.get(0);
	}
	
	
	public StokenBuy vodoBuyDel(double price){
		String rmSQL = "from StokenBuy where tokenBuyPrice < :price and state = :state and buyType =:buyType";
		Query query = this.getSession().createQuery(rmSQL);
		query.setDouble("price", price);
		query.setInteger("state", 0);
		query.setInteger("buyType", 1);
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		List<StokenBuy> stokenBuys = query.list();		
		if(stokenBuys.size() == 0 || stokenBuys == null){
			return null;
		}
		return stokenBuys.get(0);
	}
	
	
	public List<StokenBuy> splituh(){
		String rmSQL = "from StokenBuy where state = :state";
		Query query = this.getSession().createQuery(rmSQL);
		query.setInteger("state",0);
        query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
}
