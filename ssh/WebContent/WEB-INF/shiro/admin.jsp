<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=basePath %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<title>admin页面</title>
</head>
<body>
	<h1 align="center">admin才能访问的页面！</h1>
	<h1 align="center"><a href="<%=basePath %>shiro/deleteStudent.mvc">删除学生链接</a></h1>
	<h1 align="center"><a href="<%=basePath %>shiro/logout.mvc">退出登录</a></h1>
</body>
</html>