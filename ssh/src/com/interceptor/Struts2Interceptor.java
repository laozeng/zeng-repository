package com.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * tomcat服务器启动时不会执行该拦截器intercept()
 */
@SuppressWarnings("all")
public class Struts2Interceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		String url = context.getName();
		//不能获取httpSession类型的session
//		HttpSession session = (HttpSession) context.get("session");
		Map session = context.getSession();
		if(session.get("user") == null && url.indexOf("user_list") != -1){
			invocation.getStack().set("error", "您还没有登录呢！");
			return "notLogin";
		}
//		System.out.println("context.getName():"+context.getName()); // index_releaseUI 返回action名称和方法名
		return invocation.invoke();
	}
}
