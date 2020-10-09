package com.han.lanshan.system.service;

import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.Org;


public interface IOrgService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteOrg(List ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param Org org
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateOrg(Org org, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param orgList
	 * @throws Exception
	 * @version
	 */
	void saveOrgList(List<Org> orgList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param org
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findOrgCount(Org org, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param Org org
	 * @return List<Org>
	 * @throws Exception
	 */
	List<Org> findOrgList(Org org, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return Org
	 * @throws Exception
	 */
	Org findOrgById(String id) throws Exception;
    
}
