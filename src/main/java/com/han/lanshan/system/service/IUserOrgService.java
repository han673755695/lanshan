package com.han.lanshan.system.service;

import java.util.List;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.Org;
import com.han.lanshan.system.entry.UserOrg;


public interface IUserOrgService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteUserOrg(List ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param UserOrg userOrg
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateUserOrg(UserOrg userOrg, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param userOrgList
	 * @throws Exception
	 * @version
	 */
	void saveUserOrgList(List<UserOrg> userOrgList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param userOrg
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findUserOrgCount(UserOrg userOrg, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param UserOrg userOrg
	 * @return List<UserOrg>
	 * @throws Exception
	 */
	List<UserOrg> findUserOrgList(UserOrg userOrg, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return UserOrg
	 * @throws Exception
	 */
	UserOrg findUserOrgById(String id) throws Exception;
	
	/**
	 * 
	  * 描述：   根据用户id获取用户的所有部门
	  * 创建人：HYD
	  * 创建时间：2019年9月28日 下午9:45:27   
	  * 修改人：HYD
	  * 修改时间：2019年9月28日 下午9:45:27
	  * 修改备注：   
	 * @param userId
	 * @return
	 * @throws Exception
	 * @version
	 */
	List<Org> findUserOrgByUserId(String userId) throws Exception;
    
}
