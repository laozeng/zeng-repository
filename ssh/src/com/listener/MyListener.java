package com.listener;

import java.text.MessageFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * ServletContextListener-->监听整个服务器（tomcat）启动，关闭
 * ServletContextAttributeListener-->监听application的属性增加，替换，移除等事件
 * HttpSessionAttributeListener-->监听session的属性增加，替换，移除等
 * ServletRequestAttributeListener-->监听request的属性添加，替换，移除等
 * HttpSessionListener-->监听session的创建和销毁(只要用户一访问网站的任何一个页面都会创建一个session，如果已经创建那么后面的就不会再次创建session了)
 * @author user
 */
public class MyListener implements HttpSessionListener,ServletContextListener{
	
	private int count = 0;
	private ServletContext servletContext = null;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(10);
		System.out.println(MessageFormat.format("id为【{0}】的session对象创建了！", session.getId()));
		servletContext.setAttribute("count", ++count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(MessageFormat.format("id为【{0}】的session对象销毁了！", se.getSession().getId()));
		servletContext.setAttribute("count", --count);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		servletContext = null;
	}
	//ServletContextListener,ServletContextAttributeListener,
	//HttpSessionAttributeListener,ServletRequestAttributeListener,
	/*//========================服务器监听(start)=============================
	//tomcat服务器关闭时触发
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyListener...contextDestroyed...tomcat服务器关闭了...");
	}

	//tomcat服务器启动时触发(最先触发，比servlet,inteceptor,filter都早)
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyListener...contextInitialized...tomcat服务器打开了....");
	}
	//========================服务器监听(end)===============================
	

	//========================application监听(start)==========================
	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		System.out.println(MessageFormat.format("MyListener...application作用域中添加了名称为【{0}】，值为【{1}】的对象", scab.getName(),scab.getValue()));
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println(MessageFormat.format("MyListener...application作用域中移除了名称为【{0}】，值为【{1}】的对象", scab.getName(),scab.getValue()));
	}
	
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println(MessageFormat.format("MyListener...application作用域中替换了名称为【{0}】，值为【{1}】的对象", scab.getName(),scab.getValue()));
	}
	//========================application监听(end)==========================
	

	//========================session监听(start)============================
	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("MyListener...session作用域中添加了名称为【{0}】，值为【{1}】的对象", sbe.getName(),sbe.getValue()));
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("MyListener...session作用域中移除了名称为【{0}】，值为【{1}】的对象", sbe.getName(),sbe.getValue()));
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("MyListener...session作用域中替换了名称为【{0}】，值为【{1}】的对象", sbe.getName(),sbe.getValue()));
	}
	//========================session监听(end)============================

	
	//========================request监听(start)==========================
	@Override
	public void attributeAdded(ServletRequestAttributeEvent rab) {
		System.out.println(MessageFormat.format("MyListener...request作用域中添加了名称为【{0}】，值为【{1}】的对象", rab.getName(),rab.getValue()));
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent rab) {
		System.out.println(MessageFormat.format("MyListener...request作用域中移除了名称为【{0}】，值为【{1}】的对象", rab.getName(),rab.getValue()));
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent rab) {
		System.out.println(MessageFormat.format("MyListener...request作用域中替换了名称为【{0}】，值为【{1}】的对象", rab.getName(),rab.getValue()));
	}
	//========================request监听(end)==========================
*/}
