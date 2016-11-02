package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
	//到公共页面去
	@RequestMapping("/common.html")
	public String commonPageUI(){
		return "security/common";
	}
	
	//到登录页面
	@RequestMapping("/login.html")
	public String loginUI(){
		return "security/login";
	}
	
	//处理登录操作
	@RequestMapping("/doLogin")
	public ModelAndView doLogin(String username,String password,HttpServletRequest request){
		ModelAndView mav = null;
		if(username.equals("admin") && password.equals("123456")){
			HttpSession session = request.getSession();
			session.setAttribute("admin", username);
			mav = new ModelAndView("redirect:/admin.html");
			return mav;
		}else{
			String tip = "你输入的用户名或者密码有误！";
			mav = new ModelAndView("forward:/login.html");
			mav.addObject("tip", tip);
			return mav;
		}
	}
	
	//退出登录操作
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		return "redirect:/login.html";
	}
	
	//到只有管理员才能进入的页面
	@RequestMapping("/admin.html")
	public String adminUI(){
		return "security/admin";
	}
	
	//挤出下线方法
	@RequestMapping("/outline")
	public String outline(){
		return "security/outline";
	}
}
