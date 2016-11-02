package com.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * tomcat����������ʱ����ִ�и�������intercept()
 */
@SuppressWarnings("all")
public class Struts2Interceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		String url = context.getName();
		//���ܻ�ȡhttpSession���͵�session
//		HttpSession session = (HttpSession) context.get("session");
		Map session = context.getSession();
		if(session.get("user") == null && url.indexOf("user_list") != -1){
			invocation.getStack().set("error", "����û�е�¼�أ�");
			return "notLogin";
		}
//		System.out.println("context.getName():"+context.getName()); // index_releaseUI ����action���ƺͷ�����
		return invocation.invoke();
	}
}
