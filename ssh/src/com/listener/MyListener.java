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
 * ServletContextListener-->����������������tomcat���������ر�
 * ServletContextAttributeListener-->����application���������ӣ��滻���Ƴ����¼�
 * HttpSessionAttributeListener-->����session���������ӣ��滻���Ƴ���
 * ServletRequestAttributeListener-->����request��������ӣ��滻���Ƴ���
 * HttpSessionListener-->����session�Ĵ���������(ֻҪ�û�һ������վ���κ�һ��ҳ�涼�ᴴ��һ��session������Ѿ�������ô����ľͲ����ٴδ���session��)
 * @author user
 */
public class MyListener implements HttpSessionListener,ServletContextListener{
	
	private int count = 0;
	private ServletContext servletContext = null;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(10);
		System.out.println(MessageFormat.format("idΪ��{0}����session���󴴽��ˣ�", session.getId()));
		servletContext.setAttribute("count", ++count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(MessageFormat.format("idΪ��{0}����session���������ˣ�", se.getSession().getId()));
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
	/*//========================����������(start)=============================
	//tomcat�������ر�ʱ����
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyListener...contextDestroyed...tomcat�������ر���...");
	}

	//tomcat����������ʱ����(���ȴ�������servlet,inteceptor,filter����)
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyListener...contextInitialized...tomcat����������....");
	}
	//========================����������(end)===============================
	

	//========================application����(start)==========================
	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		System.out.println(MessageFormat.format("MyListener...application�����������������Ϊ��{0}����ֵΪ��{1}���Ķ���", scab.getName(),scab.getValue()));
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println(MessageFormat.format("MyListener...application���������Ƴ�������Ϊ��{0}����ֵΪ��{1}���Ķ���", scab.getName(),scab.getValue()));
	}
	
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println(MessageFormat.format("MyListener...application���������滻������Ϊ��{0}����ֵΪ��{1}���Ķ���", scab.getName(),scab.getValue()));
	}
	//========================application����(end)==========================
	

	//========================session����(start)============================
	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("MyListener...session�����������������Ϊ��{0}����ֵΪ��{1}���Ķ���", sbe.getName(),sbe.getValue()));
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("MyListener...session���������Ƴ�������Ϊ��{0}����ֵΪ��{1}���Ķ���", sbe.getName(),sbe.getValue()));
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("MyListener...session���������滻������Ϊ��{0}����ֵΪ��{1}���Ķ���", sbe.getName(),sbe.getValue()));
	}
	//========================session����(end)============================

	
	//========================request����(start)==========================
	@Override
	public void attributeAdded(ServletRequestAttributeEvent rab) {
		System.out.println(MessageFormat.format("MyListener...request�����������������Ϊ��{0}����ֵΪ��{1}���Ķ���", rab.getName(),rab.getValue()));
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent rab) {
		System.out.println(MessageFormat.format("MyListener...request���������Ƴ�������Ϊ��{0}����ֵΪ��{1}���Ķ���", rab.getName(),rab.getValue()));
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent rab) {
		System.out.println(MessageFormat.format("MyListener...request���������滻������Ϊ��{0}����ֵΪ��{1}���Ķ���", rab.getName(),rab.getValue()));
	}
	//========================request����(end)==========================
*/}
