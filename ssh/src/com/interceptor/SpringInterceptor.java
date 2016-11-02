package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ��������ִ�е�ʱ����1.perHande() 2.commonMethod()-->��ͨmvc���� 3.postHandle()  4.afterCompletion()
 * @author user
 */
public class SpringInterceptor implements HandlerInterceptor {
	//��spring����ע��
	private String redirectUrl;
	
	//��ҵ������(���û��Զ���Ŀ�����)֮ǰ������
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		//Object handler -->  org.springframework.web.method.HandlerMethod
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		String webPath = (String) req.getAttribute("webPath");
		System.out.println("SpringInterceptor...webPath:"+webPath);
		//����ǵ���¼ҳ��Ļ���ֱ�ӷ���
		if(url.contains("loginUI")){
			return true;
		}
		/*if(session.getAttribute("user") == null){
			if(url.contains("user_list") || url.contains("index_ftl")){
				req.setAttribute("error", "����û�е�¼�����ȵ�¼��");
				req.getRequestDispatcher(redirectUrl).forward(req, res);
				//��Ҫ��ת��·��
				session.setAttribute("url", url);
				return false;
			}
		}*/
		return true;
	}

	//��ҵ������֮�󱻵���
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler, ModelAndView mav) throws Exception {
	}

	//��DispatcherServlet��ȫ������֮�󱻵��ã�������һЩ������Դ�Ĳ���
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
