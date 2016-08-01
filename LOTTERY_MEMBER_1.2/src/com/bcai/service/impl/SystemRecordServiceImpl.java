package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.SystemRecordDao;
import com.bcai.domain.SystemRecord;
import com.bcai.service.SystemRecordService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class SystemRecordServiceImpl extends AbstractServiceImpl<SystemRecord, Long> implements SystemRecordService {

	@Autowired
	private SystemRecordDao systemRecordDao;
	
	@Override
	public GenericDaoImpl<SystemRecord, Long> getDao() {		
		return systemRecordDao;
	}

}
