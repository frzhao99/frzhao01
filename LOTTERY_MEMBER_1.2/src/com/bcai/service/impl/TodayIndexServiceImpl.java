package com.bcai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.StokenBuyDao;
import com.bcai.dao.TodayIndexDao;
import com.bcai.domain.Message;
import com.bcai.domain.TodayIndex;
import com.bcai.service.TodayIndexService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;


@Service
public class TodayIndexServiceImpl extends AbstractServiceImpl<TodayIndex, Long> implements TodayIndexService{

	@Autowired
	private TodayIndexDao todayIndexDao;
	
	
	@Override
	public GenericDaoImpl<TodayIndex, Long> getDao() {
		// TODO Auto-generated method stub
		return todayIndexDao;
	}
	
	@Override
	public List<TodayIndex> weekIndex(){
	
		List<TodayIndex> todayIndexList = todayIndexDao.indexList();
		return todayIndexList;
	}

}
