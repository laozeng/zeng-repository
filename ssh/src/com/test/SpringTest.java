package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exception.MyAopException;

public class SpringTest {
	public static void main(String[] args) {
		ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext.xml");
		MyAopException myAopException1 = (MyAopException) context1.getBean("myException");
		MyAopException myAopException2 = (MyAopException) context2.getBean("myException");
		System.out.println("myAopException1:"+myAopException1);
		System.out.println("myAopException2:"+myAopException2);
		System.out.println("myAopException1==myAopException2:"+(myAopException1 == myAopException2));
	}
	
	
}
