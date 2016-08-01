package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberActiveListDao;
import com.bcai.domain.MemberActiveList;
import com.bcai.service.MemberActiveListService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class MemberActiveListServiceImpl extends AbstractServiceImpl<MemberActiveList, Long> implements MemberActiveListService {
   
	@Autowired
	private MemberActiveListDao memberActiveListDao;
	
	@Override
	public GenericDaoImpl<MemberActiveList, Long> getDao() {
		// TODO Auto-generated method stub
		return memberActiveListDao;
	}

}
