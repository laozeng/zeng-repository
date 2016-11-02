package com.shiro.controller;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dao.UserDao;
import com.shiro.entity.User;

/**
 * 自定义realm，获取AuthorizationInfo和AuthenticationInfo
 * @author user
 */
public class MyShiroRealm extends AuthorizingRealm {
	private UserDao userDao;

	//获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		//获取用户名
		String username  = (String) pc.fromRealm(getName()).iterator().next();
		//根据用户名获取对应的权限
		List<String> urls = userDao.getPermissionByUsername(username);
		//将权限资源添加到用户信息中
		if(urls != null && urls.size() > 0){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (String url : urls) {
				info.addStringPermission(url);
			}
			return info;
		}
		return null; 
	}
	
	//获取验证信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken at) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) at;
		String username = token.getUsername();
		if(username != null && !"".equals(username)){
			User user = userDao.getUserByUsername(username);
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
			return info;
		}
		return null;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
