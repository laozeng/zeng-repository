package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.CommUtil;
/**
 * ���еĹ�����������ʵ��Filter�ӿڡ��ýӿڶ�����init(),doFilter()��destory()����������
  (1)init(FilterConfig filterConfig)
      ��webӦ�ó�������ʱ��web������������ web.xml�ļ��е�������Ϣ������ÿ��ע���Filterʵ�����󣬲����䱣���ڷ��������ڴ��С�Web��������Filter����ʵ����
      ���������ø�Filter�����init������Init������Filter���������н�ִ��һ�Σ�web�����ڵ���init����ʱ���ᴫ��һ������Filter�����ú����л�����FilterConfig����
      (FilterConfig���÷���ServletConfig����)������FilterConfig������Եõ�ServletContext�����Լ����������������õĹ������ĳ�ʼ��������
  (2)doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      doFilter()����������Servlet�ӿڵ�service()���������ͻ�������Ŀ����Դ��ʱ�������ͻ���������Ŀ����Դ������Ĺ������� doFilter()���������в��� request, 
      response Ϊ web ������ Filter ������һ�� Filter ���ݹ������������Ӧ���󣻲��� chain Ϊ����ǰ Filter ���Ķ������ض��Ĳ�����ɺ󣬿����ڵ�ǰ Filter ����� doFilter 
      �����ڲ���Ҫ���� FilterChain ����� chain.doFilter(request,response)�������ܰ����󽻸��� Filter ���е���һ�� Filter ����Ŀ�� Servlet ����ȥ����Ҳ����ֱ����ͻ��˷�����Ӧ��Ϣ
      ����������RequestDispatcher��forward()��include()�������Լ� HttpServletResponse��sendRedirect()����������ת��������Դ������������������Ӧ������������ ServletRequest��
      ServletResponse��Ҳ����˵����������ʹ�ò��������ھ����Э�顣
 (3)public void destroy()
       ��Web����ж�� Filter ����֮ǰ�����á��÷�����Filter�����������н�ִ��һ�Ρ�����������У������ͷŹ�����ʹ�õ���Դ��
 */
//ע�⣺1.Filter HttpServlet Ĭ�϶��ǵ���ģʽ�����������ں�tomcat����������һ�£��������Ų�ͬ�û��ķ��ʶ��ı�(����û�����һ����Դ)
  // 2.filter �������ö������web.xml�и������õ�˳��ִ��
@SuppressWarnings("all")
public class SystemFilter implements Filter {
	
	private int count = 0;
	
	@Override
	public void destroy() {
		
	}

	//�÷���ֻ��������(service()��doPost()��doGet(),������action�еķ���)֮ǰִ��(������̬��Դ�ļ����磺css��js��jpg�ȣ����ǲ������ؾ�̬��Դ)
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String contextPath = request.getContextPath().equals("/") ? "": request.getContextPath();
		String webPath = CommUtil.getURL(request);
		String port = ":" + CommUtil.null2Int(Integer.valueOf(request.getServerPort()));
		if ((!CommUtil.generic_domain(request).equals("localhost"))) {
			webPath = "http://www." + CommUtil.generic_domain(request) + port + contextPath;
		}
		//��װ��Ŀ¼��Ϣ(�˴���װ����Ϣ��ǰ�˿��Է���,�ڿ�������Ҳ���Է���,ͨ��request.getAttribute("webPath")�����ʽ����)
		request.setAttribute("webPath", webPath);
		request.setAttribute("age", "100");
		//�˴����и�����filter����servlet����action����controller  ��ͨ��ת����
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		/*
		 * 	FilterConfig config
		 * //����web��xml�����ļ��ж���ĸù�����������
		    public String getFilterName();
		  //���ص�����������servlet������
		    public ServletContext getServletContext();
		  //���ع�������ʼ������ֵ���ַ�����ʽ��������������ʱ������nul1��name�ǳ�ʼ��������
		    public String getInitParameter(String name);
		  //��Enumeration��ʽ���ع��������г�ʼ������ֵ�����û�г�ʼ������������Ϊ��
		    public Enumeration getInitParameterNames();
		 */
		System.out.println("fileName:"+config.getFilterName());
		System.out.println("servletContext:"+config.getServletContext());
		System.out.println("charset:"+config.getInitParameter("charset"));
		System.out.println("enumeration:"+config.getInitParameterNames());
	}
}
