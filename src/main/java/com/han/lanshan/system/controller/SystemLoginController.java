package com.han.lanshan.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.han.lanshan.system.common.ReturnData;
import com.han.lanshan.system.common.SessionUser;
import com.han.lanshan.system.constant.SystemEnum.MenuType;
import com.han.lanshan.system.entry.Menu;
import com.han.lanshan.system.service.IMenuService;

@Controller
@RequestMapping("/s/system/login")
public class SystemLoginController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemLoginController.class);
	
	@Resource
	private IMenuService menuService;

	/**
	 * 
	  * 描述：跳到登陆页面   
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午7:49:48   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午7:49:48
	  * 修改备注：   
	 * @param model
	 * @param request
	 * @return
	 * @version
	 */
	@RequestMapping("toLogin")
	public String toLogin(Model model, HttpServletRequest request) {
		logger.info("跳到登陆页面");
		return "/system/login";
	}
	
	/**
	 * 
	  * 描述：登陆方法   
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午7:49:30   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午7:49:30
	  * 修改备注：   
	 * @param model
	 * @param request
	 * @return
	 * @version
	 */
	@RequestMapping("login")
	@ResponseBody
	public ReturnData login(Model model, HttpServletRequest request) {
		ReturnData returnData = ReturnData.getSuccess();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (StringUtils.isEmpty(username)) {
			returnData.setStatus(ReturnData.ERROR);
			returnData.setMessage("用户名不能为空");
			return returnData;
		}
		if (StringUtils.isEmpty(password)) {
			returnData.setStatus(ReturnData.ERROR);
			returnData.setMessage("密码不能为空");
			return returnData;
		}

		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前准备 token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 执行认证登陆
		subject.login(token);
		
		return returnData;
	}
	
	/**
	 * 
	  * 描述：   跳到登陆页面
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午7:50:48   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午7:50:48
	  * 修改备注：   
	 * @return
	 * @version
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		try {
			String userId = SessionUser.getUserId();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("userId", userId);
			//菜单类型(1,菜单,2按钮)
			paramMap.put("menuTpye", MenuType.菜单.getValue());
			//菜单等级
			paramMap.put("level", "1");
			
			List<Menu> firstMenuList = menuService.findAuthMenuByUser(paramMap);
			paramMap.put("level", "2");
			List<Menu> secondMenuList = menuService.findAuthMenuByUser(paramMap);
			paramMap.put("level", "3");
			List<Menu> threeMenuList = menuService.findAuthMenuByUser(paramMap);
			paramMap.put("level", "4");
			List<Menu> fourMenuList = menuService.findAuthMenuByUser(paramMap);
			
			model.addAttribute("firstMenuList", firstMenuList);
			model.addAttribute("secondMenuList", secondMenuList);
			model.addAttribute("threeMenuList", threeMenuList);
			model.addAttribute("fourMenuList", fourMenuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/system/index";
	}
	
	/**
	 * 欢迎页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toWelcome")
	public String toWelcome(Model model, HttpServletRequest request) {
		logger.info("进入后台管理欢迎页面");
		return "/system/welcome";
	}
	
}
