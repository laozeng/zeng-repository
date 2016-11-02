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
	 * helloWorld 为前台页面输入的链接路径
	 * 由org.springframework.web.servlet.view.InternalResourceViewResolver视图解析器解析为实际资源路径名称
	 * 解析规律：  prefix(前缀：WEB-INF/views/) + returnValue(方法返回值：success) + suffix(后缀：.jsp) -->WEB-INF/views/success.jsp
	 * @return
	 */
	@RequestMapping("/helloWorld")
	public String helloWorld(){
		System.out.println("hello world!");
		return "success";
	}
	
	/**
	 * 如果参数中的类型为int float double char boolean byte short long等基本数据类型的话，那么在请求的路径中必须包含该参数类型
	 * 如果参数中的类型为Integer Float Double Char Boolean Byte Short Long等包装类型的话，那么路径中不一定要包含改参数，系统将会认为该参数的值为null
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
	 * 支持对象参数，以及级联属性的传递 
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
//		return "redirect:WEB-INF/views/success.jsp";//注意：如果文件在WEB-INF/目录之下，那么重定向请求不到你需要的页面
		return "redirect:success.jsp";
	}
	
	@RequestMapping("/forwardPage.htm")//可以改变url的后缀
	public String forwardPage(){
		System.out.println("forwardPage");
		return "forward:WEB-INF/views/success.jsp";
	}
	
	@RequestMapping("/testServletAPI.htm")//原生态的servlet api接口也可以作为参数
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
	 *  使用springmvc模拟数据库进行CRUD操作 
	 * 
	 */
	public static List<User> list = new ArrayList<User>();
	
	static{
//		list.add(new User("aaa1","22815586561@qq.com",12,new Date(),new Address("湖北1", "孝感1")));
//		list.add(new User("aaa2","22815586562@qq.com",22,new Date(),new Address("湖北2", "孝感2")));
//		list.add(new User("aaa3","22815586563@qq.com",32,new Date(),new Address("湖北3", "孝感3")));
//		list.add(new User("aaa4","22815586564@qq.com",42,new Date(),new Address("湖北4", "孝感4")));
	}
	
	@RequestMapping("/listPage.htm")
	public ModelAndView listPage(){
//		User user = new User("aaa1","22815586561@qq.com",12,new Date(),new Address("湖北1", "孝感1"));
		User user = null;
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("list", list);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("deleteUser/{id}")//rest 风格的url路径
	public String deleteUser(@PathVariable() Integer id){
		System.out.println("获取用户编号--->"+id+"，执行删除操作");
		System.out.println("删除之前的个数："+list.size());
		list.remove(id-1);
		System.out.println("删除之后的个数："+list.size());
		return "redirect:/listPage.htm";//相当于Struts中的RedirectAction,跳转到其他方法中继续执行其他的操作
	}
	
	@RequestMapping(value="editUser/{id}",method = RequestMethod.GET)//rest 风格的url路径  点击a标签过来的，默认是get请求
	public ModelAndView editUser(@PathVariable Integer id ){
		System.out.println("进入获取用户模块，编号为："+id);
		ModelAndView mav = new ModelAndView("edit");
		mav.addObject("user", list.get(id-1));
		mav.addObject("index", id);
		return mav;
	}
	
	@RequestMapping(value="/editUser/{id}",method = RequestMethod.POST)//表单提交过来的，post请求
	public String editUser(User user){
		System.out.println("获取用户信息--->"+user+"，执行修改操作");
		return "redirect:/listPage.htm";
	}
	@InitBinder  //处理日期类型的响应不到的错误
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
	}
	
	
	@RequestMapping(value="/addUser",method = RequestMethod.GET)//点击a标签过来的，默认是get请求
	public String addUser(){
		return "add";
	}
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)//提交表单过来的，是post请求
	public String addUser(User user){
		System.out.println("进入添加模块，待添加的用户："+user);
		System.out.println("添加之前的个数："+list.size());
		list.add(user);
		System.out.println("添加之后的个数："+list.size());
		return "redirect:/listPage.htm";
	}
}
