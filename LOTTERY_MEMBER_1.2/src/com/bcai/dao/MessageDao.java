package com.bcai.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class MessageDao  extends GenericDaoImpl<Message,Long>   {

	
	public int getNewCount(String mbName){
		
		String rmSQL = "select count(*) from Message where mbName = '"+mbName+"' and status = 1";
	
		Query query = this.getSession().createQuery(rmSQL);
		
		return ((Long) query.uniqueResult()).intValue();
	
	}
	
	public void refreshMessage(String mbName,String id){
		
		String hql = "update Message set status = 2 where id = :id";
		Query query = this.getSession().createQuery(hql);
		query.setInteger("id", Integer.valueOf(id));
		query.executeUpdate();
		
	}
}
