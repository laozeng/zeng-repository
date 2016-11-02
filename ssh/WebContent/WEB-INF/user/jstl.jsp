<%@ page import="com.entity.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jstl页</title>
</head>
<body>
	<%
		/* //application
		application.setAttribute("name", "曾昌");
		application.setAttribute("name", "曾杰");
		application.removeAttribute("name"); 
		
		//session
		session.setAttribute("name", "曾昌");
		session.setAttribute("name", "曾杰");
		session.removeAttribute("name");
		
		//request
		request.setAttribute("name", "曾昌");
		request.setAttribute("name", "曾杰");
		request.removeAttribute("name");
		
		session.setAttribute("曾昌", new UserInfo("曾昌"));
		session.removeAttribute("曾昌"); */
		
// 		session.setAttribute("曾昌", new UserInfo("曾昌"));
	%>
	<!-- 
		访问这个jsp页面，服务器就会马上创建一个HttpSession对象，然后将实现了HttpSessionActivationListener接口的JavaBean对象绑定到session对象中，
		这个jsp页面在等待1分钟之后没有人再次访问，那么服务器就会自动将这个HttpSession对象钝化(序列化)到硬盘上，
		在以下位置----》 C:\apache-tomcat-8.0\localhost-config\work\Catalina\localhost\ssh\session
		当再次访问这个Jsp页面时，服务器又会自动将已经钝化(序列化)到硬盘上HttpSession对象重新活化(反序列化)回到内存中。
	 -->
	 
	<%--jstl语法：http://www.cnblogs.com/lihuiyy/archive/2012/02/24/2366806.html --%>
	<!-- 该session编号在后端可以通过HttpSessionListener监听接口获取！ -->
	<h1>一访问jsp页面，session就创建了，编号为：${pageContext.session.id }</h1>
	<h1 align="center">首页欢迎您！</h1>
	<h1 align="center">webPath:${webPath }</h1>
	<h1 align="center">当前在线人数:${count }</h1>
</body>
</html>