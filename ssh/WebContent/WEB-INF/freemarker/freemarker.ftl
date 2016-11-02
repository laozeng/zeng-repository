<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>freemarker简单实用demo</title>
  </head>
  
  <body>
	使用freemarker模板语法输出个人信息：<br/>
	用户编号：${user.userId}<br/>
	用户姓名：${user.username}<br/>
	用户密码：${user.password}<br/>
	用户地址：${user.address}<br/>
	
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
  </body>
</html>
