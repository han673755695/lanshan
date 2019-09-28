package com.han.lanshan.system.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.han.lanshan.system.common.GlobalStatic;
import com.han.lanshan.system.common.Page;
import com.han.lanshan.system.common.ReturnData;
import com.han.lanshan.system.constant.SystemEnum;
import com.han.lanshan.system.entry.Org;
import com.han.lanshan.system.entry.Role;
import com.han.lanshan.system.entry.User;
import com.han.lanshan.system.entry.UserOrg;
import com.han.lanshan.system.service.IOrgService;
import com.han.lanshan.system.service.IRoleService;
import com.han.lanshan.system.service.IUserOrgService;
import com.han.lanshan.system.service.IUserRoleService;
import com.han.lanshan.system.service.IUserService;

@Controller
@RequestMapping("/s/user")
public class UserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;
	@Autowired
	private IUserRoleService userRoleService;
	@Resource
	private IOrgService orgService;
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
	public String list(HttpServletRequest request, Model model, User user) {
		user.setStatus(SystemEnum.ActiveEnum.可用.getValue());
		ReturnData returnObject = listjson(request, model, user);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/user/userList";
	}
	
	
	/**
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnData listjson(HttpServletRequest request, Model model, User user) {
		ReturnData returnData = ReturnData.getSuccess();
		
		try {
			Page page = Page.getPage(request);
			List<User> datas = userService.findUserList(user, page);
			
			for (User user2 : datas) {
				//该用户的角色
				List<Map<String, String>> findUserRoleByUserId = userRoleService.findUserRoleByUserId(user2.getId());
				String roleNames = "";
				if (CollectionUtils.isNotEmpty(findUserRoleByUserId)) {
					for (int i = 0; i < findUserRoleByUserId.size(); i++) {
						if (i == 0) {
							roleNames += findUserRoleByUserId.get(i).get("roleName");
						}else {
							roleNames += "," + findUserRoleByUserId.get(i).get("roleName");
						}
					}
				}
				user2.setRoleNames(roleNames);
				
				//该用户的所有部门
				List<Org> findUserOrgByUserId = userOrgService.findUserOrgByUserId(user2.getId());
				String orgNames = "";
				if (CollectionUtils.isNotEmpty(findUserOrgByUserId)) {
					for (int i = 0; i < findUserOrgByUserId.size(); i++) {
						Org org = findUserOrgByUserId.get(i);
						if (i == 0) {
							orgNames += org.getOrgName();
						}else {
							orgNames += "," + org.getOrgName();
						}
					}
				}
				user2.setOrgNames(orgNames);
				
			}
			
			
			int totalCount = userService.findUserCount(user, page);
			page.setTotalCount(totalCount);
			returnData.setQueryParams(user);
			returnData.setPage(page);
			returnData.setData(datas);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
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
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
		try {
			Page page = Page.getPage(request);
			user.setStatus(SystemEnum.ActiveEnum.可用.getValue());
			File file = userService.findDataExportExcel("/system/user/userList", page, User.class, user);
			String fileName="user" + GlobalStatic.excelext;
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
		try {
			java.lang.String id = request.getParameter("id");
			ReturnData returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			Role role = new Role();
			role.setActive(SystemEnum.ActiveEnum.可用.getValue());
			//所有的可用角色
			List<Role> roleList = roleService.findRoleList(role, null);
			//该用户的角色
			List<Map<String, String>> findUserRoleByUserId = userRoleService.findUserRoleByUserId(id);
			String roleIds = "";
			if (CollectionUtils.isNotEmpty(findUserRoleByUserId)) {
				for (int i = 0; i < findUserRoleByUserId.size(); i++) {
					if (i == 0) {
						roleIds += findUserRoleByUserId.get(i).get("roleId");
					}else {
						roleIds += "," + findUserRoleByUserId.get(i).get("roleId");
					}
				}
			}
			
			model.addAttribute("roleList", roleList);
			model.addAttribute("roleIds", roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return "/system/user/userLook";
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
				User clazzPicture = userService.findUserById(id);
				returnObject.setData(clazzPicture);
			}else{
				returnObject.setStatus(ReturnData.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
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
	public ReturnData saveorupdate(Model model, User user, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnObject = ReturnData.getSuccess();
		try {
			java.lang.String id =user.getId();
			if(StringUtils.isBlank(id)){
				user.setId(null);
			}
			
			userService.saveorupdateUser(user, true);
			
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
			java.lang.String id = request.getParameter("id");
			ReturnData returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			Role role = new Role();
			role.setActive(SystemEnum.ActiveEnum.可用.getValue());
			//所有的可用角色
			List<Role> roleList = roleService.findRoleList(role, null);
			
			Org org = new Org();
			org.setActive(SystemEnum.ActiveEnum.可用.getValue());
			//所有的部门
			List<Org> findOrgList = orgService.findOrgList(org, null);
			//该用户的角色
			List<Map<String, String>> findUserRoleByUserId = userRoleService.findUserRoleByUserId(id);
			String roleIds = "";
			if (CollectionUtils.isNotEmpty(findUserRoleByUserId)) {
				for (int i = 0; i < findUserRoleByUserId.size(); i++) {
					if (i == 0) {
						roleIds += findUserRoleByUserId.get(i).get("roleId");
					}else {
						roleIds += "," + findUserRoleByUserId.get(i).get("roleId");
					}
				}
			}
			
			//该用户的所有部门
			List<Org> findUserOrgByUserId = userOrgService.findUserOrgByUserId(id);
			String orgIds = "";
			if (CollectionUtils.isNotEmpty(findUserOrgByUserId)) {
				for (int i = 0; i < findUserOrgByUserId.size(); i++) {
					Org org2 = findUserOrgByUserId.get(i);
					if (i == 0) {
						orgIds += org2.getId();
					}else {
						orgIds += "," + org2.getId();
					}
				}
			}
			
			model.addAttribute("roleList", roleList);
			model.addAttribute("orgList", findOrgList);
			model.addAttribute("roleIds", roleIds);
			model.addAttribute("orgIds", orgIds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return "/system/user/userCru";
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
			 	userService.deleteUser(ids);
			} else {
				returnObject.setStatus(ReturnData.ERROR);
				returnObject.setMessage("删除失败");
				return returnObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			returnObject.setStatus(ReturnData.ERROR);
			returnObject.setMessage("删除失败");
		}
		return returnObject;
	}
	
}
