package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.util.CustomTimestampEditor;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/login.html")
	public String loginUI(){
		return "background/login";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(String username,String password,HttpServletRequest request){
		if(username.equals("admin") && password.equals("123456")){
			request.getSession().setAttribute("admin", username);
			return "redirect:/admin/index.html";
		}
		request.setAttribute("error", "用户名或者密码有误！");
		return "forward:/admin/login.html";
	}
	
	@RequestMapping("/index.html")
	public String indexUI(){
//		Map model = new HashMap();
//		model.put(key, value);
//		ModelAndView mav = new ModelAndView("/background/index", model);
		System.out.println("enter..");
		return "background/index";
	}
	
	@InitBinder//传递对象属性的时候，如果里面属性的类型是date或者是timestamp类型的话，就会出现获取不到的情况,只需要把改方法放在对应的类中就行
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datetimeFormat.setLenient(false);
		//自动转换日期类型的字段格式
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomTimestampEditor(datetimeFormat, true));
	}
}
