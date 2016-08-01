package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BlkAmountDao;
import com.bcai.domain.BlkAmount;
import com.bcai.service.BlkAmountService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class BlkAmountServiceImpl extends AbstractServiceImpl<BlkAmount, Long> implements BlkAmountService {

	@Autowired
	private BlkAmountDao blkAmountDao;
	
	@Override
	public GenericDaoImpl<BlkAmount, Long> getDao() {		
		return blkAmountDao;
	}

}
