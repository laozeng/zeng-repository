package com.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * spring�쳣��,һ����500(�����߼�����)����ͻ���뵽����
 * @author user
 */
public class SpringException implements HandlerExceptionResolver{
	
	@Override
	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse res, Object handler, Exception exception) {
		//Object handler -->  org.springframework.web.method.HandlerMethod
		ModelMap map = new ModelMap();
		map.addAttribute("exception", exception);
		//��ת��ָ����ҳ�沢��Я������
		return new ModelAndView("spring-exception/500.jsp",map);
	}
}
