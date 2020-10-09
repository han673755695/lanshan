package com.han.lanshan.system.entry;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public class Role implements Serializable {
	/**
	 * @version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @version 主键id
	 */
	private String id;
	/**
	 * @version 角色名称
	 */
	private String roleName;
	/**
	 * @version 描述
	 */
	private String bewrite;
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
	 * 角色所拥有的菜单
	 */
	private List<Menu> menuList;
	/**
	 * 角色所拥有的菜单字符串
	 */
	private String menuIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getBewrite() {
		return bewrite;
	}

	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
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

	@Transient
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	@Transient
	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", bewrite=" + bewrite + ", createUser=" + createUser
				+ ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate
				+ ", active=" + active + ", menuIds=" + menuIds + "]";
	}

}