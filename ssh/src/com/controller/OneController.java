package com.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("oneController/")
@SessionAttributes("user")  //使user的访问级别变为session,可以跨请求访问(使用redirect时候不报错，但使用forward时会报错，不知道啥原因?)
public class OneController {
	
	public OneController(){
		//注意：每个controller是单例模式，即一次服务器开关周期中，它只会实例化一次
		System.out.println("enter...");
	}
	
	private String param = "逗比";
	
	@RequestMapping(value = "/one.html", method=RequestMethod.POST)
	public String oneUI(){
		return "test/one";
	}
	
	/*@ModelAttribute
	public void getParam(@ModelAttribute Integer id,@ModelAttribute String name,@ModelAttribute String type,Model model){
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
	}*/
	
	@RequestMapping(value = "/one.html", method=RequestMethod.GET) //注意：两个方法名称可以相同，但访问的路径不能相同
	public String oneUI(Integer id,String name,String type,ModelAndView mav,ModelMap model){ //此处使用Model也可以
		System.out.println("param:"+param);
		if(type !=null && type.equals("redirect")){
			try {
				//name = new String(name.getBytes("iso8859-1"),"utf-8");
				name =(String) mav.getModel().get("name");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("id:"+id+",name:"+name+",type:"+type);
		Object obj = model.get("user");
		if(obj == null){
			model.addAttribute("user", "com.zengchang");
		}
		return "test/one";
	}
	
	//转发
	@RequestMapping("/forwardToOne.html")
	public String forwardToOne(HttpServletRequest request){
		System.out.println("enter...");
		//此处加上项目名称或者不加都行,但必须要加控制器名称(本控制器中方法之间的跳转)
		return "forward:"+request.getContextPath()+"/oneController/one.html?id="+1+"&name="+"杆子&type="+"forward";
	}
	
	//重定向(如果想要在方法之间传递参数的话，可以定义一个全局的变量，因为默认情况下，springmvc中的控制器都是单例模式，即一个服务器开关周期内，一个控制器只会实例化一次)
	@RequestMapping("/redirectToOne.html")
	public ModelAndView redirectToOne(HttpServletRequest request){
		//此处加上项目名称或者不加都行,但必须要加控制器名称
		ModelAndView mav = new ModelAndView("redirect:"+request.getContextPath()+"/oneController/one.html?id="+1+"&type="+"redirect");
		mav.addObject("name", "杆子");
		param = "傻逼";
		return mav;
	}
}
