package com.exception;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.aop.ThrowsAdvice;
/**
 * aop自定义处理异常(基于schema的切面)
 * @author user
 */
public class MyAopException{
	
	//logger记录日志
	private Logger logger = Logger.getLogger(MyAopException.class);
	
	/*public void afterThrowing(Method method, Object[] args, Object target,Exception ex) {
		System.out.println("method.getName():"+method.getName());
		System.out.println("target.getClass().getName()"+target.getClass().getName());
		System.out.println("ex.getMessage():"+ex.getMessage());
		logger.info(ex);
	}*/
	//如果指定的切入点方法报错，那么就会进入该方法
	public void afterThrowing(JoinPoint jp,Exception exception) {
		System.out.println("出错所在类："+jp.getTarget().getClass().getName());
		System.out.println("出错所在方法："+jp.getSignature().getName());
		System.out.println("exception:"+exception);
		System.out.println("exception.getMessage():"+exception.getMessage());
		//将错误信息写入log日志中
		logger.error("出错所在类："+jp.getTarget().getClass().getName());
		logger.error("出错所在方法："+jp.getSignature().getName());
		logger.error(exception);
	}
	
	public void queryBefore(JoinPoint jp){
		
	}
}
