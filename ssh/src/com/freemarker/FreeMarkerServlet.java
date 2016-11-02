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

	//��֧����������
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*//��ʼ��cfg�������ڼ���freemarkerģ���ļ�
		Configuration cfg = new Configuration();
		//����ģ���ļ�����·��
		cfg.setDirectoryForTemplateLoading(new File("D:/workspace/socket/WebRoot/WEB-INF/templates"));
//		cfg.setServletContextForTemplateLoading(request, "WEB-INF/templates");
		//����ģ��
		Template template = cfg.getTemplate("freemarker.ftl");
		//��ȡ�������
		PrintWriter out = response.getWriter();
		//��װ����
		User user = new User(1, "����", "123456", "Т��");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		//��ҳ�����
		try {
			template.process(map, out);
			request.getRequestDispatcher("freemarker.jsp").forward(request, response);
		} catch (TemplateException e) {
			e.printStackTrace();
		}*/
		request.setAttribute("name", "����");
		request.getRequestDispatcher("WEB-INF/filter/filter.jsp").forward(request, response);
	}
	
	//��ģ���е������ڿ���̨��ʾ
	public static void main(String[] args) {
		try {
			//��ʼ��cfg�������ڼ���freemarkerģ���ļ�
			Configuration cfg = new Configuration();
			//����ģ���ļ�����·��(ע�⣺��������ǿ���̨���ԵĻ�����ô��ֻ��дʵ�ʵ�����·����,���ļ����ڱ��ص�·��)
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+"/WebContent/WEB-INF/freemarker"));
			//����ģ��
			Template template = cfg.getTemplate("freemarker.ftl");
			//��ȡ�������
			//��װ����
			User user = new User(1, "����", "123456", "Т��");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			//�����̨���
			template.process(map, new OutputStreamWriter(System.out));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
