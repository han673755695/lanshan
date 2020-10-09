package com.han.lanshan.system.config.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.han.lanshan.system.entry.Menu;
import com.han.lanshan.system.entry.User;
import com.han.lanshan.system.service.IMenuService;
import com.han.lanshan.system.service.IUserService;

/**
 * 描述： 自定义的认证授权类 创建人：HYD 
 * 创建时间：2019年9月1日 下午10:42:41 
 * 修改人：HYD 
 * 修改时间：2019年9月1日 下午10:42:41 
 * 修改备注：
*/
@Configuration
public class MyShiroRealm extends AuthorizingRealm {

	private static final Logger logger = Logger.getLogger(MyShiroRealm.class);

	@Autowired
	private IUserService userService;
	@Resource
	private IMenuService menuService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		User user = (User) principalCollection.getPrimaryPrincipal();

		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("userId", user.getId());
			List<Menu> firstMenuList = menuService.findAuthMenuByUser(paramMap);
			for (Menu menu : firstMenuList) {
				if (StringUtils.isNotBlank(menu.getUrl())) {
					simpleAuthorInfo.addStringPermission(menu.getUrl());
					System.out.println("menu.getUrl(): " + menu.getUrl());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return simpleAuthorInfo;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		char[] password = token.getPassword();
		String username = token.getUsername();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("username", username);
		parameterMap.put("password", new String(password));
		User user = userService.selectUserByNameAndPwd(parameterMap);
		if (user == null) {
			throw new AuthenticationException("用户名或密码不正确");
		}

		/*
		 * 第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象 第二个参数必须放密码， 第三个参数放
		 * 当前realm的名字，因为可能有多个realm
		 */
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());

		// 清之前的授权信息
		// super.clearCachedAuthorizationInfo(authcInfo.getPrincipals());
		SecurityUtils.getSubject().getSession().setAttribute("user", user);
		return authcInfo;
	}

}
