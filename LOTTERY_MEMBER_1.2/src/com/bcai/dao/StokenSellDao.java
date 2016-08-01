package com.bcai.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.StokenSell;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class StokenSellDao extends GenericDaoImpl<StokenSell, Long> {
	
	
	public Page<StokenSell> findStokenSell(Page<StokenSell> pageData){
		List<StokenSell> stokenSells = new ArrayList<StokenSell>();
		int statIndex = pageData.getPagination().getCurrentlyPageFirstResoultIndex();
		int endIndex =pageData.getPagination().getPageSize();
		List list = this.find("select token_sell_price,surplus_sell_count,mb_name,all_sell_count from all_stoken_sell limit "+statIndex+","+endIndex+"");
		List topData = this.find("select count(*) from all_stoken_sell");
		int topCount = Integer.valueOf(topData.get(0).toString());
		for(int i = 0;i < list.size(); i++){
			StokenSell stokenSell = new StokenSell();
			Object[] data = (Object[])list.get(i);	
			stokenSell.setTokenSellPrice((Double)data[0]);
			stokenSell.setSurplusSellCount((Double)data[1]);
			stokenSell.setMbName((String)data[2]);
			stokenSell.setTokenSellCount((Double)data[3]);
			stokenSells.add(stokenSell);
		}
		pageData.getPagination().setTotalCount(topCount);
		pageData.setResult(stokenSells);
		
		return pageData;
	}
	
	public Page<StokenSell> findGroupSell(Page<StokenSell> pageData){
		List<StokenSell> stokenSells = new ArrayList<StokenSell>();
		int statIndex = pageData.getPagination().getCurrentlyPageFirstResoultIndex();
		int endIndex =pageData.getPagination().getPageSize();
		List list = this.find("SELECT a.all_sell_count,a.token_sell_price,a.deal_count from group_sell_count as a order by a.token_sell_price asc limit "+statIndex+","+endIndex+"");
		List topData = this.find("select count(*) from group_sell_count");
		int topCount = Integer.valueOf(topData.get(0).toString());
		for(int i = 0;i < list.size(); i++){
			StokenSell stokenSell = new StokenSell();
			Object[] data = (Object[])list.get(i);						
			stokenSell.setTokenSellCount((Double)data[0]);
			stokenSell.setTokenSellPrice((Double)data[1]);
			stokenSell.setSelledCount((Double)data[2]);
			stokenSells.add(stokenSell);
		}
		pageData.getPagination().setTotalCount(topCount);
		pageData.setResult(stokenSells);
		
		return pageData;
	}
	
	public List<StokenSell> findStokenSellsByPrice(Double price){		
		String rmSQL = "from StokenSell where (state = ? or state = ?) and tokenSellPrice = ? order by tokenSellTime asc";
		List<StokenSell> stokenSells = this.findList(rmSQL,new Object[] {0,1,price});
		return stokenSells;
	}
	
	public StokenSell findLatelySell(){
		String rmSQL = "from StokenSell order by tokenSellTime desc";
		Query query = this.getSession().createQuery(rmSQL);
		query.setMaxResults(1);
		List<StokenSell> stokenSells = query.list();
		if(stokenSells.size() == 0){
			return null;
		}
		return stokenSells.get(0);
	}
	
	public List<StokenSell> findStokenSellReake(){
		String rmSQL = "from StokenSell where tokenSellPrice = :tokenSellPrice and state = :state and selledCount=:selledCount";
		Query query = this.getSession().createQuery(rmSQL).setDouble("tokenSellPrice",38).setInteger("state", 2).setDouble("selledCount", 0);
		query.setMaxResults(1);
		List<StokenSell> stokenSells = query.list();
		if(stokenSells == null || stokenSells.size() == 0){
			return null;
		}
		return stokenSells;
	}
	
	public List<StokenSell> findRoevleByPrice(double price){
		String rmSQL = "from StokenSell where tokenSellPrice = :tokenSellPrice and state = :state";
		Query query = this.getSession().createQuery(rmSQL).setDouble("tokenSellPrice",price).setDouble("state", 0);
		List<StokenSell> stokenSells = query.list();
		if(stokenSells == null || stokenSells.size() == 0){
			return null;
		}
		return stokenSells;
	}

}
