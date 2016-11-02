package com.controller.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.UserInfo;

@Controller
@RequestMapping("/freeMarker")
public class FreeMarkerController {
	
	
	@RequestMapping("/freemarker.html")  
	public ModelAndView freemarkerUI(){
		List<UserInfo> list = new ArrayList<UserInfo>();
		for(int i=0;i<100;i++){
			list.add(new UserInfo("aaa"+i, i, "13554014654"+i));
		}
		
		//到template/test/freemarker.jsp页面
		ModelAndView mav = new ModelAndView("test/freemarker");
		mav.addObject("users",list);
		mav.addObject("birthday", new Date());
		return mav;
	}
}
