package com.shiro.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shiro.dao.UserDao;

public class Test01 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao =(UserDao) context.getBean("userDao");
		List<String> urls = userDao.getPermissionByUsername("zengchang");
		for (String string : urls) {
			System.out.println("url:"+string);
		}
	}
}
