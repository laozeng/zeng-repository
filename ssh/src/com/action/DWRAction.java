package com.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.entity.User;

/**
 * dwr例子  参考路径： http://www.cnblogs.com/yokoboy/archive/2013/08/09/3249383.html 
 * 
 * DWR（Direct Web Remoting）
   DWR is a Java library that enables Java on the server and JavaScript in a browser to interact and call each other as simply as possible.
   Dwr能让在服务器端的java代码和浏览器客户端的javascript代码尽可能简单的相互调用。
 * 
 * 调用dwr技术的四个要点：
  1.引入dwr架包，将架包放入lib目录之下
  2.在web.xml中配置相关的servlet，如下：
     <servlet>
  	<servlet-name>dwrServlet</servlet-name>
  	<servlet-class>uk.ltd.getahead.dwr.DWRServlet</servlet-class>
  	<!-- 解决session error的问题 -->
  	<init-param>
	    <param-name>crossDomainSessionSecurity</param-name>
	    <param-value>false</param-value>
   </init-param>
  	<!-- tomcat服务器启动时，初始化dwrServlet这个servlet -->
  	<load-on-startup>0</load-on-startup>
   </servlet>
    <servlet>
  3.在web-inf目录之下编写dwr.xml文件(即是设置相关的类，指定哪些类可以被前台调用)
  4.页面的引用
 */
@Controller
public class DWRAction {
	//使该map的生命周期和服务器一致
	private static Map<Integer, String> map = new HashMap<Integer, String>();
	private static int index  = 0;
	
	public String dwrUI(){
		return "dwrUI";
	}
	
	public List<User> getUsers(){
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User("曾昌"+i, "武汉"+i, new Date());
			list.add(user);
		}
		return list;
	}
	
	//添加元素
	public boolean save(String string){
		if(!map.containsValue(string)){
			map.put(index, string);
			index ++;
			return true;
		}else{
			return false;
		}
	}
	
	//寻找元素
	public String findString(Integer index){
		String temp = map.get(index);
		return temp;
	}
	
	//进入到省市级联模块
	public String proCityUI(){
		return "proCityUI";
	}
	
	public static void main(String[] args) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(format.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
