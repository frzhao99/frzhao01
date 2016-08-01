package com.bcai.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.MemberStoken;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class MemberStokenDao extends GenericDaoImpl<MemberStoken, Long> {
	public double countMill(){
		List topData = this.find("select sum(mill) from member_stoken");
		double topCount = Double.valueOf(topData.get(0).toString());
		return topCount;
	};
	public double countDebris(){
		List topData = this.find("select sum(token_remain) from member_stoken");
		List tra_debris_remain = this.find("select sum(tra_debris_remain) from member_stoken");
		double topCount = Double.valueOf(topData.get(0).toString());
		double debrCount = Double.valueOf(tra_debris_remain.get(0).toString());
		return topCount+debrCount;
	};
	
	public List<MemberStoken> findMemberStoken(){
		String rmSQL = "from MemberStoken where tokenRemark = :tokenRemark";
		Query query = this.getSession().createQuery(rmSQL).setString("tokenRemark","no");
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<MemberStoken> findMemberStokenBySparce(){
		String rmSQL = "from MemberStoken where tokenSpare = :tokenSpare";
		Query query = this.getSession().createQuery(rmSQL).setString("tokenSpare","no");
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<MemberStoken> findMemberStokenByTokenRemin(){
		String rmSQL = "from MemberStoken where tokenRemain >= :tokenRemain";
		Query query = this.getSession().createQuery(rmSQL).setInteger("tokenRemain",10);
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<MemberStoken> findMemberStokenByMill(){
		String rmSQL = "from MemberStoken where isMin = :isMin";
		Query query = this.getSession().createQuery(rmSQL).setInteger("isMin",0);
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	public int reIsMill(int va){
		String upHQL ="update MemberStoken set isMin = ?";
		Query query = this.getSession().createQuery(upHQL);
	    query.setInteger(0, va);
	    return query.executeUpdate();
	}
	
	
	public List<MemberStoken> revoDebris(){
		String rmSQL = "from MemberStoken where tokenRemarkab = :tokenRemarkab";
		Query query = this.getSession().createQuery(rmSQL);
		query.setString("tokenRemarkab","dj");
        query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	
}
