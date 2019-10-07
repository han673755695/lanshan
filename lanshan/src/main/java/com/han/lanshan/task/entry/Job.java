package com.han.lanshan.task.entry;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {
	/**
	 * @version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @version 任务ID
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
	 * @version cron执行表达式
	 */
	private String cronExpression;
	/**
	 * @version 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 */
	private String misfirePolicy;
	/**
	 * @version 是否并发执行（0允许 1禁止）
	 */
	private String concurrent;
	/**
	 * @version 状态（0正常 1暂停）
	 */
	private String active;
	/**
	 * @version 创建者
	 */
	private String createBy;
	/**
	 * @version 创建时间
	 */
	private Date createTime;
	/**
	 * @version 更新者
	 */
	private String updateBy;
	/**
	 * @version 更新时间
	 */
	private Date updateTime;
	/**
	 * @version 备注信息
	 */
	private String remark;

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

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getMisfirePolicy() {
		return misfirePolicy;
	}

	public void setMisfirePolicy(String misfirePolicy) {
		this.misfirePolicy = misfirePolicy;
	}

	public String getConcurrent() {
		return concurrent;
	}

	public void setConcurrent(String concurrent) {
		this.concurrent = concurrent;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", invokeTarget=" + invokeTarget
				+ ", cronExpression=" + cronExpression + ", misfirePolicy=" + misfirePolicy + ", concurrent="
				+ concurrent + ", active=" + active + ", createBy=" + createBy + ", createTime=" + createTime
				+ ", updateBy=" + updateBy + ", updateTime=" + updateTime + ", remark=" + remark + "]";
	}

}