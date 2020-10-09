package com.han.lanshan.system.entry;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
	/**
	 * @version
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private Integer age;
	private String password;
	private String email;
	private Date createDate;
	private Date updateDate;
	private String hobby;
	private String status;
	private String account;
	private String userType;
	private String sex;
	private String bak1;
	private String bak2;
	private String bak3;
	/**
	 * 角色ids
	 */
	private String roleIds;
	/**
	 * 角色名称
	 */
	private String roleNames;
	/**
	 * 角色集合
	 */
	private List<Role> roleList;

	/**
	 * 部门ids
	 */
	private String orgIds;
	/**
	 * 部门名称
	 */
	private String orgNames;
	/**
	 * 部门集合
	 */
	private List<Org> orgList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public List<Org> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", password=" + password + ", email=" + email
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", hobby=" + hobby + ", status="
				+ status + ", account=" + account + ", userType=" + userType + ", sex=" + sex + ", bak1=" + bak1
				+ ", bak2=" + bak2 + ", bak3=" + bak3 + ", roleIds=" + roleIds + ", roleNames=" + roleNames
				+ ", orgIds=" + orgIds + ", orgNames=" + orgNames + "]";
	}

}