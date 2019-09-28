package com.han.lanshan.system.config.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jagregory.shiro.freemarker.ShiroTags;

/**
 * shiro配置类 
  * 描述： 创建人：HYD <br>
  *  创建时间：2019年9月1日 下午10:42:27 <br>
  *  修改人：HYD <br>
  *  修改时间：2019年9月1日 下午10:42:27 <br>
  *  修改备注：
 * @version
 */
@Configuration
public class ShiroConfig {
	
	@Autowired
	MyShiroRealm myShiroRealm;
	@Autowired
	private Filter systemPermissions;
	
	/**
	 * shiro的主拦截器
	 * 
	 * @return
	 */

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 设置自定义的过滤器,也就是子拦截器
		shiroFilterFactoryBean.setFilters(getFilters());
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 退出
		map.put("/system/login/logout", "logout");
		// 静态资源
		map.put("/system/**", "anon");
		
		
		// 登陆接口
		map.put("/s/system/login/login", "anon");
		map.put("/s/system/login/toLogin", "anon");
		map.put("/s/system/login/index", "anon");
		
		//自动生成代码
		map.put("/generator/createFile", "anon");
		//map.put("/s/menu/**", "anon");

		map.put("/unauthorized", "anon");
		// 自定义的拦截器 拦截
		map.put("/s/**", "systemPermissions");
		
		map.put("/**", "authc");
		
		// 登录页面
		shiroFilterFactoryBean.setLoginUrl("/s/system/login/toLogin");
		// 首页
		shiroFilterFactoryBean.setSuccessUrl("/s/system/login/index");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

		return shiroFilterFactoryBean;
	}
	
	/**
	 * 
	  * 描述：   自定义的拦截器
	  * 创建人：HYD
	  * 创建时间：2019年9月8日 下午11:06:36   
	  * 修改人：HYD
	  * 修改时间：2019年9月8日 下午11:06:36
	  * 修改备注：   
	 * @return
	 * @version
	 */
	private Map<String, Filter> getFilters() {
		Map<String, Filter> filters = new HashMap<>();
		// 权限校验的过滤器
		filters.put("systemPermissions", systemPermissions);

		return filters;
	}
	

	/**
	 * 权限管理器
	 * 
	 * @return
	 */
	@Bean("securityManager")
	public org.apache.shiro.mgt.SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		myShiroRealm.setCachingEnabled(true);
		// 不缓存认证(会有问题)
		myShiroRealm.setAuthenticationCachingEnabled(false);
		// 缓存授权
		myShiroRealm.setAuthorizationCachingEnabled(true);
		// 数据库认证的实现
		securityManager.setRealm(myShiroRealm);
		// session 管理器
		securityManager.setSessionManager(sessionManager());
		// 缓存管理器
		securityManager.setCacheManager(shiroCacheManager());
		return securityManager;
	}

	/**
	 * session管理器
	 * 
	 * @return
	 */
	@Bean("sessionManager")
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// URL重写中去掉jsessionId
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		// 超时时间
		sessionManager.setGlobalSessionTimeout(1800000L);
		// 定时检查失效的session,默认true
		sessionManager.setSessionValidationSchedulerEnabled(true);
		// 删除过期的session,默认true
		sessionManager.setDeleteInvalidSessions(true);
		// 相隔多久检查一次session的有效性,使用默认的60分钟
		// sessionManager.setSessionValidationInterval(cacheTimeOut);
		// session存储的实现
		sessionManager.setSessionDAO(shiroSessionDAO());
		// sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
		sessionManager.setSessionIdCookie(shiroSessionIdCookie());

		return sessionManager;
	}

	/**
	 * session存储的实现
	 * 
	 * @return
	 */
	@Bean("shiroSessionDAO")
	public SessionDAO shiroSessionDAO() {
		EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
		return sessionDAO;
	}

	/**
	 * sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
	 * 
	 * @return
	 */
	@Bean("shiroSessionIdCookie")
	public SimpleCookie shiroSessionIdCookie() {
		SimpleCookie sessionIdCookie = new SimpleCookie();
		// cookie的name,对应的默认是 JSESSIONID
		sessionIdCookie.setName("SHAREJSESSIONID");
		// more secure, protects against XSS attacks
		sessionIdCookie.setHttpOnly(true);
		// jsessionId的path为 / 用于多个系统共享jsessionId
		sessionIdCookie.setPath("/");

		return sessionIdCookie;
	}

	/**
	 * 单机session
	 * 
	 * @return
	 */
	@Bean("shiroCacheManager")
	public CacheManager shiroCacheManager() {
		MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
		return cacheManager;
	}


	@Bean("lifecycleBeanPostProcessor")
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	
	// shiro在页面中使用标签
	@Bean("configuration")
	public freemarker.template.Configuration setConfiguration(freemarker.template.Configuration cong) {
		cong.setSharedVariable("shiro", new ShiroTags());
		return cong;
	}

}
