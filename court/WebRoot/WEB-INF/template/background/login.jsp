<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
	<title>后台登陆页面</title>
	<link href="<%=basePath %>resource/css/admin_login.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
	<div class="admin_login_wrap" >
    <h1>后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="<%=path %>/admin/doLogin" method="post" id="myForm">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="username"  id="user" size="40" class="admin_input_style" value=""/>
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="password"  id="pwd" size="40" class="admin_input_style" />
                    </li>
                     <!-- 显示错误信息区域 -->
                    <li>
						<strong style="color:red;font-size:13px;" id="error">${error} </strong>
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="提交" class="btn btn-primary" id="sub"/>
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <p class="admin_copyright"><a tabindex="5" href="index!indexUI" target="_blank">返回首页</a> &copy; 2015 Powered by <a href="http://www.qunhai.net" target="_blank">上海群海</a></p>
</div>
  </body>
</html>
