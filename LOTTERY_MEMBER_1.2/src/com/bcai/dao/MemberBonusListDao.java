package com.bcai.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.MemberBonusList;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.HibernateUtils;

/**
 * 会员奖金明细表
 * @author Fred
 *
 */
@Repository
public class MemberBonusListDao extends GenericDaoImpl<MemberBonusList, Long> {
	
	public double getDynamicBonus(String mbName){
		String hsql = "select sum(bonusBringMoney) from MemberBonusList where mbName = ? and (bonusSort = ? or bonusSort = ? or bonusSort = ?)";
		
		Query query = HibernateUtils.createQuery(this.getSession(), hsql, new Object[] {mbName,1,2,4});
		double amount = 0;
		if(!query.list().isEmpty() && query.list().get(0) != null){  
	        amount = Double.parseDouble(query.list().get(0).toString());  
	    } 
		return amount;		
	}
	
	public double getShareBonus(String mbName){
		String hsql = "select sum(bonusBringMoney) from MemberBonusList where mbName = ? and bonusSort = ?";
		Query query = HibernateUtils.createQuery(this.getSession(), hsql, new Object[] {mbName,3});
		double amount = 0;
		if(!query.list().isEmpty()&& query.list().get(0) != null){  
	        amount = Double.parseDouble(query.list().get(0).toString());  
	    } 
		return amount;		
	}
	
//	public List<MemberBonusList> findBonusList(String starDate,String endDate){
//		String hsql ="from MemberBonusList where bonusBringTime >= :sdate and bonusBringTime <= :edate";
//		
//	}
	
}
