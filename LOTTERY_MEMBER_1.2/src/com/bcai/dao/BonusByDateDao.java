package com.bcai.dao;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.BonusByDate;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class BonusByDateDao extends GenericDaoImpl<BonusByDate, Long>  {
	public BonusByDate getBonusByDate(String mbName,String ordDate){
		String byDSQL = "from BonusByDate where mbName = ? and stbringDate = ?";
		Query query = this.getSession().createQuery(byDSQL);
	    query.setLockOptions(LockOptions.UPGRADE); 
		query.setString(0, mbName);
		query.setString(1, ordDate);
		query.setMaxResults(1);
		if(query.list().size()>0 && query.list().get(0) != null){
			return (BonusByDate) query.list().get(0);
		}else{
			return null;
		}
//		Criterion criterion = Restrictions.eq("mbName", mbName);
//		Criterion mcCriterion = Restrictions.eq("stbringDate", ordDate);
//		return (BonusByDate) HibernateUtils.createCriteria(this.getSession(), entityClass, criterion,mcCriterion).uniqueResult();		
	
	};
}
