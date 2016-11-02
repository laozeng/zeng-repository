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
<title>登录页面</title>
</head>
<body>
	<h1 align="center">用户登录</h1>
	<!-- springmvc -->
	<h3 align="center">springmvc登录</h3>
	<form action="<%=basePath%>shiro/doLogin.mvc" method="post">
		<table style="margin:10px auto;" border="0">
		<tr>
			<td><span>用户名：</span></td>
			<td><input type="text" value="zengchang" name="username"/><br /></td>
		</tr>
		<tr>
			<td><span>密码：</span></td>
			<td><input type="password" value="123456" name="password"/><br /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="color:red;">${error }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="登录"/>&nbsp;&nbsp;
				<input type="reset" value="重置"/>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	</form> 
</body>
</html>