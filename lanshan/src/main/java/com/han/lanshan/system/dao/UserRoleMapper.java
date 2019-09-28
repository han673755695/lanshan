package com.han.lanshan.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.UserRole;

@Repository
public interface UserRoleMapper {


	/**
	 * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteUserRole(List<String> ids) throws Exception;

	/**
	 * 新增
	 * 
	 * @param UserRole userRole
	 * @return String
	 * @throws Exception
	 */
	void saveUserRole(UserRole userRole) throws Exception;
	
	/**
	 * 更新
	 * 
	 * @param UserRole userRole
	 * @return String
	 * @throws Exception
	 */
	void updateUserRole(UserRole userRole) throws Exception;
	
	/**
	 * 
	  * 描述：   动态修改信息
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午5:56:04   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午5:56:04
	  * 修改备注：   
	 * @param user
	 * @throws Exception
	 * @version
	 */
	void updateActiveUserRole(UserRole userRole) throws Exception;

	/**
	 * 
	 * 描述： 批量保存 创建人：HYD 创建时间：2019年9月8日 下午9:26:24 修改人：HYD 修改时间：2019年9月8日 下午9:26:24
	 * 修改备注：
	 * 
	 * @param userRoleList
	 * @throws Exception
	 * @version
	 */
	void saveUserRoleList(List<UserRole> userRoleList) throws Exception;

	/**
	 * 列表
	 * 
	 * @param UserRole userRole
	 * @return List<UserRole>
	 * @throws Exception
	 */
	List<UserRole> findUserRoleList(UserRole userRole, Page page) throws Exception;
	
	/**
	 * 
	  * 描述：   根据用户id获取用户的角色列表
	  * 创建人：HYD
	  * 创建时间：2019年9月27日 下午8:54:57   
	  * 修改人：HYD
	  * 修改时间：2019年9月27日 下午8:54:57
	  * 修改备注：   
	 * @param userId
	 * @return
	 * @throws Exception
	 * @version
	 */
	List<Map<String, String>> findUserRoleByUserId(String userId) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param userRole
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findUserRoleCount(UserRole userRole, Page page) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return User
	 * @throws Exception
	 */
	UserRole findUserRoleById(String id) throws Exception;

}