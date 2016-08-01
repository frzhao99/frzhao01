package com.bcai.service.impl;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;

import com.bcai.service.MemberStokenService;
import com.bcai.service.MillRecordService;

public class ProcessMillJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {		// TODO Auto-generated method stub
		
		MemberStokenService memberStokenService = (MemberStokenService) ContextLoader.getCurrentWebApplicationContext().getBean("memberStokenServiceImpl");
		boolean isWh = true;
		do{
			isWh = memberStokenService.newMill();
		}while(isWh );
	}

}
