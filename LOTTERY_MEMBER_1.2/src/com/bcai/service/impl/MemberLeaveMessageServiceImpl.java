package com.bcai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberLeaveMessageDao;
import com.bcai.domain.MemberLeaveMessage;
import com.bcai.service.MemberLeaveMessageService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class MemberLeaveMessageServiceImpl extends AbstractServiceImpl<MemberLeaveMessage, Long> implements MemberLeaveMessageService{
	
    @Autowired
	private MemberLeaveMessageDao memberLeaveMessageDao;
    
   

	@Override
	public GenericDaoImpl<MemberLeaveMessage, Long> getDao() {
		return memberLeaveMessageDao;
	}



	@Override
	public List<MemberLeaveMessage> findLeaveMessages(String mbName) {
		String finSql = "from MemberLeaveMessage where userName = ? and isReply = ?";
		List<MemberLeaveMessage> memberLeaveMLists =  memberLeaveMessageDao.findList(finSql, new Object[]{mbName,0});
		return memberLeaveMLists;
	}

	
    


}
