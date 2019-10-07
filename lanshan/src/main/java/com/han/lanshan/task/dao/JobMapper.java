package com.han.lanshan.task.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.task.entry.Job;

@Repository
public interface JobMapper {


	/**
	 * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteJob(List<String> ids) throws Exception;

	/**
	 * 新增
	 * 
	 * @param Job job
	 * @return String
	 * @throws Exception
	 */
	void saveJob(Job job) throws Exception;
	
	/**
	 * 更新
	 * 
	 * @param Job job
	 * @return String
	 * @throws Exception
	 */
	void updateJob(Job job) throws Exception;
	
	/**
	 * 
	  * 描述：   动态修改信息
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午5:56:04   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午5:56:04
	  * 修改备注：   
	 * @param user
	 * @throws Exception
	 * @version
	 */
	void updateActiveJob(Job job) throws Exception;

	/**
	 * 
	 * 描述： 批量保存 创建人：HYD 创建时间：2019年9月8日 下午9:26:24 修改人：HYD 修改时间：2019年9月8日 下午9:26:24
	 * 修改备注：
	 * 
	 * @param jobList
	 * @throws Exception
	 * @version
	 */
	void saveJobList(List<Job> jobList) throws Exception;

	/**
	 * 列表
	 * 
	 * @param Job job
	 * @return List<Job>
	 * @throws Exception
	 */
	List<Job> findJobList(Job job, Page page) throws Exception;
	
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
	 * 根据ID查找
	 * 
	 * @param id
	 * @return User
	 * @throws Exception
	 */
	Job findJobById(String id) throws Exception;

}