package com.han.lanshan.system.entry;

import java.io.Serializable;
import java.util.Date;

public class DicData implements Serializable {
	/**
	 * @version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @version
	 * 
	 */
	private String id;
	/**
	 * @version 名称
	 */
	private String name;
	/**
	 * @version 编码
	 */
	private String code;
	/**
	 * @version 排序
	 */
	private Integer sort;
	/**
	 * @version 描述
	 */
	private String remark;
	/**
	 * @version 是否有效(0否,1是)
	 */
	private Integer active;
	/**
	 * @version 类型
	 */
	private String typekey;
	/**
	 * @version
	 * 
	 */
	private String bak1;
	/**
	 * @version
	 * 
	 */
	private String bak2;
	/**
	 * @version
	 * 
	 */
	private String bak3;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getTypekey() {
		return typekey;
	}

	public void setTypekey(String typekey) {
		this.typekey = typekey;
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
		return "DicData [id=" + id + ", name=" + name + ", code=" + code + ", sort=" + sort + ", remark=" + remark
				+ ", active=" + active + ", typekey=" + typekey + ", bak1=" + bak1 + ", bak2=" + bak2 + ", bak3=" + bak3
				+ "]";
	}

}