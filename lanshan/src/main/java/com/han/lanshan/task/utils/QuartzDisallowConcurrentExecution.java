package com.han.lanshan.task.utils;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

import com.han.lanshan.task.entry.Job;

/**
 * 定时任务处理（禁止并发执行）
 * 
 * @author ruoyi
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
	@Override
	protected void doExecute(JobExecutionContext context, Job job) throws Exception {
		JobInvokeUtil.invokeMethod(job);
	}
}
