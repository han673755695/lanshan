package com.han.lanshan.system.common;

import java.util.Map;

/**
 * 返回的数据结构
 * 
 * @author my
 *
 */
public class ReturnData {

	public static final String DATAKEY = "returndata";

	public static String SUCCESS = "success";
	public static String ERROR = "error";

	// 存放数据map
	private Map<String, Object> map;
	// 存放数据
	private Object data;
	// 返回状态
	private String status;
	// 返回分页信息
	private Page page;
	// 请求参数
	private Object queryParams;
	// 返回信息
	private String message = "返回成功";

	public ReturnData() {

	}

	public Object getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Object queryParams) {
		this.queryParams = queryParams;
	}

	public ReturnData(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 获取返回成功实体类
	 * 
	 * @return
	 */
	public static ReturnData getSuccess() {
		return new ReturnData(SUCCESS);
	}

	/**
	 * 获取返回失败实体类
	 * 
	 * @return
	 */
	public static ReturnData getError() {
		return new ReturnData(ERROR);
	}

	@Override
	public String toString() {
		return "ResultData [mapData=" + map + ", data=" + data + ", status=" + status + ", page=" + page + ", message="
				+ message + "]";
	}

}
