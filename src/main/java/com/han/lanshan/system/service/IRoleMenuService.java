package com.han.lanshan.system.service;

import java.util.List;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.Menu;
import com.han.lanshan.system.entry.RoleMenu;


public interface IRoleMenuService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteRoleMenu(List ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param RoleMenu roleMenu
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateRoleMenu(RoleMenu roleMenu, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param roleMenuList
	 * @throws Exception
	 * @version
	 */
	void saveRoleMenuList(List<RoleMenu> roleMenuList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param roleMenu
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findRoleMenuCount(RoleMenu roleMenu, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param RoleMenu roleMenu
	 * @return List<RoleMenu>
	 * @throws Exception
	 */
	List<RoleMenu> findRoleMenuList(RoleMenu roleMenu, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return RoleMenu
	 * @throws Exception
	 */
	RoleMenu findRoleMenuById(String id) throws Exception;
    
	/**
	 * 
	  * 描述：   根据角色id获取权限
	  * 创建人：HYD
	  * 创建时间：2019年9月28日 上午11:26:02   
	  * 修改人：HYD
	  * 修改时间：2019年9月28日 上午11:26:02
	  * 修改备注：   
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @version
	 */
	List<Menu> findRoleMenuByRoleId(String roleId) throws Exception;
}
