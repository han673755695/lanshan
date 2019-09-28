package com.han.lanshan.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.common.SessionUser;
import com.han.lanshan.system.constant.SystemEnum;
import com.han.lanshan.system.dao.UserMapper;
import com.han.lanshan.system.entry.User;
import com.han.lanshan.system.entry.UserRole;
import com.han.lanshan.system.service.IUserRoleService;
import com.han.lanshan.system.service.IUserService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

	@Autowired
	private UserMapper userDao;
	@Autowired
	private IUserRoleService userRoleService;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findUserList((User) obj, page);
	}
	

	@Override
	public User selectUserByNameAndPwd(Map<String, Object> params) {
		try {
			return userDao.selectUserByNameAndPwd(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteUser(String ids) throws Exception {
		try {
			String[] idArr = ids.split(",");
			userDao.deleteUser(idArr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateUser(User user, boolean all) throws Exception {
		try {
			String userId = SessionUser.getUserId();
			if (StringUtils.isBlank(user.getId())) {
				String uuid = UUIDUtils.getUUID();
				user.setId(uuid);
				user.setStatus(SystemEnum.ActiveEnum.可用.getValue());
				user.setCreateDate(new Date());
				userDao.saveUser(user);
				
			}else {
				
				if (all) {
					//当为true时,不更新字段为null的数据
					userDao.updateActiveUser(user);
				}else {
					userDao.updateUser(user);
				}
				
				//删除原有的角色信息
				UserRole userRole = new UserRole();
				userRole.setActive(SystemEnum.ActiveEnum.可用.getValue());
				userRole.setUserId(user.getId());
				List<UserRole> findUserRoleList = userRoleService.findUserRoleList(userRole, null);
				List<String> idList = new ArrayList<String>();
				for (UserRole userRole2 : findUserRoleList) {
					idList.add(userRole2.getId());
				}
				
				userRoleService.deleteUserRole(idList);
				
			}
			
			//更新角色信息
			List<UserRole> userRoleList = new ArrayList<UserRole>(); 
			if (StringUtils.isNotBlank(user.getRoleIds())) {
				String[] roleIdArr = user.getRoleIds().split(",");
				for (String roleId : roleIdArr) {
					if (StringUtils.isNotBlank(roleId)) {
						
						UserRole userRole = new UserRole();
						userRole.setId(UUIDUtils.getUUID());
						userRole.setCreateDate(new Date());
						userRole.setCreateUser(userId);
						userRole.setUserId(user.getId());
						userRole.setRoleId(roleId);
						userRole.setActive(SystemEnum.ActiveEnum.可用.getValue());
						userRoleList.add(userRole);
					}
				}
			}
			if (CollectionUtils.isNotEmpty(userRoleList)) {
				userRoleService.saveUserRoleList(userRoleList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveUserList(List<User> userList) throws Exception {
		try {
			userDao.saveUserList(userList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findUserCount(User user, Page page) throws Exception {
		try {
			return userDao.findUserCount(user, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<User> findUserList(User user, Page page) throws Exception {
		try {
			return userDao.findUserList(user, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public User findUserById(String id) throws Exception {
		try {
			return userDao.findUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
