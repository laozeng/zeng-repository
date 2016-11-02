package com.entity;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
/**
 * HttpSessionBindingListener-->可以监听该类被添加到session或是从session中移除
 * @author user
 */
public class UserInfo implements Serializable,HttpSessionBindingListener,HttpSessionActivationListener{
	private static final long serialVersionUID = 1L;
	public Integer id;
	private String name;
	private String password;
    private String errors;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	//普通方法
	public void show(String content){
		System.out.println("content:"+content);
	}
	
	private int calculator(int num1,int num2){
		return num1+num2;
	}
	
	//构造方法
	public UserInfo(){
		
	}
	
	public UserInfo(int id,String name,String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public UserInfo(String name){
		this.name = name;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("名称为【{0}】的对象被添加到session中了！", name));
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("名称为【{0}】的对象从session中移除了！", name));
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		//如果再有人访问就从硬盘中读进来
		System.out.println(MessageFormat.format("名称为【{0}】的对象和id为【{1}】的session被反序列化到内存中了！", name,se.getSession().getId()));
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		//一分钟没有人访问就写出到硬盘上面去 (在WebContent/META-INF之下新增context.xml文件)
		System.out.println(MessageFormat.format("名称为【{0}】的对象和id为【{1}】的session被序列化到硬盘中了！", name,se.getSession().getId()));
	}
}
