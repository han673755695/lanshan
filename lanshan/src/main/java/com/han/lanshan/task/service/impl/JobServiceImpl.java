package com.han.lanshan.task.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.common.SessionUser;
import com.han.lanshan.system.entry.User;
import com.han.lanshan.system.service.impl.BaseServiceImpl;
import com.han.lanshan.system.utils.UUIDUtils;
import com.han.lanshan.task.dao.JobMapper;
import com.han.lanshan.task.entry.Job;
import com.han.lanshan.task.service.IJobService;
import com.han.lanshan.task.utils.CronUtils;
import com.han.lanshan.task.utils.ScheduleConstants;
import com.han.lanshan.task.utils.ScheduleUtils;

@Service
@Transactional
public class JobServiceImpl extends BaseServiceImpl implements IJobService {

	@Autowired
	private JobMapper jobDao;
	@Autowired
	private Scheduler scheduler;

	@PostConstruct
	public void init() {

		try {
			Job job = new Job();
			List<Job> findJobList = findJobList(job, null);

			if (CollectionUtils.isNotEmpty(findJobList)) {
				for (Job job2 : findJobList) {
					updateSchedulerJob(job2);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 描述： 初始化数据库中的定时任务 创建人：HYD 创建时间：2019年10月7日 下午9:10:14 修改人：HYD 修改时间：2019年10月7日
	 * 下午9:10:14 修改备注：
	 * 
	 * @param job
	 * @throws SchedulerException
	 * @version
	 */
	private void updateSchedulerJob(Job job) throws SchedulerException {
		String jobId = job.getId();
		String jobGroup = job.getJobGroup();
		// 判断是否存在
		JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
		if (scheduler.checkExists(jobKey)) {
			// 防止创建时存在数据问题 先移除，然后在执行创建操作
			scheduler.deleteJob(jobKey);
		}
		ScheduleUtils.createScheduleJob(scheduler, job);
	}

	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {

		return (List<T>) findJobList((Job) obj, page);
	}

	@Override
	public void deleteJob(List ids) throws Exception {
		try {

			if (CollectionUtils.isNotEmpty(ids)) {
				jobDao.deleteJob(ids);

				for (Object object : ids) {
					String id = (String) object;
					Job job = findJobById(id);
					scheduler.deleteJob(ScheduleUtils.getJobKey(job.getId(), job.getJobGroup()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateJob(Job job, boolean all) throws Exception {
		try {
			User user = SessionUser.getUser();
			if (StringUtils.isBlank(job.getId())) {
				String uuid = UUIDUtils.getUUID();
				job.setId(uuid);
				job.setCreateBy(user.getId());
				job.setCreateTime(new Date());
				jobDao.saveJob(job);
				ScheduleUtils.createScheduleJob(scheduler, job);
			} else {
				job.setUpdateBy(user.getId());
				job.setUpdateTime(new Date());
				if (all) {
					// 当为true时,不更新字段为null的数据
					jobDao.updateActiveJob(job);
				} else {
					jobDao.updateJob(job);
				}

				job = findJobById(job.getId());
				changeState(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 
	 * 描述： 更改定时任务的状态 创建人：HYD 创建时间：2019年10月7日 下午9:09:43 修改人：HYD 修改时间：2019年10月7日
	 * 下午9:09:43 修改备注：
	 * 
	 * @param job
	 * @version
	 */
	private void changeState(Job job) {
		try {
			String active = job.getActive();
			if (ScheduleConstants.Status.NORMAL.getValue().equals(active)) {

				scheduler.resumeJob(ScheduleUtils.getJobKey(job.getId(), job.getJobGroup()));
			} else if (ScheduleConstants.Status.PAUSE.getValue().equals(active)) {

				scheduler.pauseJob(ScheduleUtils.getJobKey(job.getId(), job.getJobGroup()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveJobList(List<Job> jobList) throws Exception {
		try {
			for (Job job : jobList) {
				jobDao.saveJob(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int findJobCount(Job job, Page page) throws Exception {
		try {
			return jobDao.findJobCount(job, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Job> findJobList(Job job, Page page) throws Exception {
		try {

			return jobDao.findJobList(job, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Job findJobById(String id) throws Exception {
		try {
			return jobDao.findJobById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void runJob(Job job) throws Exception {
		String jobId = job.getId();
		String jobGroup = job.getJobGroup();
		Job properties = findJobById(job.getId());
		// 参数
		JobDataMap dataMap = new JobDataMap();
		dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
		scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
	}

	/**
	 * 
	  * 描述：   验证Cron表达式是否正确
	  * 创建人：HYD
	  * 创建时间：2019年10月7日 下午9:34:01   
	  * 修改人：HYD
	  * 修改时间：2019年10月7日 下午9:34:01
	  * 修改备注：   
	 * @param cronExpression
	 * @return
	 * @version
	 */
	public boolean checkCronExpressionIsValid(String cronExpression) {
		return CronUtils.isValid(cronExpression);
	}

}
