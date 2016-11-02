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
 * shiro���ԣ�
 * �ο�·����http://blog.csdn.net/swingpyzf/article/details/46342023
 * http://www.cppblog.com/guojingjia2006/archive/2014/05/14/206956.html
 * @author user
 *
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {
	@Autowired
	private UserDao userDao;
	//����¼ҳ��
	@RequestMapping("/loginUI.mvc")
	public ModelAndView loginUI(String error,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("shiro/login");
		if(error != null && !"".equals(error)){
			mav.addObject("error", error);
		}
		return mav;
	}
	
	//�����¼ҳ��
	@RequestMapping("/doLogin.mvc")
	public ModelAndView doLogin(String username,String password,RedirectAttributes redirectAttributes){
		ModelAndView mav = new ModelAndView("redirect:/shiro/adminUI.mvc");
		User user = userDao.getUserByUsername(username);
		if(user == null){
			mav.setViewName("redirect:/shiro/loginUI.mvc");
			redirectAttributes.addFlashAttribute("error", "���˻������ڣ�");
			return mav;
		}
		if(!user.getPassword().equals(password)){
			mav.setViewName("redirect:/shiro/loginUI.mvc");
			redirectAttributes.addFlashAttribute("error", "���˻���������");
			return mav;
		}
		//���֮ǰ���û���Ϣ
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
		//��¼�ɹ�֮�����shiro token��
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return mav;
	}
	
	//�˳���¼
	@RequestMapping("/logout.mvc")
	public ModelAndView logout(HttpServletRequest request){
		//���shiro�е��û���Ϣ
		Subject subject = SecurityUtils.getSubject();
		SecurityUtils.getSecurityManager().logout(subject);
		//���session�е��û���Ϣ
		request.getSession().removeAttribute("user");
		ModelAndView mav = new ModelAndView("redirect/shiro/loginUI.mvc");
		return mav;
	}
	
	//������Աҳ��
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
	
	//��ת��û��Ȩ��ҳ��
	@RequestMapping("/notPermission.mvc")
	public ModelAndView notPermissionUI(){
		ModelAndView mav = new ModelAndView("shiro/403");
		return mav;
	}
}
