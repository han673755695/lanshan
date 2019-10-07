package com.han.lanshan.system.controller;

import java.io.File;
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
import com.han.lanshan.system.constant.SystemEnum;
import com.han.lanshan.system.entry.Menu;
import com.han.lanshan.system.service.IMenuService;

@Controller
@RequestMapping("/s/menu")
public class MenuController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Resource
	private IMenuService menuService;
	
	/**
	 * 列表
	 * 
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, Menu menu) {
		ReturnData returnObject = listjson(request, model, menu);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/menu/menuList";
	}
	
	
	/**
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnData listjson(HttpServletRequest request, Model model, Menu menu) {
		ReturnData returnData = ReturnData.getSuccess();
		
		try {
			Page page = Page.getPage(request);
			menu.setActive(SystemEnum.ActiveEnum.可用.getValue());
			List<Menu> datas = menuService.findMenuList(menu, null);
			int totalCount = menuService.findMenuCount(menu, null);
			page.setTotalCount(totalCount);
			returnData.setQueryParams(menu);
			returnData.setPage(page);
			returnData.setData(datas);
		} catch (Exception e) {
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
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, Menu menu) {
		try {
			Page page = Page.getPage(request);
			File file = menuService.findDataExportExcel("/system/menu/menuList", page, Menu.class, menu);
			String fileName="menu" + GlobalStatic.excelext;
			downFile(response, file, fileName, true);
		} catch (Exception e) {
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
		return "/system/menu/menuLook";
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
				Menu menu = menuService.findMenuById(id);
				returnObject.setData(menu);
				
				if (!"-1".equals(menu.getParentId())) {
					Menu parentMenu = menuService.findMenuById(menu.getParentId());
					menu.setParentName(parentMenu.getName());
				}
				
			}else{
				returnObject.setStatus(ReturnData.ERROR);
			}
		} catch (Exception e) {
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
	public ReturnData saveorupdate(Model model, Menu menu, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			java.lang.String id =menu.getId();
			if(StringUtils.isBlank(id)){
				menu.setId(null);
			}
			
			menuService.saveorupdateMenu(menu, true);
			
		} catch (Exception e) {
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
	public String updatepre(Model model, HttpServletRequest request, HttpServletResponse response, Menu menu) {
		try {
			ReturnData returnObject = listjson(request, model, menu);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return "/system/menu/menuCru";
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
			 	menuService.deleteMenu(ids);
			} else {
				returnObject.setStatus(ReturnData.ERROR);
				returnObject.setMessage("删除失败");
				return returnObject;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			returnObject.setStatus(ReturnData.ERROR);
			returnObject.setMessage("操作失败");
			return returnObject;
		}
		return returnObject;
	}
	
	
	@RequestMapping(value="/getTreeMenu")
	@ResponseBody      
	public ReturnData getTreeMenu(HttpServletRequest request) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			Menu menu = new Menu();
			menu.setActive(SystemEnum.ActiveEnum.可用.getValue());
			List<Menu> treeMenuList = menuService.getTreeMenu(menu);
			returnObject.setData(treeMenuList);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			returnObject.setStatus(ReturnData.ERROR);
			returnObject.setMessage("操作失败");
			return returnObject;
		}
		return returnObject;
	}
	
}
