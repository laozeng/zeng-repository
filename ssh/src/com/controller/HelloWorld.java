package com.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import com.entity.Address;
import com.entity.User;

@Controller
public class HelloWorld {
	
	/**
	 * helloWorld Ϊǰ̨ҳ�����������·��
	 * ��org.springframework.web.servlet.view.InternalResourceViewResolver��ͼ����������Ϊʵ����Դ·������
	 * �������ɣ�  prefix(ǰ׺��WEB-INF/views/) + returnValue(��������ֵ��success) + suffix(��׺��.jsp) -->WEB-INF/views/success.jsp
	 * @return
	 */
	@RequestMapping("/helloWorld")
	public String helloWorld(){
		System.out.println("hello world!");
		return "success";
	}
	
	/**
	 * ��������е�����Ϊint float double char boolean byte short long�Ȼ����������͵Ļ�����ô�������·���б�������ò�������
	 * ��������е�����ΪInteger Float Double Char Boolean Byte Short Long�Ȱ�װ���͵Ļ�����ô·���в�һ��Ҫ�����Ĳ�����ϵͳ������Ϊ�ò�����ֵΪnull
	 * @return
	 */
	@RequestMapping("/paramMethod")
	public String paramMethod(@RequestParam(value="username") String username,String password,Integer age,User user,Date date,Double number){
		System.out.println("paramMethod!username:"+username+",password:"+password+",age:"+age+",user:"+user+",date:"+date+",number:"+number);
//		System.out.println("paramMethod!username:"+username+",password:"+password+",age:"+age);
		return "success";
	}
	
	@RequestMapping("/testPariableMethod/{id}")
	public String testPariableMethod(@PathVariable(value="id") Integer id){
		System.out.println("testPariableMethod,   id--->"+id);
		return "success";
	}
	
	/**
	 * ֧�ֶ���������Լ��������ԵĴ��� 
	 * @return
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("user:"+user);
		return "success";
	}
	
	@RequestMapping("/redirectPage")
	public String redirectPage(){
		System.out.println("redirectPage");
//		return "redirect:WEB-INF/views/success.jsp";//ע�⣺����ļ���WEB-INF/Ŀ¼֮�£���ô�ض������󲻵�����Ҫ��ҳ��
		return "redirect:success.jsp";
	}
	
	@RequestMapping("/forwardPage.htm")//���Ըı�url�ĺ�׺
	public String forwardPage(){
		System.out.println("forwardPage");
		return "forward:WEB-INF/views/success.jsp";
	}
	
	@RequestMapping("/testServletAPI.htm")//ԭ��̬��servlet api�ӿ�Ҳ������Ϊ����
	public String testServletAPI(HttpServletResponse response,HttpServletRequest request){
		System.out.println("response:"+response);
		System.out.println("request:"+request);
		return "success";
	}
	
	@RequestMapping("/testAjax")
	public void testAjax(HttpServletResponse response,String param1,String param2) throws Exception{
		PrintWriter pw = response.getWriter();
		System.out.println("param1:"+param1+",param2:"+param2);
		if(param1.equals("admin") && param2.equals("123456")){
			pw.print("success");
		}else{
			pw.print("fail");
		}
	}
	
	
	/*
	 *  ʹ��springmvcģ�����ݿ����CRUD���� 
	 * 
	 */
	public static List<User> list = new ArrayList<User>();
	
	static{
//		list.add(new User("aaa1","22815586561@qq.com",12,new Date(),new Address("����1", "Т��1")));
//		list.add(new User("aaa2","22815586562@qq.com",22,new Date(),new Address("����2", "Т��2")));
//		list.add(new User("aaa3","22815586563@qq.com",32,new Date(),new Address("����3", "Т��3")));
//		list.add(new User("aaa4","22815586564@qq.com",42,new Date(),new Address("����4", "Т��4")));
	}
	
	@RequestMapping("/listPage.htm")
	public ModelAndView listPage(){
//		User user = new User("aaa1","22815586561@qq.com",12,new Date(),new Address("����1", "Т��1"));
		User user = null;
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("list", list);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("deleteUser/{id}")//rest ����url·��
	public String deleteUser(@PathVariable() Integer id){
		System.out.println("��ȡ�û����--->"+id+"��ִ��ɾ������");
		System.out.println("ɾ��֮ǰ�ĸ�����"+list.size());
		list.remove(id-1);
		System.out.println("ɾ��֮��ĸ�����"+list.size());
		return "redirect:/listPage.htm";//�൱��Struts�е�RedirectAction,��ת�����������м���ִ�������Ĳ���
	}
	
	@RequestMapping(value="editUser/{id}",method = RequestMethod.GET)//rest ����url·��  ���a��ǩ�����ģ�Ĭ����get����
	public ModelAndView editUser(@PathVariable Integer id ){
		System.out.println("�����ȡ�û�ģ�飬���Ϊ��"+id);
		ModelAndView mav = new ModelAndView("edit");
		mav.addObject("user", list.get(id-1));
		mav.addObject("index", id);
		return mav;
	}
	
	@RequestMapping(value="/editUser/{id}",method = RequestMethod.POST)//���ύ�����ģ�post����
	public String editUser(User user){
		System.out.println("��ȡ�û���Ϣ--->"+user+"��ִ���޸Ĳ���");
		return "redirect:/listPage.htm";
	}
	@InitBinder  //�����������͵���Ӧ�����Ĵ���
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
	}
	
	
	@RequestMapping(value="/addUser",method = RequestMethod.GET)//���a��ǩ�����ģ�Ĭ����get����
	public String addUser(){
		return "add";
	}
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)//�ύ�������ģ���post����
	public String addUser(User user){
		System.out.println("�������ģ�飬����ӵ��û���"+user);
		System.out.println("���֮ǰ�ĸ�����"+list.size());
		list.add(user);
		System.out.println("���֮��ĸ�����"+list.size());
		return "redirect:/listPage.htm";
	}
}
