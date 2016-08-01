package com.bcai.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.BuyDebrisRecord;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class BuyDebrisRecordDao extends GenericDaoImpl<BuyDebrisRecord, Long>  {
	public List<BuyDebrisRecord> findBuyDeb(int max,String mbName){
		String rmSQL = "from BuyDebrisRecord where mbName=:mbName";
		Query query = this.getSession().createQuery(rmSQL).setString("mbName", mbName);
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(max);
		return query.list();
	}
}
