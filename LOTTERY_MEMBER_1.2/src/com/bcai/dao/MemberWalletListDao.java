package com.bcai.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcai.domain.MemberWalletList;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.HibernateUtils;

@Repository
public class MemberWalletListDao extends GenericDaoImpl<MemberWalletList, Long> {

	public Page<MemberWalletList> find(Page<MemberWalletList> pageData,String mbName) {
	    Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);		
	    criteria.add(Restrictions.eq("mbName", mbName));
		HibernateUtils.setParameter(criteria, pageData);		
		pageData.setResult(criteria.list());		
		return pageData;	
	}
	
	public List<MemberWalletList> findMemberWalletListByCe(){
        String rmSQL = "from MemberWalletList where id >= :minId and id <= :maxId and type = :type and description = :description";
		
		Query query = this.getSession().createQuery(rmSQL).setInteger("minId", 4161994)
				.setInteger("maxId", 4164437).setInteger("type", 0).setString("description", "荣誉奖金");
		
		//query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<MemberWalletList> findMemberWalletListByMbName(String mbName){
        String rmSQL = "from MemberWalletList where id >= :minId and id <= :maxId and type = :type and description = :description and mbName = :mbName";
		
		Query query = this.getSession().createQuery(rmSQL).setInteger("minId", 4161994)
				.setInteger("maxId", 4164437).setInteger("type", 0).setString("description", "荣誉奖金").setString("mbName", mbName);
		
		//query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	
	
}
