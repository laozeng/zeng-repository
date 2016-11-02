package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.util.CommUtil;

public class SystemInterceptor extends HandlerInterceptorAdapter {
	//要跳转到的页面  该值由spring注入
	private String redirectUrl = "";
	//在业务处理器(即用户自定义的控制器)之前被调用
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getContextPath().equals("/") ? "": request.getContextPath();
		String webPath = CommUtil.getURL(request);
		String port = ":" + CommUtil.null2Int(Integer.valueOf(request.getServerPort()));
		/*if ((!CommUtil.generic_domain(request).equals("localhost"))) {
			webPath = "http://www." + CommUtil.generic_domain(request) + port + contextPath;
		}*/
		//封装根目录信息(此处封装的信息在前端可以访问,在控制器中也可以访问,通过request.getAttribute("webPath")这个方式访问)
		request.setAttribute("webPath", webPath);
		
		HttpSession session = request.getSession();
		String requestUrl = request.getRequestURL().toString();
		//如果当前的请求是到后台的登录页面或者是处理登录请求的话，那么都放行交由业务处理器处理
		if(session.getAttribute("admin") != null || requestUrl.endsWith("login.html") || requestUrl.endsWith("doLogin")){
			//交由其他业务处理器继续处理请求
			return true;
		}else{
			//进行拦截到指定的页面！
//			response.sendRedirect(redirectUrl);
			request.setAttribute("error", "您还没有登录，请先登录！");
			request.getRequestDispatcher(redirectUrl).forward(request, response);
			return false;
		}
	}
	
	//在业务处理器之后被调用
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//在DispatcherServlet完全处理完之后被调用，用来做一些清理资源的操作
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
