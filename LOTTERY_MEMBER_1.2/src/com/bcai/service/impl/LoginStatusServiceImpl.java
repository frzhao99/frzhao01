package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.LoginStatusDao;
import com.bcai.domain.LoginStatus;
import com.bcai.service.LoginStatusService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service("loginStatusDaoServiceImpl")
public class LoginStatusServiceImpl extends AbstractServiceImpl<LoginStatus,Long> implements LoginStatusService  {

	@Autowired
	private LoginStatusDao loginStatusDao;
	
	@Override
	public GenericDaoImpl<LoginStatus, Long> getDao() {		
		return loginStatusDao;
	}

}
