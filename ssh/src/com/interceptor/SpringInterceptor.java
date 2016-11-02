package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 该拦截器执行的时机：1.perHande() 2.commonMethod()-->普通mvc方法 3.postHandle()  4.afterCompletion()
 * @author user
 */
public class SpringInterceptor implements HandlerInterceptor {
	//由spring容器注入
	private String redirectUrl;
	
	//在业务处理器(即用户自定义的控制器)之前被调用
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		//Object handler -->  org.springframework.web.method.HandlerMethod
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		String webPath = (String) req.getAttribute("webPath");
		System.out.println("SpringInterceptor...webPath:"+webPath);
		//如果是到登录页面的话就直接放行
		if(url.contains("loginUI")){
			return true;
		}
		/*if(session.getAttribute("user") == null){
			if(url.contains("user_list") || url.contains("index_ftl")){
				req.setAttribute("error", "您还没有登录，请先登录！");
				req.getRequestDispatcher(redirectUrl).forward(req, res);
				//需要跳转的路径
				session.setAttribute("url", url);
				return false;
			}
		}*/
		return true;
	}

	//在业务处理器之后被调用
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler, ModelAndView mav) throws Exception {
	}

	//在DispatcherServlet完全处理完之后被调用，用来做一些清理资源的操作
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object handler, Exception exception)
					throws Exception {
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
