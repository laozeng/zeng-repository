<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出错了....</title> 
<link rel="stylesheet" href="<%=basePath %>resource/css/404.css" type="text/css"></link>
<style type="text/css">
.error{
	width:1040px;
	margin:50px auto;
	background:#fff;
	text-align:left;
}
.error_hd{
	width:1000px;
	height:220px;
	padding:20px;
}
.error_hd_ctn{
	width:680px;
	height:250px;
	float:right;
}
.index{
	display:block;
	width:145px;
	height:45px;
	float:left;
	text-align:center;
	line-height:45px;
	color:#fff;
	background:#E74B3E;
	margin:5px;
	font-size:18px;
	border-radius:10px;
}
.back{
	display:block;
	width:145px;
	height:45px;
	float:left;
	text-align:center;
	line-height:45px;
	color:#fff;
	background:#259775;
	margin:5px;
	font-size:18px;
	border-radius:10px;
}
.error_ft{
	width:1000px;
	height:490px;
	padding:20px;
}
</style>
</head>
<body> 
	<div class="error">
		<div class="error_hd">
			<img class="left" src="<%=basePath %>upload/404_01.png"></img>
			<div class="error_hd_ctn">
				<p class="font30 mt20">您访问的页面找不回来了</p>
				<p class="font16 mt10">抱歉，有可能您输入的的网址不正确或者该产品活动已经下架；</p>
				<p class="font16 mt10">亲，不要慌张，建议您：</p>
				<p class="mt10"><a class="index" href="<%=basePath %>">返回首页</a><a href="javascript:void(0);" class="back" onclick="window.history.back()">后退一步</a></p>
			</div>
		</div>
	</div>
</body>
</html>
