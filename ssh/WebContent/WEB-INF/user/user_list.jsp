<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!-- 该表达式依赖于standard.jar和jstl.jar两个架包 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户集合</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  	<div align="center">
  		<h3>用户信息：</h3>
  		<p>${user }</p>
  	</div>
  	
  	<!-- 
  	          没有用Struts2框架会报错：
	  	The Struts dispatcher cannot be found.  This is usually caused by using Struts tags without the associated filter. 
	  	Struts tags are only usable when the request has passed through its servlet filter, 
	  	which initializes the Struts dispatcher needed for this tag. - [unknown location] 
  	-->
  	<!-- struts2 -->
  	<%-- <div align="center">
  		<h3>Struts2调用后台静态常量：</h3>
  		count:<s:property value="@com.constant.Area@COUNT" /> <br/>
  		name:<s:property value="@com.constant.Area@NAME" /> <br/>
  		address:<s:property value="@com.constant.Area@ADDRESS" /> <br/>
  		<h3>Struts2调用后台静态方法(该特性在Struts 2.3之后就被禁用了，因为不安全)：</h3>
  		<s:set name="param" value="我是曾昌" />
  		getName:<s:property value="@com.constant.Area@getName(#param)" /> <br/>
  	</div>
	<h3 align="center">用户集合&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>user/add_user">添加</a></h3>
	<table align="center" border="1px">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>电话</td>
			<td>操作</td>
		</tr>
		<s:iterator value="list" var="user" status="sta">
			<tr>
				<td><s:property value="#sta.index" /></td>
				<td><s:property value="#user.username" /></td>
				<td><s:property value="#user.userAge" /></td>
				<td><s:property value="#user.userPhone" /></td>
				<td>
					<a href="<%=basePath%>user/update_user?user_id=<s:property value='#user.userId' />">修改</a>
					<a href="<%=basePath%>user/delete_user?user_id=<s:property value='#user.userId' />">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>  --%>
	
	<!-- springmvc -->
	 <div align="center">
  		<h3>springmvc：(在该模式之下不能修改，只能在Struts2模式之下修改)</h3>
  		<p>${user }&nbsp;&nbsp;&nbsp;&nbsp;<a href="${webPath }/user/logout/1.mvc">退出登录</a></p>
  		<table align="center" border="1px">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>电话</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list}" var="user" varStatus="status">
			<tr>
				<td>${status.index + 1 }</td>
				<td>${user.username }</td>
				<td>${user.userAge }</td>
				<td>${user.username }</td>
				<td>
					<a href="user/update/${user.username}/${user.userId}.mvc">修改</a>
					<a href="user/delete/${user.userId}.mvc">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
  	</div> 
  </body>
</html>
