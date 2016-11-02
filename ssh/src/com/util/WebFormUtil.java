package com.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
/**
 * 表单工具类
 * @author user
 */
@SuppressWarnings("all")
public class WebFormUtil {
	private Class clz;
	private HttpServletRequest request;
	public WebFormUtil(Class clz, HttpServletRequest request) {
		this.clz = clz;
		this.request = request;
	}
	public WebFormUtil(Class clz) {
		this.clz = clz;
	}
	
	public Object getObjectByWeb(){
		Object object = new Object();
		return object;
	}
	
	public static void main(String[] args) throws Exception {
		Class clz = Class.forName("com.entity.UserInfo");
		Method[] methods = clz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println("name:"+method.getName());
		}
	}
}
