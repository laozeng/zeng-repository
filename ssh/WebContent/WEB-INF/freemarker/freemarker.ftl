<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>freemarker��ʵ��demo</title>
  </head>
  
  <body>
	ʹ��freemarkerģ���﷨���������Ϣ��<br/>
	�û���ţ�${user.userId}<br/>
	�û�������${user.username}<br/>
	�û����룺${user.password}<br/>
	�û���ַ��${user.address}<br/>
	
	<#--freemarker map��Ӧ��-->
	<#--����һ��map,ע����freemarker��,map��keyֻ�����ַ�������Ϊkey-->
	<#assign userMap={"1","���»�","2":"��ѧ��"}/>
	<#--��ȡmap�е�ֵ-->
	${userMap["1"]}
	<#--��ȡmap��keys-->
	<#assign  keys=userMap?keys/>
	<#--����map ��ѡ��ȡkey�ļ���-->
	<#list keys as key>
	  key:${key}-value:${userMap["${key}"]}
	</#list>
	</br>
	<#--ֱ�ӱ���map�ĵڶ��ַ�ʽ-->
	<#list userMap?keys as key>
	  key:${key}--value:${userMap["${key}"]}
	</#list>
	</br>
	<#--ֱ�ӱ���map��values-->
	<#list userMap?values as value>
	 ${value}
	</#list>
  </body>
</html>
