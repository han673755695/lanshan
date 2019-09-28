package com.han.lanshan.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.constant.SystemEnum;
import com.han.lanshan.system.dao.MenuMapper;
import com.han.lanshan.system.entry.Menu;
import com.han.lanshan.system.service.IMenuService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
public class MenuServiceImpl extends BaseServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper menuDao;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findMenuList((Menu) obj, page);
	}
	

	@Override
	public void deleteMenu(String ids) throws Exception {
		try {
			String[] idArr = ids.split(",");
			menuDao.deleteMenu(idArr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateMenu(Menu menu, boolean all) throws Exception {
		try {
			if (StringUtils.isBlank(menu.getId())) {
				String uuid = UUIDUtils.getUUID();
				menu.setId(uuid);
				if (StringUtils.isEmpty(menu.getParentId())) {
					menu.setParentId("-1");
				}
				Menu parentMenu = menuDao.findMenuById(menu.getParentId());
				String level = parentMenu.getLevel();
				menu.setLevel(Integer.parseInt(level) + 1 + "");
				menu.setCreateDate(new Date());
				menu.setUpdateDate(new Date());
				menu.setActive(SystemEnum.ActiveEnum.可用.getValue());
				menuDao.saveMenu(menu);
			}else {
				if (all) {
					//当为true时,不更新字段为null的数据
					menuDao.updateActiveMenu(menu);
				}else {
					menuDao.updateMenu(menu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveMenuList(List<Menu> menuList) throws Exception {
		try {
			menuDao.saveMenuList(menuList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findMenuCount(Menu menu, Page page) throws Exception {
		try {
			return menuDao.findMenuCount(menu, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Menu> findMenuList(Menu menu, Page page) throws Exception {
		try {
			return menuDao.findMenuList(menu, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Menu findMenuById(String id) throws Exception {
		try {
			return menuDao.findMenuById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public List<Menu> getTreeMenu(Menu menu) throws Exception {
		try {
			List<Menu> findMenuList = menuDao.findMenuList(menu, null);
			return findMenuList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}


	@Override
	public List<Menu> findAuthMenuByUser(Map<String, String> paramMap) throws Exception {
		try {
			return menuDao.findAuthMenuByUser(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

}
