package com.bcai.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.EpSell;
import com.bcai.domain.Member;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.Notice;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class MemberWalletDao extends GenericDaoImpl<MemberWallet, Long> {
	public double countMemberBmBalance(){
		List topData = this.find("select sum(wallet) from member_wallet");
		double topCount = Double.valueOf(topData.get(0).toString());
		return topCount;
	};

	public MemberWallet findMemberWalletById(String mbName){
		 String hql = "from MemberWallet where mbName = :mbName";  
		 Query query = this.getSession().createQuery(hql).setString("mbName", mbName);  
		 query.setLockOptions(LockOptions.UPGRADE); 
		 List<MemberWallet> mws = query.list();
		 if(mws !=null && mws.size() == 1){
			return mws.get(0);
		 }
		return null;

	};
	
	public List<MemberWallet> findMemberWall(){
		String rmSQL = "from MemberWallet where ruby = :ruby";
		Query query = this.getSession().createQuery(rmSQL).setDouble("ruby", 1);
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<MemberWallet> frozen(){
		String rmSQL = "from MemberWallet where remarks = :remarks";
		Query query = this.getSession().createQuery(rmSQL);
		query.setString("remarks","dj");
        query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
}
