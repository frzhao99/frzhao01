package com.bcai.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.PeepList;
import com.bcai.domain.StokenDealRecord;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.DateUtils;

@Repository
public class PeepListDao extends GenericDaoImpl<PeepList, Long> {
	public List<PeepList> findPeeListByMbnameAndPrId(String mbName,String prId){
		String rmSQL = "from PeepList where mbName = :mbName and prId = :prId order by prTime desc ";
		Query query = this.getSession().createQuery(rmSQL);
		query.setString("mbName", mbName);
		query.setString("prId", prId);
		query.setMaxResults(10);
		List<PeepList> stokenDealRecord = query.list();
		return stokenDealRecord;
	}
}
