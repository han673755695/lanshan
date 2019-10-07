package com.han.lanshan.task.service;

import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.service.IBaseService;
import com.han.lanshan.task.entry.Job;
import com.han.lanshan.task.utils.CronUtils;


public interface IJobService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteJob(List ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param Job job
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateJob(Job job, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param jobList
	 * @throws Exception
	 * @version
	 */
	void saveJobList(List<Job> jobList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param job
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findJobCount(Job job, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param Job job
	 * @return List<Job>
	 * @throws Exception
	 */
	List<Job> findJobList(Job job, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return Job
	 * @throws Exception
	 */
	Job findJobById(String id) throws Exception;
    
	/**
	 * 
	  * 描述：   执行任务
	  * 创建人：HYD
	  * 创建时间：2019年10月7日 下午6:04:20   
	  * 修改人：HYD
	  * 修改时间：2019年10月7日 下午6:04:20
	  * 修改备注：   
	 * @throws Exception
	 * @version
	 */
	void runJob(Job job) throws Exception;
	
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
	public boolean checkCronExpressionIsValid(String cronExpression);
}
