package com.han.lanshan.task.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.service.impl.BaseServiceImpl;
import com.han.lanshan.system.utils.UUIDUtils;
import com.han.lanshan.task.dao.JobLogMapper;
import com.han.lanshan.task.entry.JobLog;
import com.han.lanshan.task.service.IJobLogService;


@Service
@Transactional
public class JobLogServiceImpl extends BaseServiceImpl implements IJobLogService {

	@Autowired
	private JobLogMapper jobLogDao;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findJobLogList((JobLog) obj, page);
	}
	

	@Override
	public void deleteJobLog(List ids) throws Exception {
		try {
		
			if (CollectionUtils.isNotEmpty(ids)) {
				jobLogDao.deleteJobLog(ids);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateJobLog(JobLog jobLog, boolean all) throws Exception {
		try {
			if (StringUtils.isBlank(jobLog.getId())) {
				String uuid = UUIDUtils.getUUID();
				jobLog.setId(uuid);
				jobLogDao.saveJobLog(jobLog);
			}else {
				if (all) {
					//当为true时,不更新字段为null的数据
					jobLogDao.updateActiveJobLog(jobLog);
				}else {
					jobLogDao.updateJobLog(jobLog);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveJobLogList(List<JobLog> jobLogList) throws Exception {
		try {
			for (JobLog jobLog : jobLogList) {
				jobLogDao.saveJobLog(jobLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findJobLogCount(JobLog jobLog, Page page) throws Exception {
		try {
			return jobLogDao.findJobLogCount(jobLog, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<JobLog> findJobLogList(JobLog jobLog, Page page) throws Exception {
		try {
			return jobLogDao.findJobLogList(jobLog, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public JobLog findJobLogById(String id) throws Exception {
		try {
			return jobLogDao.findJobLogById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
