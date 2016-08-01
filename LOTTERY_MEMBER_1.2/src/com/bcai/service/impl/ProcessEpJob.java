package com.bcai.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.web.context.ContextLoader;

import com.bcai.service.EpSellService;

public class ProcessEpJob implements StatefulJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		

		EpSellService epSellService = (EpSellService) ContextLoader.getCurrentWebApplicationContext().getBean("epSellServiceImpl");
		boolean isWh = true;
		do{
			isWh = epSellService.checkEp();
		}while(isWh );
		
		boolean ischeck = true;
		do{
			ischeck = epSellService.checkConfPayEp();
		}while(ischeck );
		
	    if(!isWh && !ischeck){
	    	epSellService.updateCheckEp();
	    }
	}

}
