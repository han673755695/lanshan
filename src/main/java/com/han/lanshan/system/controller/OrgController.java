package com.han.lanshan.system.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.han.lanshan.system.common.GlobalStatic;
import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.common.ReturnData;
import com.han.lanshan.system.entry.Org;
import com.han.lanshan.system.service.IOrgService;

@Controller
@RequestMapping("/s/org")
public class OrgController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OrgController.class);
	
	@Resource
	private IOrgService orgService;
	
	/**
	 * 列表
	 * 
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, Org org) {
		ReturnData returnObject = listjson(request, model, org);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/org/orgList";
	}
	
	
	/**
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnData listjson(HttpServletRequest request, Model model, Org org) {
		ReturnData returnData = ReturnData.getSuccess();
		
		try {
			Page page = Page.getPage(request);
			List<Org> datas = orgService.findOrgList(org, page);
			int totalCount = orgService.findOrgCount(org, page);
			page.setTotalCount(totalCount);
			returnData.setQueryParams(org);
			returnData.setPage(page);
			returnData.setData(datas);
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
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, Org org) {
		try {
			Page page = Page.getPage(request);
			File file = orgService.findDataExportExcel("/system/org/orgList", page, Org.class, org);
			String fileName="org" + GlobalStatic.excelext;
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
	public String look(Model model, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/org/orgLook";
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
	public ReturnData lookjson(Model model,HttpServletRequest request,HttpServletResponse response) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			java.lang.String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				Org org = orgService.findOrgById(id);
				returnObject.setData(org);
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
	public ReturnData saveorupdate(Model model, Org org, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			java.lang.String id =org.getId();
			if(StringUtils.isBlank(id)){
				org.setId(null);
			}
			
			orgService.saveorupdateOrg(org, true);
			
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
	public String updatepre(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			ReturnData returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return "/system/org/orgCru";
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
			 	orgService.deleteOrg(Arrays.asList(idsArr));
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
