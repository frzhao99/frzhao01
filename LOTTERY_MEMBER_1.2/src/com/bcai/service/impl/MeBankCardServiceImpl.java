package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcai.dao.MeBankCardDao;
import com.bcai.domain.MeBankCard;
import com.bcai.service.MeBankCardService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

public class MeBankCardServiceImpl extends AbstractServiceImpl<MeBankCard,Long> implements MeBankCardService{

	@Autowired
	private MeBankCardDao meBankCardDao;
	
	@Override
	public GenericDaoImpl<MeBankCard, Long> getDao() {		
		return meBankCardDao;
	}

}
