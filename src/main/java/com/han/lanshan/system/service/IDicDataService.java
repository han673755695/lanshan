package com.han.lanshan.system.service;

import java.util.List;
import java.util.Map;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.DicData;


public interface IDicDataService extends IBaseService{

    
    /**
	  * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteDicData(List ids) throws Exception;
	
	/**
	 * 新增/更新
	 * 
	 * @param DicData dicData
	 * @return String
	 * @throws Exception
	 */
	void saveorupdateDicData(DicData dicData, boolean all) throws Exception;
	
	/**
	 * 
	  * 描述：   批量保存
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午9:26:24   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午9:26:24
	  * 修改备注：   
	 * @param dicDataList
	 * @throws Exception
	 * @version
	 */
	void saveDicDataList(List<DicData> dicDataList) throws Exception;
	
	/**
	 * 
	  * 描述：   根据条件动态获取count
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午2:48:40   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午2:48:40
	  * 修改备注：   
	 * @param dicData
	 * @param page
	 * @return
	 * @throws Exception
	 * @version
	 */
	int findDicDataCount(DicData dicData, Page page) throws Exception;
	
	/**
	 * 列表
	 * 
	 * @param DicData dicData
	 * @return List<DicData>
	 * @throws Exception
	 */
	List<DicData> findDicDataList(DicData dicData, Page page) throws Exception;
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return DicData
	 * @throws Exception
	 */
	DicData findDicDataById(String id) throws Exception;
    
}
