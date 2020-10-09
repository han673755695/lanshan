package com.han.lanshan.system.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.dao.UserOrgMapper;
import com.han.lanshan.system.entry.Org;
import com.han.lanshan.system.entry.UserOrg;
import com.han.lanshan.system.service.IUserOrgService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
@Transactional
public class UserOrgServiceImpl extends BaseServiceImpl implements IUserOrgService {

	@Autowired
	private UserOrgMapper userOrgDao;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findUserOrgList((UserOrg) obj, page);
	}
	

	@Override
	public void deleteUserOrg(List ids) throws Exception {
		try {
		
			if (CollectionUtils.isNotEmpty(ids)) {
				userOrgDao.deleteUserOrg(ids);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateUserOrg(UserOrg userOrg, boolean all) throws Exception {
		try {
			if (StringUtils.isBlank(userOrg.getId())) {
				String uuid = UUIDUtils.getUUID();
				userOrg.setId(uuid);
				userOrgDao.saveUserOrg(userOrg);
			}else {
				if (all) {
					//当为true时,不更新字段为null的数据
					userOrgDao.updateActiveUserOrg(userOrg);
				}else {
					userOrgDao.updateUserOrg(userOrg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveUserOrgList(List<UserOrg> userOrgList) throws Exception {
		try {
			for (UserOrg userOrg : userOrgList) {
				userOrgDao.saveUserOrg(userOrg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findUserOrgCount(UserOrg userOrg, Page page) throws Exception {
		try {
			return userOrgDao.findUserOrgCount(userOrg, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<UserOrg> findUserOrgList(UserOrg userOrg, Page page) throws Exception {
		try {
			return userOrgDao.findUserOrgList(userOrg, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public UserOrg findUserOrgById(String id) throws Exception {
		try {
			return userOrgDao.findUserOrgById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public List<Org> findUserOrgByUserId(String userId) throws Exception {
		try {
			return userOrgDao.findUserOrgByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
