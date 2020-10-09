package com.han.lanshan.task.entry;

import java.io.Serializable;
import java.util.Date;

public class JobLog implements Serializable {
	/**
	 * @version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @version 任务日志ID
	 */
	private String id;
	/**
	 * @version 任务名称
	 */
	private String jobName;
	/**
	 * @version 任务组名
	 */
	private String jobGroup;
	/**
	 * @version 调用目标字符串
	 */
	private String invokeTarget;
	/**
	 * @version 日志信息
	 */
	private String jobMessage;
	/**
	 * @version 执行状态（0正常 1失败）
	 */
	private String active;
	/**
	 * @version 异常信息
	 */
	private String exceptionInfo;
	/**
	 * @version 创建时间
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getInvokeTarget() {
		return invokeTarget;
	}

	public void setInvokeTarget(String invokeTarget) {
		this.invokeTarget = invokeTarget;
	}

	public String getJobMessage() {
		return jobMessage;
	}

	public void setJobMessage(String jobMessage) {
		this.jobMessage = jobMessage;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "JobLog [id=" + id + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", invokeTarget=" + invokeTarget
				+ ", jobMessage=" + jobMessage + ", active=" + active + ", exceptionInfo=" + exceptionInfo
				+ ", createTime=" + createTime + "]";
	}

}