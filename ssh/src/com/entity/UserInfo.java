package com.entity;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
/**
 * HttpSessionBindingListener-->���Լ������౻��ӵ�session���Ǵ�session���Ƴ�
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
	
	//��ͨ����
	public void show(String content){
		System.out.println("content:"+content);
	}
	
	private int calculator(int num1,int num2){
		return num1+num2;
	}
	
	//���췽��
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
		System.out.println(MessageFormat.format("����Ϊ��{0}���Ķ�����ӵ�session���ˣ�", name));
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent sbe) {
		System.out.println(MessageFormat.format("����Ϊ��{0}���Ķ����session���Ƴ��ˣ�", name));
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		//��������˷��ʾʹ�Ӳ���ж�����
		System.out.println(MessageFormat.format("����Ϊ��{0}���Ķ����idΪ��{1}����session�������л����ڴ����ˣ�", name,se.getSession().getId()));
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		//һ����û���˷��ʾ�д����Ӳ������ȥ (��WebContent/META-INF֮������context.xml�ļ�)
		System.out.println(MessageFormat.format("����Ϊ��{0}���Ķ����idΪ��{1}����session�����л���Ӳ�����ˣ�", name,se.getSession().getId()));
	}
}
