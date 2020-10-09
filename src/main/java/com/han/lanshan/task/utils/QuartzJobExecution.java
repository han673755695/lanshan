package com.han.lanshan.task.utils;

import org.quartz.JobExecutionContext;

import com.han.lanshan.task.entry.Job;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob {
	@Override
	protected void doExecute(JobExecutionContext context, Job job) throws Exception {
		JobInvokeUtil.invokeMethod(job);
	}
}
