package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webPath = req.getParameter("webPath");
		System.out.println("doGet....");
		System.out.println("webPath:"+webPath);
		resp.sendRedirect("index/indexUI.mvc");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webPath = req.getParameter("webPath");
		System.out.println("doPost....");
		System.out.println("webPath:"+webPath);
		resp.sendRedirect("index/indexUI.mvc");
	}
}
