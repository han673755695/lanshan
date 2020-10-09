package com.han.lanshan.system.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.han.lanshan.system.common.GlobalStatic;
import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.common.ReturnData;
import com.han.lanshan.system.entry.DicData;
import com.han.lanshan.system.service.IDicDataService;

@Controller
@RequestMapping("/s/dicData/{pathtypekey}")
public class DicDataController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(DicDataController.class);
	
	@Resource
	private IDicDataService dicDataService;
	
	/**
	 * 列表
	 * 
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@PathVariable String pathtypekey,HttpServletRequest request, Model model, DicData dicData) {
		ReturnData returnObject = listjson(pathtypekey, request, model, dicData);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/dicData/dicDataList";
	}
	
	
	/**
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnData listjson(@PathVariable String pathtypekey,HttpServletRequest request, Model model, DicData dicData) {
		ReturnData returnData = ReturnData.getSuccess();
		
		try {
			Page page = Page.getPage(request);
			List<DicData> datas = dicDataService.findDicDataList(dicData, page);
			int totalCount = dicDataService.findDicDataCount(dicData, page);
			page.setTotalCount(totalCount);
			returnData.setQueryParams(dicData);
			returnData.setPage(page);
			returnData.setData(datas);
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("typekey", pathtypekey);
			returnData.setMap(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			returnData.setStatus(ReturnData.ERROR);
			returnData.setMessage("操作失败");
			return returnData;
		}
		return returnData;
	}
	
	
	/**
	 * 导出
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @param 
	 */
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, DicData dicData) {
		try {
			Page page = Page.getPage(request);
			File file = dicDataService.findDataExportExcel("/system/dicData/dicDataList", page, DicData.class, dicData);
			String fileName="dicData" + GlobalStatic.excelext;
			downFile(response, file, fileName, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return;
	}
	
	/**
	 * 查看
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/look")
	public String look(@PathVariable String pathtypekey, Model model, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnObject = lookjson(pathtypekey, model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/dicData/dicDataLook";
	}
	
	/**
	 * 查看的Json
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnData lookjson(@PathVariable String pathtypekey, Model model,HttpServletRequest request,HttpServletResponse response) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			java.lang.String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				DicData dicData = dicDataService.findDicDataById(id);
				returnObject.setData(dicData);
			}else{
				returnObject.setStatus(ReturnData.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			returnObject.setStatus(ReturnData.ERROR);
			returnObject.setMessage("操作失败");
			return returnObject;
		}
		return returnObject;
		
	}
	
	/**
	 * 新增/修改
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody      
	public ReturnData saveorupdate(Model model, DicData dicData, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			java.lang.String id =dicData.getId();
			if(StringUtils.isBlank(id)){
				dicData.setId(null);
			}
			
			dicDataService.saveorupdateDicData(dicData, true);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			returnObject.setStatus(ReturnData.ERROR);
			returnObject.setMessage("操作失败");
			return returnObject;
		}
		return returnObject;
	
	}
	
	/**
	 * 新增/修改页面
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(@PathVariable String pathtypekey, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			ReturnData returnObject = lookjson(pathtypekey, model, request, response);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("typekey", pathtypekey);
			returnObject.setMap(dataMap);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return "/system/dicData/dicDataCru";
	}
	
	/**
	 * 删除
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody      
	public ReturnData delete(HttpServletRequest request) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			String ids=request.getParameter("ids");
			if(StringUtils.isNotBlank(ids)){
				String[] idsArr = ids.split(",");
			 	dicDataService.deleteDicData(Arrays.asList(idsArr));
			} else {
				returnObject.setStatus(ReturnData.ERROR);
				returnObject.setMessage("删除失败");
				return returnObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			returnObject.setStatus(ReturnData.ERROR);
			returnObject.setMessage("操作失败");
			return returnObject;
		}
		return returnObject;
	}
	
}
