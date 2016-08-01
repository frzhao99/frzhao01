package com.bcai.service.impl;

import java.text.ParseException;

import javax.servlet.ServletContextEvent;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.bcai.service.impl.ProcessBonusJob;
import com.bcai.service.impl.ProcessEpJob;
import com.bcai.service.impl.ProcessMillJob;
import com.bcai.service.impl.ProcessSynMillJob;
import com.bcai.service.impl.ProcessUpgradeJob;

public class StmhcapListener extends ContextLoaderListener {
	

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextInitialized(event);
		initialQuartzs();		
	}

	private void initialQuartzs() {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		System.out.println("initialQuartzs");
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			initialFreshManGiftQuartz(scheduler);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initialFreshManGiftQuartz(Scheduler scheduler) {
		// 引进作业程序
		JobDetail jobDetail = new JobDetail("jobDetail-s1", "jobDetailGroup-s1", ProcessUpgradeJob.class);
		CronTrigger trigger = new CronTrigger(" Income Report ", " Report Generation ");
		
		JobDetail bonusjobDetail = new JobDetail("bonusDetail-s1", "bonusDetailGroup-s1", ProcessBonusJob.class);
		CronTrigger bonustrigger = new CronTrigger("bonus Income Report ", "bonus Report Generation ");
		
		JobDetail milljobDetail = new JobDetail("milljobDetail-s1", "milljobDetailGroup-s1", ProcessMillJob.class);
		CronTrigger milltrigger = new CronTrigger("mill Income Report ", "mill Report Generation ");
		
		JobDetail epjobDetail = new JobDetail("epjobDetail-s1", "epjobDetailGroup-s1", ProcessEpJob.class);
		CronTrigger eptrigger = new CronTrigger("ep Income Report ", "ep Report Generation ");
		
		JobDetail synMilljobDetail = new JobDetail("synMilljobDetail-s1", "synMilljobDetailGroup-s1", ProcessSynMillJob.class);
		CronTrigger synMilltrigger = new CronTrigger("synMill Income Report ", "synMill Report Generation ");
		
		/**//* 每1分钟执行一次 */
		try {
			trigger.setCronExpression("1 0/1 * * * ?");
			bonustrigger.setCronExpression("45 0/5 * * * ?");
			milltrigger.setCronExpression("3 0/5 1-20 * * ?");
			eptrigger.setCronExpression("35 2 0/1 * * ?");
			synMilltrigger.setCronExpression("28 0/5 4-7 * * ?");
			
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.scheduleJob(bonusjobDetail, bonustrigger);
			scheduler.scheduleJob(milljobDetail, milltrigger);
			scheduler.scheduleJob(epjobDetail,eptrigger);
			scheduler.scheduleJob(synMilljobDetail,synMilltrigger);
			scheduler.start();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}

