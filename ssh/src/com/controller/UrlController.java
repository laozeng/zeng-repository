package com.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.User;

@Controller
@RequestMapping("/urlController")
public class UrlController {
	
	@RequestMapping("url.html")
	public String urlUI(){
		return "test/url";
	}
	
	@RequestMapping("url1.html")
	public String urlUI1(){
		return "test/index";
	}
	
	@RequestMapping("/method1/{year}/{month}/{day}.html")
	public String method1(@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer day){
		return "test/url";
	}
	
	/*@RequestMapping("/getEntity")
	public String getEntity(User user){ //传递实体对象参数
		System.out.println("user:"+user);
		return "test/urlDetail";
	}*/
	
	@RequestMapping(value="/getEntity",method=RequestMethod.POST)
	public String getEntity(Integer age,String name){ //传递实体对象参数
		System.out.println("age:"+age);
		return "test/urlDetail";
	}
	
	@InitBinder  //处理日期类型的响应不到的错误
	public void initBinder(WebDataBinder binder) {  
		//处理date类型
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	   /* //处理timestamp
	    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
	    dateFormat2.setLenient(false);
	    binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(dateFormat2, false));*/
	}
	
	/*<bean id="freemarkerViewResolver" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">  
	        <property name="order" value="1" />  
	        <property name="prefix" value="/freemarker/" />  
	        <property name="suffix" value=".ftl" />  
	        <property name="contentType" value="text/html;charset=utf-8" />  
	        <property name="viewClass">  
	            <value>org.springframework.web.servlet.view.freemarker.FreeMarkerView</value>  
	        </property>  
	    </bean>  
	  
	    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">   
	        <property name="order" value="2" />  
	        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />  
	         <property name="prefix" value="/WEB-INF/jsp/" />   
	         <property name="suffix" value=".jsp"></property>   
	    </bean>  
	  
	<bean  class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">  
	        <property name="templateLoaderPath" value="/WEB-INF" />  
	        <property name="defaultEncoding" value="UTF-8" />  
	</bean>*/  
}
