package com.han.lanshan.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.entry.DicData;

@Repository
public interface DicDataMapper {


	/**
	 * 删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void deleteDicData(List<String> ids) throws Exception;

	/**
	 * 新增
	 * 
	 * @param DicData dicData
	 * @return String
	 * @throws Exception
	 */
	void saveDicData(DicData dicData) throws Exception;
	
	/**
	 * 更新
	 * 
	 * @param DicData dicData
	 * @return String
	 * @throws Exception
	 */
	void updateDicData(DicData dicData) throws Exception;
	
	/**
	 * 
	  * 描述：   动态修改信息
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午5:56:04   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午5:56:04
	  * 修改备注：   
	 * @param user
	 * @throws Exception
	 * @version
	 */
	void updateActiveDicData(DicData dicData) throws Exception;

	/**
	 * 
	 * 描述： 批量保存 创建人：HYD 创建时间：2019年9月8日 下午9:26:24 修改人：HYD 修改时间：2019年9月8日 下午9:26:24
	 * 修改备注：
	 * 
	 * @param dicDataList
	 * @throws Exception
	 * @version
	 */
	void saveDicDataList(List<DicData> dicDataList) throws Exception;

	/**
	 * 列表
	 * 
	 * @param DicData dicData
	 * @return List<DicData>
	 * @throws Exception
	 */
	List<DicData> findDicDataList(DicData dicData, Page page) throws Exception;
	
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
	 * 根据ID查找
	 * 
	 * @param id
	 * @return User
	 * @throws Exception
	 */
	DicData findDicDataById(String id) throws Exception;

}