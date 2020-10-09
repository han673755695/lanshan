package com.han.lanshan.system.service;

import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.Menu;


public interface IMenuService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteMenu(String ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param Menu menu
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateMenu(Menu menu, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param menuList
	 * @throws Exception
	 * @version
	 */
	void saveMenuList(List<Menu> menuList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param menu
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findMenuCount(Menu menu, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param Menu menu
	 * @return List<Menu>
	 * @throws Exception
	 */
	List<Menu> findMenuList(Menu menu, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return Menu
	 * @throws Exception
	 */
	Menu findMenuById(String id) throws Exception;
	
	/**
	 * 
	  * 描述：   前台获取菜单树方法
	  * 创建人：HYD
	  * 创建时间：2019年9月24日 下午8:19:33   
	  * 修改人：HYD
	  * 修改时间：2019年9月24日 下午8:19:33
	  * 修改备注：   
	 * @param menu
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	List<Menu> getTreeMenu(Menu menu) throws Exception;
	
	/**
	 * 
	  * 描述：   根据用户id获取权限菜单
	  * 创建人：HYD
	  * 创建时间：2019年9月28日 下午12:21:26   
	  * 修改人：HYD
	  * 修改时间：2019年9月28日 下午12:21:26
	  * 修改备注：   
	 * @param paramMap
	 * @return
	 * @throws Exception
	 * @version
	 */
	List<Menu> findAuthMenuByUser(Map<String, String> paramMap) throws Exception;
    
}
