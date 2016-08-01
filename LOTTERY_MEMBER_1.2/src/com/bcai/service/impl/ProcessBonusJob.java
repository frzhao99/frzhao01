package com.bcai.service.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.web.context.ContextLoader;

import com.bcai.service.MillRecordService;

public class ProcessBonusJob implements StatefulJob {
	
   

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MillRecordService millRecordService = (MillRecordService) ContextLoader.getCurrentWebApplicationContext().getBean("millRecordServiceImpl");
		boolean isWh = true;
		
		do{
		     isWh = millRecordService.sellBonus();
		}while(isWh );
		
	}
	


}
