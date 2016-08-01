package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.PeepListDao;
import com.bcai.domain.PeepList;
import com.bcai.service.PeepListService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class PeepListServiceImpl extends AbstractServiceImpl<PeepList, Long> implements PeepListService  {

	@Autowired
	private PeepListDao peepListDao;
	
	@Override
	public GenericDaoImpl<PeepList, Long> getDao() {
		
		return peepListDao;
	}

}
