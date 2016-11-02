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
 * dwr����  �ο�·���� http://www.cnblogs.com/yokoboy/archive/2013/08/09/3249383.html 
 * 
 * DWR��Direct Web Remoting��
   DWR is a Java library that enables Java on the server and JavaScript in a browser to interact and call each other as simply as possible.
   Dwr�����ڷ������˵�java�����������ͻ��˵�javascript���뾡���ܼ򵥵��໥���á�
 * 
 * ����dwr�������ĸ�Ҫ�㣺
  1.����dwr�ܰ������ܰ�����libĿ¼֮��
  2.��web.xml��������ص�servlet�����£�
     <servlet>
  	<servlet-name>dwrServlet</servlet-name>
  	<servlet-class>uk.ltd.getahead.dwr.DWRServlet</servlet-class>
  	<!-- ���session error������ -->
  	<init-param>
	    <param-name>crossDomainSessionSecurity</param-name>
	    <param-value>false</param-value>
   </init-param>
  	<!-- tomcat����������ʱ����ʼ��dwrServlet���servlet -->
  	<load-on-startup>0</load-on-startup>
   </servlet>
    <servlet>
  3.��web-infĿ¼֮�±�дdwr.xml�ļ�(����������ص��ָ࣬����Щ����Ա�ǰ̨����)
  4.ҳ�������
 */
@Controller
public class DWRAction {
	//ʹ��map���������ںͷ�����һ��
	private static Map<Integer, String> map = new HashMap<Integer, String>();
	private static int index  = 0;
	
	public String dwrUI(){
		return "dwrUI";
	}
	
	public List<User> getUsers(){
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User("����"+i, "�人"+i, new Date());
			list.add(user);
		}
		return list;
	}
	
	//���Ԫ��
	public boolean save(String string){
		if(!map.containsValue(string)){
			map.put(index, string);
			index ++;
			return true;
		}else{
			return false;
		}
	}
	
	//Ѱ��Ԫ��
	public String findString(Integer index){
		String temp = map.get(index);
		return temp;
	}
	
	//���뵽ʡ�м���ģ��
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
