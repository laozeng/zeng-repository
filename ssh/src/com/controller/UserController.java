package com.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.User;
import com.service.UserService;

/**
 * 注意：每一个controller默认都是单例模式的 
 * @author user
 */
@Controller
@RequestMapping("/user")
public class UserController {
	//全局变量，与tomcat服务器的周期一致
	private String error = "";
	private Logger logger = Logger.getLogger(UserController.class);
	private long count = 0;
	private Map<String, String> oneLineMap  = new HashedMap();
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index_jsp.mvc")
	public ModelAndView index_jsp(HttpServletRequest request,HttpServletResponse response){
		/*String paramValue = request.getParameter("paramName");
		String webPath = (String) request.getAttribute("webPath");
		System.out.println("paramValue:"+paramValue);
		System.out.println("UserController...webPath:"+webPath);*/
		//参数为false的情况，意思是如果当前有session的话，就不会重新去创建session
		/*HttpSession session = request.getSession(false);
		boolean isContains = false; 
		//判断当前的session是否在map中，如果不在就添加进去；如果在的话就不管。
		for (String temp_session : oneLineMap.keySet()) {
			if(temp_session.equals(session.toString())){
				isContains = true;
				break;
			}
		}
		if(!isContains){
			oneLineMap.put(session.toString(), ++count+"");
		}
		mav.addObject("count", count);*/
		ModelAndView mav = new ModelAndView("user/jstl");
		return mav;
	}
	
	@RequestMapping("/index_html.mvc")
	public ModelAndView index_html(){
		ModelAndView mav = new ModelAndView("user/velocity");
		return mav;
	}
	
	@RequestMapping("/index_ftl.mvc")
	public ModelAndView index_ftl(){
		ModelAndView mav = new ModelAndView("user/freemarker");
		/*List<User> users = userService.getUsers();
		mav.addObject("users", users);*/
		mav.addObject("name", "曾昌");
		mav.addObject("birthday", new Date());
		return mav;
	}
	
	@RequestMapping("/user_list.mvc")
	public ModelAndView user_list(){
		ModelAndView mav = new ModelAndView("user/user_list");
		List<User> list = userService.getUsers();
		mav.addObject("list", list);
		return mav;
	}
	
	//@RequestParam  注意：如果有了defaultValue = ""，那么error参数可以不需要;没有的话就必须的加上 
	@RequestMapping(value="/loginUI.mvc")
	public ModelAndView loginUI(String error,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("user/login");
		if(error != null && !"".equals(error)){
			try {
				//表示页面传递过来的是ISO8859-1的编码方式，将其解析为utf-8的方式
				mav.addObject("error", new String(error.getBytes("iso8859-1"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		/*String error1 = request.getParameter("error");
		if(error1 != null && !"".equals(error1)){
			try {
				mav.addObject("error", new String(error1.getBytes("iso8859-1"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}*/
		return mav;
	}
	
	@RequestMapping(value="/doLogin.mvc",method=RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response,User user,RedirectAttributes redirectAttributes){
		ModelAndView mav = null;
		HttpSession session = request.getSession(); 
		String url = (String) (session.getAttribute("url") == null ? "" : session.getAttribute("url"));
		if("".equals(url)){
			mav = new ModelAndView("redirect:/user/user_list.mvc");
		}else{
			session.removeAttribute("url");
			mav = new ModelAndView("redirect:"+url);
		}
		if("zengchang".equals(user.getUsername()) && "123456".equals(user.getPassword())){
			request.getSession().setAttribute("user", user);
		}else{
			//1.使用全局变量，登录方法中直接用
			/*error = "您的用户名或者密码有误！";
			mav = new ModelAndView("redirect:/user/loginUI.mvc");*/
			//2.使用ModelMap传递参数，登录方法中通过request.getParameter('param');的方式获取
			/*ModelMap map = new ModelMap();
			map.addAttribute("error", "您的用户名或者密码有误！!!");
			mav = new ModelAndView("redirect:/user/loginUI.mvc",map);*/
			//3.使用RedirectAttributes封装参数，登录方法中@RequestParam(value = "error",defaultValue = "") String error来取
//			redirectAttributes.addAttribute("error", "您的用户名或者密码有误！");
			redirectAttributes.addFlashAttribute("error", "您的用户名或者密码有误");
			mav = new ModelAndView("redirect:/user/loginUI.mvc");
		}
		return mav;
	}
	
	@RequestMapping("/logout/{id}.mvc")
	public String logout(@PathVariable("id") Integer id,HttpServletRequest request){
		System.out.println("id:"+id);
		request.getSession().removeAttribute("user");
		return "redirect:/user/loginUI.mvc";
	}
	
	@RequestMapping("/update/{name}/{id}.mvc")
	public ModelAndView updateUI(@PathVariable("name") String name,@PathVariable("id") Integer id){
		System.out.println("name:"+name+",id:"+id);
		return new ModelAndView("user/user_add");
	}
	
	@RequestMapping("/delete/{id}.mvc")
	public ModelAndView delete(@PathVariable(value="id") int id){
		System.out.println("id:"+id);
		return new ModelAndView("redirect:/user/user_list.mvc");
	}
	
	
	private static List<User> users = new ArrayList<User>();
	
	static{
		for (int i = 0; i < 10; i++) {
			users.add(new User());
		}
	}
	
	@RequestMapping("/users.html")
	public String users(HttpServletRequest request){
		request.setAttribute("users", users);
		return "crud/users";
	}
	
	@RequestMapping(value="/add.html",method=RequestMethod.GET)
	public String addUser(){
		return "crud/add";
	}
	
	@RequestMapping(value="/add.html",method=RequestMethod.POST)
	public String addUser(User user){
		user.setUserId(users.get(users.size()-1).getUserId()+1); //为user设置编号
		users.add(user); //此处只是模拟添加用户，并没有实际添加到数据库中
		return "redirect:/userController/users.html";
	}
	
	
	@RequestMapping(value="/edit/{id}.html",method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("crud/edit");
		User user = null;
		for (User temp: users) {
			if(temp.getUserId().equals(id)){
				user = temp;
				break;
			}
		}
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value="/edit.html",method=RequestMethod.PUT)
	public ModelAndView editUser(User user){
		ModelAndView mav = new ModelAndView("redirect:/userController/users.html");
		for (int i = 0; i < users.size(); i++) {
			User temp = users.get(i);
			if(temp.getUserId().equals(user.getUserId())){
				temp = user;
			}
		}
		return mav;
	}
	
	
	@RequestMapping(value="/del/{id}.html",method=RequestMethod.DELETE)
	public String delUser(@PathVariable Integer id){
		for (User temp: users) {
			if(temp.getUserId().equals(id)){
				users.remove(temp);
				break;
			}
		}
		return "redirect:/userController/users.html";
	}
	
	@RequestMapping(value="/del1.html",method=RequestMethod.DELETE)
	public void delUser1(Integer id,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		for (User temp: users) {
			if(temp.getUserId().equals(id)){
				users.remove(temp);
				break;
			}
		}
		response.getWriter().print("true");
	}
	
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public String login(){
		return "front/login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(){
		return "";
	}
	
	@RequestMapping(value="/register.html",method=RequestMethod.GET)
	public String register(){
		return "front/register";
	}
	
	@InitBinder  //处理日期类型的响应不到的错误
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
	}
	
	@RequestMapping("doRegister")
	public String doRegister(){
		return "";
	}
}
