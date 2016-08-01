package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.RelationMemberDao;
import com.bcai.domain.RelationMember;
import com.bcai.service.RelationMemberService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class RelationMemberServiceImpl extends AbstractServiceImpl<RelationMember, Long> implements RelationMemberService{

	@Autowired
	private RelationMemberDao relationMemberDao;
	
	@Override
	public GenericDaoImpl<RelationMember, Long> getDao() {		
		return relationMemberDao;
	}

}
