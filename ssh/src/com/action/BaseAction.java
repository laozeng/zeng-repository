package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.util.JsonDateValueProcessor;

@Component
//该action类为基类
public class BaseAction {
	
	protected String status = ""; //返回码状态 fail/success
	protected String result = ""; //结果
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置编码方式
		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;chatset=utf-8");
		return response;
	}
	
	protected HttpSession getSession(){
		return (HttpSession) ServletActionContext.getContext().getSession();
	}
	
	protected PrintWriter getPrintWriter(){
		try {
			return getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected JsonConfig getConfig(){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		return config;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
