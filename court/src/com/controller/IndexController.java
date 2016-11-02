package com.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Question;
import com.bean.User;
import com.bean.UserInfo;
import com.service.DemoService;
import com.util.CustomTimestampEditor;
import com.util.Pager;

@Controller
@SuppressWarnings("all")
public class IndexController {
	
	@Autowired
	private DemoService demoService;
	
	private final Integer PAGESIZE = 5;
	
	//到首页的方法
	@RequestMapping("/index.htm")
	public ModelAndView indexUI(@RequestParam(value="pageIndex",defaultValue = "1") Integer pageIndex){ // 如果不传pageIndex这个参数的话，那么他的值默认为1
		ModelAndView mav = new ModelAndView("front/index");
		Pager<Question> questions = demoService.getQuestions(pageIndex, PAGESIZE);
		mav.addObject("questions", questions);
		return mav;
	}
	
	@RequestMapping("/ajax.htm")
	public String ajaxUI(ModelAndView mav,HttpServletRequest request){
		mav.addObject("webPath", request.getAttribute("webPath"));
		return "test/ajax";
	}
	
	@RequestMapping("/getJson")
	@ResponseBody
	//路径中不能带有后缀，如：getJson.html,getJson.htm
	//The resource identified by this request is only capable of generating responses with 
	//characteristics not acceptable according to the request "accept" headers ().
	public Object getJson(@RequestParam(value="pageIndex",defaultValue = "1") Integer pageIndex,HttpServletRequest request){
		List<UserInfo> list = new ArrayList<UserInfo>();
		for(int i=0;i<100;i++){
			list.add(new UserInfo("aaa"+i, i, "13554014654"+i));
		}
		
		int totalCount = list.size();
		
//		request.getSession().setAttribute("totalCount", totalCount);
		request.setAttribute("totalCount", totalCount);
		
		int toIndex = pageIndex * PAGESIZE;
		
		toIndex = toIndex > list.size() ? list.size() : pageIndex * PAGESIZE;
		
		list = list.subList((pageIndex-1)*PAGESIZE,toIndex);//包头不包尾
		
		return list;
	}
	
	@RequestMapping("/getJson1")
	@ResponseBody
	//路径中不能带有后缀，如：getJson.html,getJson.htm
	//The resource identified by this request is only capable of generating responses with 
	//characteristics not acceptable according to the request "accept" headers ().
	public Object getJson1(@RequestParam(value="pageIndex",defaultValue = "1") Integer pageIndex){
		List<UserInfo> list = new ArrayList<UserInfo>();
		for(int i=0;i<100;i++){
			list.add(new UserInfo("aaa"+i, i, "13554014654"+i));
		}
		
		int totalCount = list.size();
		
		int toIndex = pageIndex * PAGESIZE;
		
		toIndex = toIndex > list.size() ? list.size() : pageIndex * PAGESIZE;
		
		list = list.subList((pageIndex-1)*PAGESIZE,toIndex);//包头不包尾
		
		Pager<UserInfo> pager = new Pager<UserInfo>();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(PAGESIZE);
		pager.setTotalCount(totalCount);
		pager.setResult(list);
		
		return pager;
	}
}
