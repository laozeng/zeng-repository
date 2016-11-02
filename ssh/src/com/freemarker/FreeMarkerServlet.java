package com.freemarker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerServlet extends HttpServlet {
	
	public static int count = 0;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	//不支持这种做法
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*//初始化cfg对象，用于加载freemarker模板文件
		Configuration cfg = new Configuration();
		//设置模板文件所在路径
		cfg.setDirectoryForTemplateLoading(new File("D:/workspace/socket/WebRoot/WEB-INF/templates"));
//		cfg.setServletContextForTemplateLoading(request, "WEB-INF/templates");
		//创建模板
		Template template = cfg.getTemplate("freemarker.ftl");
		//获取输出对象
		PrintWriter out = response.getWriter();
		//封装数据
		User user = new User(1, "杆子", "123456", "孝感");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		//向页面输出
		try {
			template.process(map, out);
			request.getRequestDispatcher("freemarker.jsp").forward(request, response);
		} catch (TemplateException e) {
			e.printStackTrace();
		}*/
		request.setAttribute("name", "杆子");
		request.getRequestDispatcher("WEB-INF/filter/filter.jsp").forward(request, response);
	}
	
	//让模板中的内容在控制台显示
	public static void main(String[] args) {
		try {
			//初始化cfg对象，用于加载freemarker模板文件
			Configuration cfg = new Configuration();
			//设置模板文件所在路径(注意：如果仅仅是控制台测试的话，那么就只能写实际的物理路径名,即文件所在本地的路径)
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+"/WebContent/WEB-INF/freemarker"));
			//创建模板
			Template template = cfg.getTemplate("freemarker.ftl");
			//获取输出对象
			//封装数据
			User user = new User(1, "杆子", "123456", "孝感");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			//向控制台输出
			template.process(map, new OutputStreamWriter(System.out));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
