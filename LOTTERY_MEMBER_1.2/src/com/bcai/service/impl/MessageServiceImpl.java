package com.bcai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberDao;
import com.bcai.dao.MessageDao;
import com.bcai.domain.Member;
import com.bcai.domain.Message;
import com.bcai.service.MessageService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;


@Service
public class MessageServiceImpl extends AbstractServiceImpl<Message, Long> implements MessageService
{

	@Autowired
	private MessageDao messageDao;
	
	@Override
	public GenericDaoImpl<Message, Long> getDao() {
		// TODO Auto-generated method stub
		return messageDao;
	}

	public List<Message> getList(String username){
		
		String rmSQL = "from Message where mbName = ? and status = 1";
		List<Message> members = messageDao.findList(rmSQL,
				new Object[] { username });
		return members;
		
	}
	

	public int getNewCount(String mbName){
		
		return messageDao.getNewCount(mbName);
		
	}
	
	public void refreshMessage(String mbName,String id){
		
		messageDao.refreshMessage(mbName,id);
		
	}

}
