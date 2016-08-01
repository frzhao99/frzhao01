package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BuyDebrisRecordDao;
import com.bcai.domain.BuyDebrisRecord;
import com.bcai.service.BuyDebrisRecordService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class BuyDebrisRecordImpl extends AbstractServiceImpl<BuyDebrisRecord, Long> implements BuyDebrisRecordService   {

	@Autowired
	private BuyDebrisRecordDao buyDebrisRecordDao;
	
	@Override
	public GenericDaoImpl<BuyDebrisRecord, Long> getDao() {		
		return buyDebrisRecordDao;
	}

}
