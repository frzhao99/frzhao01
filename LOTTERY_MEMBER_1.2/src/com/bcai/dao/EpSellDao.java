package com.bcai.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.EpSell;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.DateUtils;

@Repository
public class EpSellDao extends GenericDaoImpl<EpSell, Long> {
	
	public void updateEpSellOrCheck(String orCheck){
		String upHQL ="update EpSell set orCheckPayMoney = ?";
		Query query = this.getSession().createQuery(upHQL);
	    query.setString(0, orCheck);	   
	    query.executeUpdate();
	}
	
	
	
	public void updateEpSellCheckConfPay(String orCheck){
		String upHQL ="update EpSell set orCheckConfPay = ?";
		Query query = this.getSession().createQuery(upHQL);
	    query.setString(0, orCheck);	   
	    query.executeUpdate();
	}
	
	public List<EpSell> findPayMoney(){
		String rmSQL = "from EpSell where orCheckPayMoney = :orCheckPayMoney and status = :status";
		Query query = this.getSession().createQuery(rmSQL).setString("orCheckPayMoney","no");
		query.setInteger("status", 1);
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<EpSell> findCheckConfPay(){
		String rmSQL = "from EpSell where orCheckConfPay = :orCheckConfPay and status = :status";
		Query query = this.getSession().createQuery(rmSQL).setString("orCheckConfPay","no");
		query.setInteger("status",2);
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<EpSell> findEpSells(String mbName){
		String rmSQL = "from EpSell where sellDate >= :ssellDate and sellDate <= :esellDate and mbName = :mbName";
		Query query = this.getSession().createQuery(rmSQL);
		query.setTimestamp("ssellDate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, "2015-12-29 00:00:00"));
		query.setTimestamp("esellDate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, "2015-12-31 23:59:00"));
		query.setString("mbName",mbName);
		return query.list();
	}
	
	
	public List<EpSell> findEpSellByMaName(String epMbname){
		String rmSQL = "from EpSell where buyMbName = :buyMbName and status = :status and sellQQ = :sellQQ";
		Query query = this.getSession().createQuery(rmSQL).setString("buyMbName",epMbname);
		query.setString("status","4");
		query.setString("sellQQ","1");
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<EpSell> findEpSellByCC(){
		String rmSQL = "from EpSell where status = :status and admin_remark = :admin_remark";
		Query query = this.getSession().createQuery(rmSQL);
		query.setString("status","0");
		query.setString("admin_remark","社区冻结");
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	
}
