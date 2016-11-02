package com.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * struts2异常拦截器
 * @author user
 */
public class Struts2Exception extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		ActionContext actionContext = invocation.getInvocationContext();
	 	ValueStack stack = invocation.getStack();
	 	String url = actionContext.getName();
//		System.out.println("context.getName():"+context.getName()); // index_releaseUI 返回action名称和方法名
		try {
			result = invocation.invoke();
		} catch (Exception e) {
			e.printStackTrace();
//			stack.set("error", e.getStackTrace());
//			return "500";
		}
		return result;
	}
}
