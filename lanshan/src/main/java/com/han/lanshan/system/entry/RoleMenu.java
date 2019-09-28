package com.han.lanshan.system.entry;

import java.io.Serializable;
import java.util.Date;

public class RoleMenu implements Serializable {
	/**
	 * @version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @version 主键id
	 */
	private String id;
	/**
	 * @version 角色id
	 */
	private String roleId;
	/**
	 * @version 菜单id
	 */
	private String menuId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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

	@Override
	public String toString() {
		return "RoleMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + ", createUser=" + createUser
				+ ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate
				+ ", active=" + active + "]";
	}

}