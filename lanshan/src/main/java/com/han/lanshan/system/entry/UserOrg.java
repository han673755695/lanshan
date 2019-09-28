package com.han.lanshan.system.entry;

import java.io.Serializable;
import java.util.Date;

public class UserOrg implements Serializable {
	/**
	 * @version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @version 主键id
	 */
	private String id;
	/**
	 * @version 部门id
	 */
	private String orgId;
	/**
	 * @version 用户id
	 */
	private String userId;
	/**
	 * @version 创建人
	 */
	private String createUser;
	/**
	 * @version 创建时间
	 */
	private Date createDate;
	/**
	 * @version 修改人
	 */
	private String updateUser;
	/**
	 * @version 修改时间
	 */
	private Date updateDate;
	/**
	 * @version 是否可用(1:可用,2:不可用)
	 */
	private String active;
	/**
	 * @version bak1
	 */
	private String bak1;
	/**
	 * @version bak2
	 */
	private String bak2;
	/**
	 * @version bak3
	 */
	private String bak3;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getBak1() {
		return bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	public String getBak2() {
		return bak2;
	}

	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

	public String getBak3() {
		return bak3;
	}

	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	@Override
	public String toString() {
		return "UserOrg [id=" + id + ", orgId=" + orgId + ", userId=" + userId + ", createUser=" + createUser
				+ ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate
				+ ", active=" + active + ", bak1=" + bak1 + ", bak2=" + bak2 + ", bak3=" + bak3 + "]";
	}

}