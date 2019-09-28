package com.han.lanshan.system.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 分页对象
 * 
 * @author my
 *
 */
public class Page {

	private static String DEFAULT_CURRENTPAGE = "1";
	private static String DEFAULT_PAGESIZE = "15";
	private static String DEFAULT_ORDERBY = "id desc";

	// 当前页
	private int currentPage = 1;
	// 每页数量
	private int pageSize = 15;
	// 总页数
	private Integer pageCount;
	// 总条目数
	private Integer totalCount;
	// 起始位置
	private Integer current;
	// 是否分页,默认分页
	private String isPage = "true";
	// 排序
	private String orderBy;

	public Page() {

	}

	public Page(int currentPage, int pageSize, String orderBy) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.orderBy = orderBy;
		this.current = (currentPage - 1) * pageSize;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 根据request中获取一个page对象
	 * 
	 * @param request
	 * @return
	 */
	public static Page getPage(HttpServletRequest request) {
		String _currentPage = request.getParameter("currentPage");
		String _pageSize = request.getParameter("pageSize");
		String _orderBy = request.getParameter("orderBy");
		if (StringUtils.isEmpty(_currentPage)) {
			_currentPage = DEFAULT_CURRENTPAGE;
		}
		if (StringUtils.isEmpty(_pageSize)) {
			_pageSize = DEFAULT_PAGESIZE;
		}
		if (!StringUtils.isEmpty(_orderBy)) {
			_orderBy = DEFAULT_ORDERBY;
		}

		Page page = new Page(Integer.parseInt(_currentPage), Integer.parseInt(_pageSize), DEFAULT_ORDERBY);
		return page;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", pageCount=" + pageCount
				+ ", totalCount=" + totalCount + ", current=" + current + ", isPage=" + isPage + "]";
	}

}
