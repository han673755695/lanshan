package com.han.lanshan.system.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.han.lanshan.system.entry.User;

/**
 * 当前登录用户信息,可以在bean中调用获取当前登录用户信息,例如 SessionUser.getUserId()获取当前登录人的userId
 * 
 * @copyright {@link weicms.net}
 * @author springrain<9iuorg@gmail.com>
 * @version 2013-03-19 11:08:15
 * @see org.springrain.frame.common.SessionUser
 */
public class SessionUser{
	private static final Logger logger = LoggerFactory.getLogger(SessionUser.class);

	private SessionUser() {
		throw new IllegalAccessError("工具类不能实例化");
	}

	public static User getUser() {

		try {

			User user = (User) getSession().getAttribute("user");
			if (user == null) {
				return null;
			}
			
			return user;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static Session getSession() {
		try {
			Subject user = SecurityUtils.getSubject();
			if (user == null) {
				return null;
			}
			Session session = user.getSession();
			return session;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String getUserId() {
		try {
			User user = getUser();
			if (user == null) {
				return null;
			}
			return user.getId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String getUserType() {
		User user = getUser();
		if (user == null) {
			return null;
		}
		return user.getUserType();
	}

	public static String getUserCode() {
		User user = getUser();
		if (user == null) {
			return null;
		}
		return user.getAccount();

	}

	public static String getUserName() {
		User user = getUser();
		if (user == null) {
			return null;
		}
		return user.getName();
	}

	public static String getEmail() {
		User user = getUser();
		if (user == null) {
			return null;
		}
		return user.getEmail();
	}

}
