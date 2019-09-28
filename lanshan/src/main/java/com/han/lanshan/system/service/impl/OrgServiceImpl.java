package com.han.lanshan.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.common.SessionUser;
import com.han.lanshan.system.dao.OrgMapper;
import com.han.lanshan.system.entry.Org;
import com.han.lanshan.system.service.IOrgService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
public class OrgServiceImpl extends BaseServiceImpl implements IOrgService {

	@Autowired
	private OrgMapper orgDao;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findOrgList((Org) obj, page);
	}
	

	@Override
	public void deleteOrg(List ids) throws Exception {
		try {
		
			if (CollectionUtils.isNotEmpty(ids)) {
				orgDao.deleteOrg(ids);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateOrg(Org org, boolean all) throws Exception {
		try {
			String userId = SessionUser.getUserId();
			if (StringUtils.isBlank(org.getId())) {
				String uuid = UUIDUtils.getUUID();
				org.setId(uuid);
				org.setCreateDate(new Date());
				org.setCreateUser(userId);
				orgDao.saveOrg(org);
			}else {
				org.setUpdateDate(new Date());
				org.setUpdateUser(userId);
				if (all) {
					//当为true时,不更新字段为null的数据
					orgDao.updateActiveOrg(org);
				}else {
					orgDao.updateOrg(org);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveOrgList(List<Org> orgList) throws Exception {
		try {
			for (Org org : orgList) {
				orgDao.saveOrg(org);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findOrgCount(Org org, Page page) throws Exception {
		try {
			return orgDao.findOrgCount(org, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Org> findOrgList(Org org, Page page) throws Exception {
		try {
			return orgDao.findOrgList(org, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Org findOrgById(String id) throws Exception {
		try {
			return orgDao.findOrgById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
