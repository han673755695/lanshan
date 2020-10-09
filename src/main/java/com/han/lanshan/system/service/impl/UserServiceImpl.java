package com.han.lanshan.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.common.SessionUser;
import com.han.lanshan.system.constant.SystemEnum;
import com.han.lanshan.system.dao.UserMapper;
import com.han.lanshan.system.entry.User;
import com.han.lanshan.system.entry.UserOrg;
import com.han.lanshan.system.entry.UserRole;
import com.han.lanshan.system.service.IUserOrgService;
import com.han.lanshan.system.service.IUserRoleService;
import com.han.lanshan.system.service.IUserService;
import com.han.lanshan.system.utils.SecUtils;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

	@Autowired
	private UserMapper userDao;
	@Autowired
	private IUserRoleService userRoleService;
	@Resource
	private IUserOrgService userOrgService;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		List<User> datas = (List<User>) findUserList((User) obj, page);
		for (User user2 : datas) {
			//该用户的角色
			List<Map<String, String>> findUserRoleByUserId = userRoleService.findUserRoleByUserId(user2.getId());
			String roleNames = "";
			if (CollectionUtils.isNotEmpty(findUserRoleByUserId)) {
				for (int i = 0; i < findUserRoleByUserId.size(); i++) {
					if (i == 0) {
						roleNames += findUserRoleByUserId.get(i).get("roleName");
					}else {
						roleNames += "," + findUserRoleByUserId.get(i).get("roleName");
					}
				}
			}
			user2.setRoleNames(roleNames);
		}
		return (List<T>) datas;
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
				String password = SecUtils.encoderByMd5With32Bit(user.getAccount());
				String uuid = UUIDUtils.getUUID();
				user.setId(uuid);
				user.setPassword(password);
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
				
				//删除原有的部门信息
				UserOrg userOrg = new UserOrg();
				userOrg.setActive(SystemEnum.ActiveEnum.可用.getValue());
				userOrg.setUserId(user.getId());
				List<UserOrg> findUserOrgList = userOrgService.findUserOrgList(userOrg, null);
				List<String> userOrgIdList = new ArrayList<String>();
				for (UserOrg userOrg2 : findUserOrgList) {
					userOrgIdList.add(userOrg2.getId());
				}
				
				userOrgService.deleteUserOrg(userOrgIdList);
				
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
			
			//保存部门
			List<UserOrg> userOrgList = new ArrayList<UserOrg>();
			if (StringUtils.isNotBlank(user.getOrgIds())) {
				String[] orgIdArr = user.getOrgIds().split(",");
				for (String orgId : orgIdArr) {
					UserOrg userOrg = new UserOrg();
					userOrg.setId(UUIDUtils.getUUID());
					userOrg.setOrgId(orgId);
					userOrg.setUserId(user.getId());
					userOrg.setCreateDate(new Date());
					userOrg.setCreateUser(userId);
					userOrg.setActive(SystemEnum.ActiveEnum.可用.getValue());
					userOrgList.add(userOrg);
				}
			}
			
			if (CollectionUtils.isNotEmpty(userOrgList)) {
				userOrgService.saveUserOrgList(userOrgList);
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
