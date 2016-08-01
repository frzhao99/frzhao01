package com.bcai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.CreditListDao;
import com.bcai.domain.CreditList;
import com.bcai.domain.Message;
import com.bcai.service.CreditListService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class CreditListServiceImpl extends AbstractServiceImpl<CreditList, Long> implements CreditListService{

	@Autowired
	CreditListDao creditListDao;
	
	@Override
	public GenericDaoImpl<CreditList, Long> getDao() {
		// TODO Auto-generated method stub
		return creditListDao;
	}
	


}
