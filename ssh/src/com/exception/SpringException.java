package com.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * spring异常类,一出现500(代码逻辑错误)错误就会进入到该类
 * @author user
 */
public class SpringException implements HandlerExceptionResolver{
	
	@Override
	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse res, Object handler, Exception exception) {
		//Object handler -->  org.springframework.web.method.HandlerMethod
		ModelMap map = new ModelMap();
		map.addAttribute("exception", exception);
		//跳转到指定的页面并且携带数据
		return new ModelAndView("spring-exception/500.jsp",map);
	}
}
