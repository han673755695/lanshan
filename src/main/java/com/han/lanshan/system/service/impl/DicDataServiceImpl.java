package com.han.lanshan.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.dao.DicDataMapper;
import com.han.lanshan.system.entry.DicData;
import com.han.lanshan.system.service.IDicDataService;
import com.han.lanshan.system.utils.UUIDUtils;


@Service
@Transactional
public class DicDataServiceImpl extends BaseServiceImpl implements IDicDataService {

	@Autowired
	private DicDataMapper dicDataDao;
	
	/**
	 * 导出方法调用
	 */
	@Override
	public <T> List<T> findListDataByParams(Object obj, Class<T> clazz, Page page) throws Exception {
		
		return (List<T>) findDicDataList((DicData) obj, page);
	}
	

	@Override
	public void deleteDicData(List ids) throws Exception {
		try {
		
			if (CollectionUtils.isNotEmpty(ids)) {
				dicDataDao.deleteDicData(ids);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveorupdateDicData(DicData dicData, boolean all) throws Exception {
		try {
			if (StringUtils.isBlank(dicData.getId())) {
				String uuid = UUIDUtils.getUUID();
				dicData.setId(uuid);
				dicDataDao.saveDicData(dicData);
			}else {
				if (all) {
					//当为true时,不更新字段为null的数据
					dicDataDao.updateActiveDicData(dicData);
				}else {
					dicDataDao.updateDicData(dicData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveDicDataList(List<DicData> dicDataList) throws Exception {
		try {
			for (DicData dicData : dicDataList) {
				dicDataDao.saveDicData(dicData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public int findDicDataCount(DicData dicData, Page page) throws Exception {
		try {
			return dicDataDao.findDicDataCount(dicData, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<DicData> findDicDataList(DicData dicData, Page page) throws Exception {
		try {
			return dicDataDao.findDicDataList(dicData, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public DicData findDicDataById(String id) throws Exception {
		try {
			return dicDataDao.findDicDataById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
