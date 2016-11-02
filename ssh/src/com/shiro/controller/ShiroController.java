package com.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shiro.dao.UserDao;
import com.shiro.entity.User;

/**
 * shiro测试：
 * 参考路径：http://blog.csdn.net/swingpyzf/article/details/46342023
 * http://www.cppblog.com/guojingjia2006/archive/2014/05/14/206956.html
 * @author user
 *
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {
	@Autowired
	private UserDao userDao;
	//到登录页面
	@RequestMapping("/loginUI.mvc")
	public ModelAndView loginUI(String error,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("shiro/login");
		if(error != null && !"".equals(error)){
			mav.addObject("error", error);
		}
		return mav;
	}
	
	//处理登录页面
	@RequestMapping("/doLogin.mvc")
	public ModelAndView doLogin(String username,String password,RedirectAttributes redirectAttributes){
		ModelAndView mav = new ModelAndView("redirect:/shiro/adminUI.mvc");
		User user = userDao.getUserByUsername(username);
		if(user == null){
			mav.setViewName("redirect:/shiro/loginUI.mvc");
			redirectAttributes.addFlashAttribute("error", "该账户不存在！");
			return mav;
		}
		if(!user.getPassword().equals(password)){
			mav.setViewName("redirect:/shiro/loginUI.mvc");
			redirectAttributes.addFlashAttribute("error", "该账户密码有误！");
			return mav;
		}
		//清空之前的用户信息
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
		//登录成功之后放入shiro token中
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return mav;
	}
	
	//退出登录
	@RequestMapping("/logout.mvc")
	public ModelAndView logout(HttpServletRequest request){
		//清掉shiro中的用户信息
		Subject subject = SecurityUtils.getSubject();
		SecurityUtils.getSecurityManager().logout(subject);
		//清楚session中的用户信息
		request.getSession().removeAttribute("user");
		ModelAndView mav = new ModelAndView("redirect/shiro/loginUI.mvc");
		return mav;
	}
	
	//到管理员页面
	@RequestMapping("/adminUI.mvc")
	public ModelAndView adminUI(){
		ModelAndView mav = new ModelAndView("shiro/admin");
		return mav;
	}
	@RequestMapping("/deleteStudent.mvc")
	public ModelAndView deleteStudent(){
		ModelAndView mav = new ModelAndView("");
		System.out.println("enter...");
		return mav;
	}
	
	//跳转到没有权限页面
	@RequestMapping("/notPermission.mvc")
	public ModelAndView notPermissionUI(){
		ModelAndView mav = new ModelAndView("shiro/403");
		return mav;
	}
}
