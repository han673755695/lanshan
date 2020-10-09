package com.han.lanshan.system.service;

import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.User;


public interface IUserService extends IBaseService{

    User selectUserByNameAndPwd(Map<String,Object> params);
    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteUser(String ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param User user
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateUser(User user, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param userList
	 * @throws Exception
	 * @version
	 */
	void saveUserList(List<User> userList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param user
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findUserCount(User user, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param User user
	 * @return List<User>
	 * @throws Exception
	 */
	List<User> findUserList(User user, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return User
	 * @throws Exception
	 */
	User findUserById(String id) throws Exception;
    
}
