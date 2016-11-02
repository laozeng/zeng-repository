<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML>
<html>
<head>
<title>freemarker.jsp</title>
</head>
<body>
  <div>
  <h1>name:${name}</h1>
	<#assign arr=["one","two","three"] />
	<#list arr as str>
		${str} <br/>
	</#list>
	
  	<table border="1" style="text-align: center;" align="center" >
		<tr id="first_tr">
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>电话</td>
		</tr>
		<#list users as user>
			<tr>
				<td>${user_index}</td>
				<td>${user.name}</td>
				<td>${user.age}</td>
				<td>${user.phone}</td>
			</tr>
		</#list>
	</table>
  </div>
</body>
</html>		