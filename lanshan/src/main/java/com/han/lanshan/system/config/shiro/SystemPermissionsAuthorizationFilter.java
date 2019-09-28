package com.han.lanshan.system.config.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 
* 描述：   
* 创建人：HYD
* 创建时间：2019年9月1日 下午10:39:19   
* 修改人：HYD
* 修改时间：2019年9月1日 下午10:39:19
* 修改备注：   后台统一的权限判断类
* @version
 */
@Component("systemPermissions")
public class SystemPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		if (uri.endsWith("/pre")) {// 去掉pre
			uri = uri.substring(0, uri.length() - 4);
		}
		if (uri.endsWith("/json")) {// 去掉json
			uri = uri.substring(0, uri.length() - 5);
		}
		if (uri.endsWith("/more")) {// 去掉more
			uri = uri.substring(0, uri.length() - 5);
		}
		int i = uri.indexOf(contextPath);
		if (i > -1) {
			uri = uri.substring(i + contextPath.length());
		}
		if (StringUtils.isBlank(uri)) {
			uri = "/";
		}

		boolean permitted = false;
		if ("/".equals(uri)) {
			permitted = true;
		} else {
			permitted = subject.isPermitted(uri);
		}

		return permitted;
//		return permitted;
	}
	
	/**
	 * springboot会把所有的filter列为平级,造成shiro的子拦截器和shiroFilter同级,<br>
	  * 造成访问异常,所以shiro的子Filter需要手动disable
	 * 
	 * @param filter
	 * @return
	 */

	@Bean("disableSystemPermissionsAuthorizationFilter")
	public FilterRegistrationBean<SystemPermissionsAuthorizationFilter> disableFramePermissionsAuthorizationFilter(
			SystemPermissionsAuthorizationFilter filter) {
		FilterRegistrationBean<SystemPermissionsAuthorizationFilter> registration = new FilterRegistrationBean<SystemPermissionsAuthorizationFilter>(
				filter);
		registration.setEnabled(false);
		return registration;
	}
	
}
