package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BonusByDateDao;
import com.bcai.domain.BonusByDate;
import com.bcai.service.BonusByDateService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class BonusByDateServiceImpl extends AbstractServiceImpl<BonusByDate, Long> implements BonusByDateService {

	@Autowired
	private BonusByDateDao bonusByDateDao;
	
	@Override
	public GenericDaoImpl<BonusByDate, Long> getDao() {
		// TODO Auto-generated method stub
		return bonusByDateDao;
	}

}
