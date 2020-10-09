package com.han.lanshan.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.dao.UserRoleMapper;
import com.han.lanshan.system.entry.UserRole;
import com.han.lanshan.system.service.IUserRoleService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
@Transactional
public class UserRoleServiceImpl extends BaseServiceImpl implements IUserRoleService {

	@Autowired
	private UserRoleMapper userRoleDao;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findUserRoleList((UserRole) obj, page);
	}
	

	@Override
	public void deleteUserRole(List ids) throws Exception {
		try {
			if (CollectionUtils.isNotEmpty(ids)) {
				userRoleDao.deleteUserRole(ids);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateUserRole(UserRole userRole, boolean all) throws Exception {
		try {
			if (StringUtils.isBlank(userRole.getId())) {
				String uuid = UUIDUtils.getUUID();
				userRole.setId(uuid);
				userRoleDao.saveUserRole(userRole);
			}else {
				if (all) {
					//当为true时,不更新字段为null的数据
					userRoleDao.updateActiveUserRole(userRole);
				}else {
					userRoleDao.updateUserRole(userRole);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveUserRoleList(List<UserRole> userRoleList) throws Exception {
		try {
			for (UserRole userRole : userRoleList) {
				userRoleDao.saveUserRole(userRole);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findUserRoleCount(UserRole userRole, Page page) throws Exception {
		try {
			return userRoleDao.findUserRoleCount(userRole, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<UserRole> findUserRoleList(UserRole userRole, Page page) throws Exception {
		try {
			return userRoleDao.findUserRoleList(userRole, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public UserRole findUserRoleById(String id) throws Exception {
		try {
			return userRoleDao.findUserRoleById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public List<Map<String, String>> findUserRoleByUserId(String userId) throws Exception {
		try {
			if (StringUtils.isNotBlank(userId)) {
				return userRoleDao.findUserRoleByUserId(userId);
			}else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
