package com.bcai.service;

import org.springframework.web.bind.annotation.RequestMapping;

import com.bcai.domain.StokenDealRecord;
import com.symbio.service.impl.BaseService;

@RequestMapping
public interface StokenDealRecordService extends BaseService<StokenDealRecord, Long>  {
	public boolean dealStoken();

	public void tradeBonus(double amony, String mbName);
	
	public void countEvenDate(String Date);
}
