<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>哦，出错了</title> 
<style type="text/css">
</style>
</head>
<body> 
	<div class="ctn05 h500 bg_white mt50" align="center">
		<img src="<%=basePath %>upload/505.png" style="margin:20px auto 0 auto"/>
		<h3>服务器出错，正在修理中<a href="<%=basePath %>">返回首页</a></h3>
	</div>
</body>
</html>
