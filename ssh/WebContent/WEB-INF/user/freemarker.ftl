<!DOCTYPE HTML>
<html>
<head>
<title>freemarker页面</title>
</head>
<body>
  <div>
  <#--注释部分 -->
  <#--freemarker语法：http://www.douban.com/note/211303714/ -->
  <#--http://blog.csdn.net/fhx007/article/details/7902040 -->

  <h1>name:${name}</h1>
	<#assign arr=["one","two","three"] />
	<#list arr as str>
		${str} <br/>
	</#list>
	
	<#setting date_format="yyyy-MM-dd" />
	birthday:${birthday?date}
  	<table border="1" style="text-align: center;" align="center"> >
		<tr id="first_tr">
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>电话</td>
		</tr>
		<#list users as user>
			<tr>
				<td>${user_index}</td>
				<td>${user.username}</td>
				<td>${user.userAge}</td>
				<td>${user.userPhone}</td>
			</tr>
		</#list>
		
		<#assign name="abc" />
	</table>
	
	<#--freemarker map的应用-->
	<#--创建一个map,注意在freemarker中,map的key只能是字符串来作为key-->
	<#assign userMap={"1","刘德华","2":"张学友"}/>
	<#--获取map中的值-->
	${userMap["1"]}
	<#--获取map的keys-->
	<#assign  keys=userMap?keys/>
	<#--遍历map 首选获取key的集合-->
	<#list keys as key>
	  key:${key}-value:${userMap["${key}"]}
	</#list>
	</br>
	<#--直接遍历map的第二种方式-->
	<#list userMap?keys as key>
	  key:${key}--value:${userMap["${key}"]}
	</#list>
	</br>
	<#--直接遍历map的values-->
	<#list userMap?values as value>
	 ${value}
	</#list>
  </div>
</body>
</html>	
