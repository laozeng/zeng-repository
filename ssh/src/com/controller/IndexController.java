package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
/**
 * ��ҳ��ؿ�����
 * @author user
 */
public class IndexController {
	//������ҳ����
	@RequestMapping("/index")
	public ModelAndView index(){
		System.out.println("enter...");
		return new ModelAndView("index/index");
	}
	
	@RequestMapping("/indexUI")
	public ModelAndView indexUI(){
		System.out.println("indexUI...");
		ModelAndView mav = new ModelAndView("index/index");
		mav.addObject("name", "����");
		return mav;
	}
	
	@RequestMapping("/men.html")
	public String menuUI(){
		return "front/men";
	}
	
	@RequestMapping("/single.html")
	public String singleUI(){
		return "front/single";
	}
	
	@RequestMapping("/blog.html")
	public String blogUI(){
		return "front/blog";
	}
	
	@RequestMapping("/blog_single.html")
	public String blogSingleUI(){
		return "front/blog_single";
	}
	
	@RequestMapping("/contact.html")
	public String contactUI(){
		return "front/contact";
	}
	
	@RequestMapping("/wishlist.html")
	public String wishlistUI(){
		return "front/wishlist";
	}
}
