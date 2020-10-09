package com.han.lanshan.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.han.lanshan.system.common.Page;

/**
 * 
 * 描述： 基础的controller类 创建人：HYD 创建时间：2019年9月8日 下午12:36:08 修改人：HYD 修改时间：2019年9月8日
 * 下午12:36:08 修改备注：
 * 
 * @version
 */
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 获取分页 page 对象
	 * 
	 * @param request
	 * @return
	 */
	public Page startPage(HttpServletRequest request) {
		// ==获取分页信息
		String _currentPage = request.getParameter("currentPage");
		String _pageSize = request.getParameter("pageSize");
		String orderBy = request.getParameter("orderBy");
		String sort = request.getParameter("sort");
		if (StringUtils.isEmpty(_currentPage)) {
			_currentPage = "1";
		}
		if (StringUtils.isEmpty(_pageSize)) {
			_pageSize = "15";
		}
		if (StringUtils.isEmpty(orderBy)) {
			orderBy = "id";
		}
		if (StringUtils.isEmpty(sort)) {
			sort = "desc";
		}
		
		return new Page();
	}

	/**
	 * 公共下载方法
	 * 
	 * @param response
	 * @param file     下载的文件
	 * @param fileName 下载时显示的文件名
	 * @return
	 * @throws Exception
	 */
	public HttpServletResponse downFile(HttpServletResponse response, File file, String fileName, boolean delFile)
			throws Exception {
		response.setContentType("application/x-download");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		OutputStream out = null;
		InputStream in = null;
		// 下面一步不可少
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.addHeader("Content-disposition", "attachment;filename=" + fileName);// 设定输出文件头

		try {
			out = response.getOutputStream();
			in = new FileInputStream(file);
			int len = in.available();
			byte[] b = new byte[len];
			in.read(b);
			out.write(b);
			out.flush();

		} catch (Exception e) {
			logger.error("下载失败");
			throw new Exception("下载失败!");
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (delFile) {
				file.delete();
			}
		}
		return response;
	}

}
