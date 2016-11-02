package com.exception;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.aop.ThrowsAdvice;
/**
 * aop�Զ��崦���쳣(����schema������)
 * @author user
 */
public class MyAopException{
	
	//logger��¼��־
	private Logger logger = Logger.getLogger(MyAopException.class);
	
	/*public void afterThrowing(Method method, Object[] args, Object target,Exception ex) {
		System.out.println("method.getName():"+method.getName());
		System.out.println("target.getClass().getName()"+target.getClass().getName());
		System.out.println("ex.getMessage():"+ex.getMessage());
		logger.info(ex);
	}*/
	//���ָ��������㷽��������ô�ͻ����÷���
	public void afterThrowing(JoinPoint jp,Exception exception) {
		System.out.println("���������ࣺ"+jp.getTarget().getClass().getName());
		System.out.println("�������ڷ�����"+jp.getSignature().getName());
		System.out.println("exception:"+exception);
		System.out.println("exception.getMessage():"+exception.getMessage());
		//��������Ϣд��log��־��
		logger.error("���������ࣺ"+jp.getTarget().getClass().getName());
		logger.error("�������ڷ�����"+jp.getSignature().getName());
		logger.error(exception);
	}
	
	public void queryBefore(JoinPoint jp){
		
	}
}
