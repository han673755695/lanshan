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
import com.han.lanshan.system.common.ShiroUser;
import com.han.lanshan.system.constant.SystemEnum;
import com.han.lanshan.system.dao.RoleMapper;
import com.han.lanshan.system.entry.Menu;
import com.han.lanshan.system.entry.Role;
import com.han.lanshan.system.entry.RoleMenu;
import com.han.lanshan.system.entry.User;
import com.han.lanshan.system.service.IRoleMenuService;
import com.han.lanshan.system.service.IRoleService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleDao;
	@Resource
	private IRoleMenuService roleMenuService;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findRoleList((Role) obj, page);
	}
	

	@Override
	public void deleteRole(String ids) throws Exception {
		try {
			String[] idArr = ids.split(",");
			roleDao.deleteRole(idArr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateRole(Role role, boolean all) throws Exception {
		try {
			User user = SessionUser.getUser();
			String userId = user.getId();
			if (StringUtils.isBlank(role.getId())) {
				String uuid = UUIDUtils.getUUID();
				role.setId(uuid);
				role.setCreateDate(new Date());
				role.setCreateUser(userId);
				
				roleDao.saveRole(role);
			}else {
				role.setUpdateDate(new Date());
				role.setUpdateUser(userId);
				if (all) {
					//当为true时,不更新字段为null的数据
					roleDao.updateActiveRole(role);
				}else {
					roleDao.updateRole(role);
				}
				
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setActive(SystemEnum.ActiveEnum.可用.getValue());
				roleMenu.setRoleId(role.getId());
				List<RoleMenu> findRoleMenuList = roleMenuService.findRoleMenuList(roleMenu, null);
				List<String> roleMenuIds = new ArrayList<String>();
				for (RoleMenu roleMenu2 : findRoleMenuList) {
					roleMenuIds.add(roleMenu2.getId());
				}
				
				if (CollectionUtils.isNotEmpty(roleMenuIds)) {
					roleMenuService.deleteRoleMenu(roleMenuIds);
				}
				
			}
			
			List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
			if (StringUtils.isNotBlank(role.getMenuIds())) {
				String[] menuIdArr = role.getMenuIds().split(",");
				for (String menuId : menuIdArr) {
					if (StringUtils.isNotBlank(menuId)) {
						RoleMenu roleMenu = new RoleMenu();
						roleMenu.setId(UUIDUtils.getUUID());
						roleMenu.setActive(SystemEnum.ActiveEnum.可用.getValue());
						roleMenu.setRoleId(role.getId());
						roleMenu.setMenuId(menuId);
						roleMenu.setCreateDate(new Date());
						roleMenu.setCreateUser(userId);
						roleMenu.setUpdateDate(new Date());
						roleMenu.setUpdateUser(userId);
						roleMenuList.add(roleMenu);
					}
					
				}
			}
			
			if (CollectionUtils.isNotEmpty(roleMenuList)) {
				roleMenuService.saveRoleMenuList(roleMenuList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveRoleList(List<Role> roleList) throws Exception {
		try {
			roleDao.saveRoleList(roleList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findRoleCount(Role role, Page page) throws Exception {
		try {
			return roleDao.findRoleCount(role, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Role> findRoleList(Role role, Page page) throws Exception {
		try {
			List<Role> findRoleList = roleDao.findRoleList(role, page);
			for (Role role2 : findRoleList) {
				role2.setMenuList(roleMenuService.findRoleMenuByRoleId(role2.getId()));
			}
			
			return findRoleList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Role findRoleById(String id) throws Exception {
		try {
			return roleDao.findRoleById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
