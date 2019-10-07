package com.han.lanshan.task.service;

import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.service.IBaseService;
import com.han.lanshan.task.entry.JobLog;


public interface IJobLogService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteJobLog(List ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param JobLog jobLog
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateJobLog(JobLog jobLog, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param jobLogList
	 * @throws Exception
	 * @version
	 */
	void saveJobLogList(List<JobLog> jobLogList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param jobLog
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findJobLogCount(JobLog jobLog, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param JobLog jobLog
	 * @return List<JobLog>
	 * @throws Exception
	 */
	List<JobLog> findJobLogList(JobLog jobLog, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return JobLog
	 * @throws Exception
	 */
	JobLog findJobLogById(String id) throws Exception;
    
}
