package com.han.lanshan.system.controller.f;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.han.lanshan.system.common.ReturnData;
import com.han.lanshan.system.controller.BaseController;
import com.han.lanshan.system.entry.User;
import com.han.lanshan.system.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "测试类", tags = "测试类")
@Controller
@RequestMapping("/test/weixin")
public class TestController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Resource
	private IUserService userService;
	
	/**
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 */
	@ApiOperation(value="微信的回调方法", notes="注意是外网")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
	@ApiParam
	@RequestMapping("/callback")
	@ResponseBody
	public ReturnData listjson(HttpServletRequest request, Model model, User user) {
		ReturnData returnData = ReturnData.getSuccess();
		
		try {
			System.out.println("回调");
			Enumeration<String> parameterNames = request.getParameterNames();
			if (parameterNames.hasMoreElements()) {
				String nextElement = parameterNames.nextElement();
				String param = request.getParameter(nextElement);
				System.out.println(nextElement + "=" + param);
			}
			
			String code = request.getParameter("code");
			
			returnData.setQueryParams(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return returnData;
	}
	
	
	
}
