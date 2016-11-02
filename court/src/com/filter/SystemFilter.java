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
 * 所有的过滤器都必须实现Filter接口。该接口定义了init(),doFilter()，destory()三个方法：
  (1)init(FilterConfig filterConfig)
      在web应用程序启动时，web服务器将根据 web.xml文件中的配置信息来创建每个注册的Filter实例对象，并将其保存在服务器的内存中。Web容器创建Filter对象实例后，
      将立即调用该Filter对象的init方法。Init方法在Filter生命周期中仅执行一次，web容器在调用init方法时，会传递一个包含Filter的配置和运行环境的FilterConfig对象
      (FilterConfig的用法和ServletConfig类似)。利用FilterConfig对象可以得到ServletContext对象，以及部署描述符中配置的过滤器的初始化参数。
  (2)doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      doFilter()方法类似于Servlet接口的service()方法。当客户端请求目标资源的时候，容器就会调用与这个目标资源相关联的过滤器的 doFilter()方法。其中参数 request, 
      response 为 web 容器或 Filter 链的上一个 Filter 传递过来的请求和相应对象；参数 chain 为代表当前 Filter 链的对象，在特定的操作完成后，可以在当前 Filter 对象的 doFilter 
      方法内部需要调用 FilterChain 对象的 chain.doFilter(request,response)方法才能把请求交付给 Filter 链中的下一个 Filter 或者目标 Servlet 程序去处理，也可以直接向客户端返回响应信息
      ，或者利用RequestDispatcher的forward()和include()方法，以及 HttpServletResponse的sendRedirect()方法将请求转向到其他资源。这个方法的请求和响应参数的类型是 ServletRequest和
      ServletResponse，也就是说，过滤器的使用并不依赖于具体的协议。
 (3)public void destroy()
       在Web容器卸载 Filter 对象之前被调用。该方法在Filter的生命周期中仅执行一次。在这个方法中，可以释放过滤器使用的资源。
 */
//注意：1.Filter HttpServlet 默认都是单例模式，即生命周期和tomcat服务器周期一致，不会随着不同用户的访问而改变(多个用户共享一个资源)
  // 2.filter 可以配置多个，在web.xml中根据配置的顺序执行
@SuppressWarnings("all")
public class SystemFilter implements Filter {
	
	private int count = 0;
	
	public void destroy() {
		
	}

	//该方法只会在请求(service()、doPost()、doGet(),或者是action中的方法)之前执行(包括静态资源文件，如：css，js，jpg等，但是不会拦截静态资源)
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String contextPath = request.getContextPath().equals("/") ? "": request.getContextPath();
		String webPath = CommUtil.getURL(request);
		String port = ":" + CommUtil.null2Int(Integer.valueOf(request.getServerPort()));
		//封装根目录信息(此处封装的信息在前端可以访问,在控制器中也可以访问,通过request.getAttribute("webPath")这个方式访问)
		request.setAttribute("webPath", webPath);
		request.setAttribute("age", "100");
		//此处放行给其他filter或者servlet或者action或者controller  是通过转发的
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		/*
		 * 	FilterConfig config
		 * //返回web．xml部署文件中定义的该过滤器的名称
		    public String getFilterName();
		  //返回调用者所处的servlet上下文
		    public ServletContext getServletContext();
		  //返回过滤器初始化参数值的字符串形式，当参数不存在时，返回nul1．name是初始化参数名
		    public String getInitParameter(String name);
		  //以Enumeration形式返回过滤器所有初始化参数值，如果没有初始化参数，返回为空
		    public Enumeration getInitParameterNames();
		 */
		System.out.println("fileName:"+config.getFilterName());
		System.out.println("servletContext:"+config.getServletContext());
		System.out.println("charset:"+config.getInitParameter("charset"));
		System.out.println("enumeration:"+config.getInitParameterNames());
	}
}
