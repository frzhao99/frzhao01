package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.HtcHistoryDao;
import com.bcai.domain.HtcHistory;
import com.bcai.service.HtcHistoryService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class HtcHistoryServiceImpl extends AbstractServiceImpl<HtcHistory,Long> implements HtcHistoryService  {

	@Autowired
	private HtcHistoryDao htcHistoryDao;
	
	@Override
	public GenericDaoImpl<HtcHistory, Long> getDao() {		
		return htcHistoryDao;
	}

}
