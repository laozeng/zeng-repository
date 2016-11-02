package com.action;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.service.*;
import com.entity.*;

import java.util.*;

@Controller
public class UserAction extends BaseAction{ // 如果要使用 token防止表单重复提交，那么该action必须继承  ActionSupport
	@Autowired
	private UserService userService;
	private List<User> list ;
	private User user;
	private int user_id;
	private int count = 0;
	
	public UserAction(){
		count++;
		System.out.println("enter...,次数："+count);
	}
	
	//到首页方法
	public String indexUI(){
		return "indexUI";
	}
	
	public String user_list(){
		list = userService.getUsers();
		return "user_list";
	}
	
	public String add_user(){
		return "add_user";
	}
	
	public String update_user(){
		user = userService.getUserById(user_id);
		return "update_user";
	}
	
	public String save_user(){
		try {
			if(user != null){
				if(user.getUserId() == null){
					//保存用户
					userService.addUser(user);
				}else{
					//修改用户
					User oldUser = userService.getUserById(user.getUserId());
					oldUser.setUsername(user.getUsername());
					oldUser.setPassword(user.getPassword());
					oldUser.setUserPhone(user.getUserPhone());
					oldUser.setUserGender(user.getUserGender());
					oldUser.setUserAge(user.getUserAge());
					userService.updateUser(oldUser);
				}
			}
			return "save_user";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save_user_fail";
	}
	
	public String delete_user(){
		try {
			user = userService.getUserById(user_id);
			userService.deleteUser(user);
			return "delete_user";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete_user_fail";
	}
	
	public String loginUI(){
		/*if(!"".equals(result)){
			ActionContext context = ActionContext.getContext();
			context.put("error", result);
			result = "";
		}*/
		return "loginUI";
	}
	
	public String doLogin(){
		if("zengchang".equals(user.getUsername()) && "123456".equals(user.getPassword())){
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return "success";
		}else{
//			result = "您输入的账户不存在或者密码有误！";
			return "fail";
		}
	}

	public java.util.List<com.entity.User> getList() {
		return list;
	}

	public void setList(java.util.List<com.entity.User> list) {
		this.list = list;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
