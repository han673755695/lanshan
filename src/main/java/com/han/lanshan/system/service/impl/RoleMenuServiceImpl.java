package com.han.lanshan.system.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.dao.RoleMenuMapper;
import com.han.lanshan.system.entry.Menu;
import com.han.lanshan.system.entry.RoleMenu;
import com.han.lanshan.system.service.IRoleMenuService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
@Transactional
public class RoleMenuServiceImpl extends BaseServiceImpl implements IRoleMenuService {

	@Autowired
	private RoleMenuMapper roleMenuDao;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findRoleMenuList((RoleMenu) obj, page);
	}
	

	@Override
	public void deleteRoleMenu(List ids) throws Exception {
		try {
		
			if (CollectionUtils.isNotEmpty(ids)) {
				roleMenuDao.deleteRoleMenu(ids);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateRoleMenu(RoleMenu roleMenu, boolean all) throws Exception {
		try {
			if (StringUtils.isBlank(roleMenu.getId())) {
				String uuid = UUIDUtils.getUUID();
				roleMenu.setId(uuid);
				roleMenuDao.saveRoleMenu(roleMenu);
			}else {
				if (all) {
					//当为true时,不更新字段为null的数据
					roleMenuDao.updateActiveRoleMenu(roleMenu);
				}else {
					roleMenuDao.updateRoleMenu(roleMenu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveRoleMenuList(List<RoleMenu> roleMenuList) throws Exception {
		try {
			for (RoleMenu roleMenu : roleMenuList) {
				roleMenuDao.saveRoleMenu(roleMenu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findRoleMenuCount(RoleMenu roleMenu, Page page) throws Exception {
		try {
			return roleMenuDao.findRoleMenuCount(roleMenu, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<RoleMenu> findRoleMenuList(RoleMenu roleMenu, Page page) throws Exception {
		try {
			return roleMenuDao.findRoleMenuList(roleMenu, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public RoleMenu findRoleMenuById(String id) throws Exception {
		try {
			return roleMenuDao.findRoleMenuById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public List<Menu> findRoleMenuByRoleId(String roleId) throws Exception {
		try {
			if (StringUtils.isNotBlank(roleId)) {
				return roleMenuDao.findRoleMenuByRoleId(roleId);
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
