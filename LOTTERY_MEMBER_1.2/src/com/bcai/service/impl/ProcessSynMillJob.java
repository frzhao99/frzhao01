package com.bcai.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.web.context.ContextLoader;

import com.bcai.service.MemberStokenService;

/**
 * 用于处理合成矿机
 * @author Frhao
 *
 */
public class ProcessSynMillJob implements StatefulJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MemberStokenService memberstokenService = (MemberStokenService) ContextLoader.getCurrentWebApplicationContext().getBean("memberStokenServiceImpl");
		boolean isWh = true;
		do{
			isWh = memberstokenService.synMill();
		}while(isWh );
		
	}

}
