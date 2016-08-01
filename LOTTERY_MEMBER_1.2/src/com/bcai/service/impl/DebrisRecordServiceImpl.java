package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.DebrisRecordDao;
import com.bcai.domain.DebrisRecord;
import com.bcai.service.DebrisRecordService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class DebrisRecordServiceImpl extends AbstractServiceImpl<DebrisRecord, Long> implements DebrisRecordService  {

	@Autowired
	private DebrisRecordDao debrisRecordDao;
	
	@Override
	public GenericDaoImpl<DebrisRecord, Long> getDao() {
		// TODO Auto-generated method stub
		return debrisRecordDao;
	}

}
