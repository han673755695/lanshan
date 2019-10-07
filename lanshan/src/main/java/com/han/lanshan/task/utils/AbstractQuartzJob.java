package com.han.lanshan.task.utils;

import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.han.lanshan.system.utils.BeanUtils;
import com.han.lanshan.task.entry.Job;
import com.han.lanshan.task.entry.JobLog;

/**
 * 抽象quartz调用
 *
 * @author ruoyi
 */
public abstract class AbstractQuartzJob implements org.quartz.Job {
	private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

	/**
	 * 线程本地变量
	 */
	private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Job job = new Job();
		BeanUtils.copyBeanProp(job, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
		try {
			before(context, job);
			if (job != null) {
				doExecute(context, job);
			}
			after(context, job, null);
		} catch (Exception e) {
			log.error("任务执行异常  - ：", e);
			after(context, job, e);
		}
	}

	/**
	 * 执行前
	 *
	 * @param context 工作执行上下文对象
	 * @param sysJob  系统计划任务
	 */
	protected void before(JobExecutionContext context, Job job) {
		threadLocal.set(new Date());
	}

	/**
	 * 执行后
	 *
	 * @param context        工作执行上下文对象
	 * @param sysScheduleJob 系统计划任务
	 */
	protected void after(JobExecutionContext context, Job job, Exception e) {
		Date startTime = threadLocal.get();
		threadLocal.remove();

		final JobLog jobLog = new JobLog();
		jobLog.setJobName(job.getJobName());
		jobLog.setJobGroup(job.getJobGroup());
		jobLog.setInvokeTarget(job.getInvokeTarget());

	}

	/**
	 * 执行方法，由子类重载
	 *
	 * @param context 工作执行上下文对象
	 * @param job     系统计划任务
	 * @throws Exception 执行过程中的异常
	 */
	protected abstract void doExecute(JobExecutionContext context, Job job) throws Exception;
}