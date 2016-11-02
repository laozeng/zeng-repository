package com.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;









//import org.apache.catalina.util.URLEncoder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/json") 
/**
 * ע�⣺ʹ��  @ResponseBodyע��ʱ����Ҫ����  jackson-core-asl-1.7.3.jar��jackson-mapper-asl-1.7.2.jar�������ܰ�
 * ��һ�ж��ǻ���spring�ģ�û��spring�Ļ�һ�ж��в�ͨ
 * @author user
 *
 */
public class JsonController {
	@RequestMapping("/ajaxUI.mvc")
	public String ajaxUI(){
		return "json/json_spring";
	}
	
	//���������ת(ת��)
	@RequestMapping("/forwardController.mvc")
	public String forwardController(){
		System.out.println("enter...");
		return "forward:/user/index_jsp.mvc?paramName=zengchang";
	}
	
	//���������ת(�ض���)
	@RequestMapping("/redirectController.mvc")
	public String redirectController(){
		//�˴��������Ĳ�����ȥ����ܲ�������
		return "redirect:/user/index_jsp.mvc?paramName='����'";
	}
	
	//���� springmvc����json���ݵ�ǰ̨  ajax
	@RequestMapping("/getString.mvc")
	@ResponseBody  //ʹ��@ResponseBody���ע��
	public Object getString(HttpServletRequest request,HttpServletResponse response) {
		//�˴�ֱ�Ӵ��������ַ�����ǰ�˻����룬ԭ������
		String temp = new String("�����Ǹ�sb��");
		return temp;
	}
	/*@RequestMapping("/getString.mvc")
	public void getString(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("�����Ǹ�sb��");
	}*/
	// ���� springmvc����json���ݵ�ǰ̨  ajax
	@RequestMapping("/getBean.mvc")
	@ResponseBody  //ʹ��@ResponseBody���ע��
	public Object getBean() {
		User user = new User(1,"����","Т��",new Date());
		return user;
	}

	@RequestMapping("/getList.mvc")
	@ResponseBody
	public Object getList() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 100; i++) {
			list.add(new User((i + 1), "����" + i, "����" + (i + 1),new Date()));
		}
		return list;
	}

	@RequestMapping("/getMap.mvc")
	@ResponseBody
	public Object ajax3() {
		Map<Integer, User> map = new HashMap<Integer, User>();
		for (int i = 0; i < 100; i++) {
			map.put(i + 1, new User((i + 1), "����" + i, "����" + (i + 1),new Date()));
		}
		return map;
	}
	
	@RequestMapping("/getResultByParam.mvc")
	public @ResponseBody Object getResultByParam(String param) {
		Map<String, String> map = new HashMap<>();
		String status = "";
		String result = "";
		if("zengchang".equals(param)){
			status = "success";
			result = "�����Ǹ�sb��";
		}else if("zengjie".equals(param)){
			status = "success";
			result = "�����Ǹ����ʣ�";
		}else{
			status = "fail";
			result = "������������ݵ�ʲô��������";
		}
		map.put("status", status);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping("/translateEntityToRomate.mvc")
	@ResponseBody
	public Object translateEntityToRomate(@RequestBody String userString,HttpServletRequest request){
		//��ȡ���������������
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			System.out.println("["+paramName+"]:"+paramValue);
		}
		Map<String, String> map = new HashMap<>();
		/*try {
			System.out.println(new String(userString.getBytes("iso-8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		//��ǰ�˴��ݹ�����ֵ��ͨ��encodeuriת���˵ģ� id=1&name=%E6%9B%BE%E6%98%8C&address=%E5%AD%9D%E6%84%9F&birthday=2016-2-29
		String status = "success";
		try {
			userString = URLDecoder.decode(userString, "utf-8");
			String[] userStrings = userString.split("&");
			Integer id = Integer.valueOf(userStrings[0].substring(userStrings[0].indexOf("=")+1));
			String name = userStrings[1].substring(userStrings[1].indexOf("=")+1);
			String address = userStrings[2].substring(userStrings[2].indexOf("=")+1);
			Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(userStrings[3].substring(userStrings[3].indexOf("=")+1));
			User user = new User(id, name, address, birthday);
			System.out.println(user);
		} catch (UnsupportedEncodingException e) {
			status = "false";
			e.printStackTrace();
		} catch (ParseException e) {
			status = "false";
			e.printStackTrace();
		}
		map.put("status", status);
		return map;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		/*String personInfo = "{id:1,name:'zengchang',age:24,address:'Т��'}";
		JSONObject json = JSONObject.fromObject(personInfo);
		System.out.println("id:"+json.get("id"));
		System.out.println("name:"+json.get("name"));
		System.out.println("age:"+json.get("age"));
		System.out.println("address:"+json.get("address"));*/
		
		/*String personInfos = "[{id:1,name:'zengchang',age:24,address:'Т��'},{id:2,name:'zengchang1',age:23,address:'Т��1'}]";
		JSONArray array = JSONArray.fromObject(personInfos);
		for (Object object : array) {
			JSONObject obj = JSONObject.fromObject(object);
			System.out.println("id:"+obj.get("id"));
			System.out.println("name:"+obj.get("name"));
			System.out.println("age:"+obj.get("age"));
			System.out.println("address:"+obj.get("address"));
		}*/
		
		/*URLEncoder encoder = new URLEncoder();
		System.out.println(encoder.encode("����"));*/
		System.out.println(URLDecoder.decode("id=1&name=%E6%9B%BE%E6%98%8C&address=%E5%AD%9D%E6%84%9F&birthday=2016-2-29","utf-8"));
		System.out.println(URLEncoder.encode("����", "utf-8"));
	}

	class User {
		private int id;
		private String name;
		private String address;
		private Date birthday;

		public User(int id, String name, String address) {
			this.id = id;
			this.name = name;
			this.address = address;
		}
		
		public User(int id, String name, String address,Date birthday) {
			this.id = id;
			this.name = name;
			this.address = address;
			this.birthday = birthday;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		//��ֹʱ���ʽ������
		@JsonSerialize(using=CustomerDateSerializer.class)
		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", address=" + address
					+ ", birthday=" + birthday + "]";
		}
	}
}
