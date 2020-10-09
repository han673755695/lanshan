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
import com.han.lanshan.system.entry.UserOrg;
import com.han.lanshan.system.service.IUserOrgService;

@Controller
@RequestMapping("/s/userOrg")
public class UserOrgController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserOrgController.class);
	
	@Resource
	private IUserOrgService userOrgService;
	
	/**
	 * 列表
	 * 
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, UserOrg userOrg) {
		ReturnData returnObject = listjson(request, model, userOrg);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/userOrg/userOrgList";
	}
	
	
	/**
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnData listjson(HttpServletRequest request, Model model, UserOrg userOrg) {
		ReturnData returnData = ReturnData.getSuccess();
		
		try {
			Page page = Page.getPage(request);
			List<UserOrg> datas = userOrgService.findUserOrgList(userOrg, page);
			int totalCount = userOrgService.findUserOrgCount(userOrg, page);
			page.setTotalCount(totalCount);
			returnData.setQueryParams(userOrg);
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
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, UserOrg userOrg) {
		try {
			Page page = Page.getPage(request);
			File file = userOrgService.findDataExportExcel("/system/userOrg/userOrgList", page, UserOrg.class, userOrg);
			String fileName="userOrg" + GlobalStatic.excelext;
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
		return "/system/userOrg/userOrgLook";
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
				UserOrg userOrg = userOrgService.findUserOrgById(id);
				returnObject.setData(userOrg);
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
	public ReturnData saveorupdate(Model model, UserOrg userOrg, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			java.lang.String id =userOrg.getId();
			if(StringUtils.isBlank(id)){
				userOrg.setId(null);
			}
			
			userOrgService.saveorupdateUserOrg(userOrg, true);
			
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
		
		return "/system/userOrg/userOrgCru";
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
			 	userOrgService.deleteUserOrg(Arrays.asList(idsArr));
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
