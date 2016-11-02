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
.tit{
  	font-size: 20px;
  	color:red;
}
</style>
</head>
<body> 
    <!-- 该页面是由struts2异常处理进来的 -->
	<div style="margin:10px auto;width:80%">
		<img src="<%=basePath %>image/505.png" style="margin:20px auto 0 auto"/>
		<h3>服务器出错，正在修理中<a href="<%=basePath %>">返回首页</a></h3>
		<h3>错误信息如下：</h3>
		<p class="tit">
			${exception }
		</p>
	</div>
</body>
</html>
