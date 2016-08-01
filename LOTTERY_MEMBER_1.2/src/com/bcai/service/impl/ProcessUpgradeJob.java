package com.bcai.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.web.context.ContextLoader;

import com.bcai.domain.MemberWalletList;
import com.bcai.service.MemberWalletListService;
import com.bcai.service.MemberWalletService;
import com.bcai.service.StokenDealRecordService;

public class ProcessUpgradeJob implements StatefulJob {
	

	@Override
	public void execute(JobExecutionContext map) throws JobExecutionException {
		StokenDealRecordService stokenDealRecordService = (StokenDealRecordService) ContextLoader
				.getCurrentWebApplicationContext().getBean(
						"stokenDealRecordServiceImpl");
       
		 boolean isdwh = true;
		 do{
		    isdwh = stokenDealRecordService.dealStoken();
		 }while(isdwh);
		

		 
		
	}

	

}
