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
@SessionAttributes("user")  //ʹuser�ķ��ʼ����Ϊsession,���Կ��������(ʹ��redirectʱ�򲻱�����ʹ��forwardʱ�ᱨ����֪��ɶԭ��?)
public class OneController {
	
	public OneController(){
		//ע�⣺ÿ��controller�ǵ���ģʽ����һ�η��������������У���ֻ��ʵ����һ��
		System.out.println("enter...");
	}
	
	private String param = "����";
	
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
	
	@RequestMapping(value = "/one.html", method=RequestMethod.GET) //ע�⣺�����������ƿ�����ͬ�������ʵ�·��������ͬ
	public String oneUI(Integer id,String name,String type,ModelAndView mav,ModelMap model){ //�˴�ʹ��ModelҲ����
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
	
	//ת��
	@RequestMapping("/forwardToOne.html")
	public String forwardToOne(HttpServletRequest request){
		System.out.println("enter...");
		//�˴�������Ŀ���ƻ��߲��Ӷ���,������Ҫ�ӿ���������(���������з���֮�����ת)
		return "forward:"+request.getContextPath()+"/oneController/one.html?id="+1+"&name="+"����&type="+"forward";
	}
	
	//�ض���(�����Ҫ�ڷ���֮�䴫�ݲ����Ļ������Զ���һ��ȫ�ֵı�������ΪĬ������£�springmvc�еĿ��������ǵ���ģʽ����һ�����������������ڣ�һ��������ֻ��ʵ����һ��)
	@RequestMapping("/redirectToOne.html")
	public ModelAndView redirectToOne(HttpServletRequest request){
		//�˴�������Ŀ���ƻ��߲��Ӷ���,������Ҫ�ӿ���������
		ModelAndView mav = new ModelAndView("redirect:"+request.getContextPath()+"/oneController/one.html?id="+1+"&type="+"redirect");
		mav.addObject("name", "����");
		param = "ɵ��";
		return mav;
	}
}
