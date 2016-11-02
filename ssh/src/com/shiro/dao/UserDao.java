package com.shiro.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.shiro.entity.Permission;
import com.shiro.entity.Role;
import com.shiro.entity.User;

@Repository
public class UserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public User getUserByUsernameAndPassword(String username,String password){
		List<User> users =  (List<User>) hibernateTemplate.find("from User obj where obj.username = ? and obj.password = ?", new Object[]{username,password});
		if(users != null && users.size() > 0){
			return users.get(0);
		}
		return null;
	}
	
	public User getUserByUsername(String username){
		List<User> users =  (List<User>) hibernateTemplate.find("from User obj where obj.username = ?", username);
		if(users != null && users.size() > 0){
			return users.get(0);
		}
		return null;
	}
	
	//通过用户名获取权限资源(主要是对应的路径)
	public List<String> getPermissionByUsername(String username){
		User user = getUserByUsername(username);
		if(user == null){
			return null;
		}
		List<String> urls = new ArrayList<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			int role_id = role.getId();
			List<Permission> permissions = (List<Permission>) hibernateTemplate.find("from Permission obj where obj.role_id = ?", role_id);
			for (Permission permission : permissions) {
				urls.add(permission.getUrl());
			}
		}
		return urls;
	}
}
