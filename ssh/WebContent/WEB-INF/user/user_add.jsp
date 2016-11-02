<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>编辑用户页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    	<h3 align="center">编辑用户 <br></h3>
    	<form action="<%=basePath%>user/save_user" method="post">
    		<input type="hidden" value="${user.userId }" name="user.userId" />
    		<table align="center" >
    			<tr>
    				<td>用户名：</td>
    				<td><input type="text" value="${user.username }" name="user.username" /></td>
    			</tr>
    			<tr>
    				<td>密码：</td>
    				<td><input type="password" value="${user.password }" name="user.password" /></td>
    			</tr>
    			<tr>
    				<td>年龄：</td>
    				<td><input type="text" value="${user.userAge }" name="user.userAge" /></td>
    			</tr>
    			<tr>
    				<td>性别：</td>
    				<td>
    					<input type="radio" value="男" name="user.userGender" id="male" <s:if test="user.userGender == '男'"> checked="checked" </s:if> /><label for="male">男</label> &nbsp;&nbsp;
    					<input type="radio" value="女" name="user.userGender" id="female" <s:if test="user.userGender == '女'"> checked="checked" </s:if> /><label for="female">女</label>
    				</td>
    			</tr>
    			<tr>
    				<td>电话：</td>
    				<td><input type="text" value="${user.userPhone }" name="user.userPhone" /></td>
    			</tr>
    			<tr>
    				<td colspan="2">
    				    <!-- 
    				    	Struts2防止表单重复提交
    				    	参考路径：http://www.cnblogs.com/linjiqin/archive/2011/03/14/1984177.html
    				     -->
						<%-- <s:token></s:token> --%>
    					<input type="submit" value="提交" />&nbsp;&nbsp;
    					<input type="reset" value="清空" />
    				</td>
    			</tr>
    		</table>
    	</form>
  </body>
</html>
