package com.han.lanshan.system.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;



/**
 * 
  * 描述：   基础的service
  * 创建人：HYD
  * 创建时间：2019年9月8日 上午11:55:39   
  * 修改人：HYD
  * 修改时间：2019年9月8日 上午11:55:39
  * 修改备注：   
 * @version
 */
public interface IBaseService {

	/**
	 * 
	 * 描述：   
	 * 创建人：HYD
	 * 创建时间：2019年9月8日 下午1:14:17   
	 * 修改人：HYD
	 * 修改时间：2019年9月8日 下午1:14:17
	 * 修改备注：   
	 * @param <T>
	 * @param ftlurl
	 * @param clazz
	 * @param queryBean
	 * @return
	 * @throws Exception
	 * @version
	 */
	public <T> File findDataExportExcel(String ftlurl, Page page, Class<T> clazz, Object queryBean)
			throws Exception;
	
	/**
	 * 
	 * 描述：   
	 * 创建人：HYD
	 * 创建时间：2019年9月8日 下午1:14:46   
	 * 修改人：HYD
	 * 修改时间：2019年9月8日 下午1:14:46
	 * 修改备注：   
	 * @param <T>
	 * @param ftlurl
	 * @param page
	 * @param clazz
	 * @param queryBean
	 * @param map
	 * @return
	 * @throws Exception
	 * @version
	 */
	public <T> File findDataExportExcel(String ftlurl, Page page, Class<T> clazz, Object queryBean,
			Map map) throws Exception;
	
	/**
	 * 
	 * 描述：   导出方法调用
	 * 创建人：HYD
	 * 创建时间：2019年9月9日 下午11:46:40   
	 * 修改人：HYD
	 * 修改时间：2019年9月9日 下午11:46:40
	 * 修改备注：   
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 * @version
	 * @param <T>
	 */
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception;
	

}
