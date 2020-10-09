package com.han.lanshan.system.service;

import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.Role;


public interface IRoleService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteRole(String ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param Role role
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateRole(Role role, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param roleList
	 * @throws Exception
	 * @version
	 */
	void saveRoleList(List<Role> roleList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param role
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findRoleCount(Role role, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param Role role
	 * @return List<Role>
	 * @throws Exception
	 */
	List<Role> findRoleList(Role role, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return Role
	 * @throws Exception
	 */
	Role findRoleById(String id) throws Exception;
    
}
