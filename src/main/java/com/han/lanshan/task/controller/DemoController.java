package com.han.lanshan.task.controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.han.lanshan.system.common.ReturnData;
import com.han.lanshan.system.controller.BaseController;
import com.han.lanshan.task.entry.DoCleanObe;

@Controller
@RequestMapping("/demoController")
public class DemoController extends BaseController {
	
	@Autowired
	private Scheduler scheduler;
	
	@RequestMapping("/start")
	@ResponseBody
	public ReturnData start() {
		System.out.println("start...");
		
//		JobDetail jobDetail = JobBuilder.newJob(QuartzInitVopVosFactory.class) .withIdentity(job.getJobName(), job.getJobGroup()).build(); jobDetail.getJobDataMap().put("job", job);
		//构建一个任务详情
		JobDetail jobDetail = JobBuilder.newJob(DoCleanObe.class).withIdentity("测试", "第一组").build();
		//cron表达式
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
		
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("测试", "第一组")
                .withSchedule(cronScheduleBuilder).build();
		
		jobDetail.getJobDataMap().put("job", "lkasflsahdfk");
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ReturnData(ReturnData.SUCCESS);
	}
}
