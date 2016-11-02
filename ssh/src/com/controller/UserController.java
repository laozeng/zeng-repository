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
 * ע�⣺ÿһ��controllerĬ�϶��ǵ���ģʽ�� 
 * @author user
 */
@Controller
@RequestMapping("/user")
public class UserController {
	//ȫ�ֱ�������tomcat������������һ��
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
		//����Ϊfalse���������˼�������ǰ��session�Ļ����Ͳ�������ȥ����session
		/*HttpSession session = request.getSession(false);
		boolean isContains = false; 
		//�жϵ�ǰ��session�Ƿ���map�У�������ھ���ӽ�ȥ������ڵĻ��Ͳ��ܡ�
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
		mav.addObject("name", "����");
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
	
	//@RequestParam  ע�⣺�������defaultValue = ""����ôerror�������Բ���Ҫ;û�еĻ��ͱ���ļ��� 
	@RequestMapping(value="/loginUI.mvc")
	public ModelAndView loginUI(String error,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("user/login");
		if(error != null && !"".equals(error)){
			try {
				//��ʾҳ�洫�ݹ�������ISO8859-1�ı��뷽ʽ���������Ϊutf-8�ķ�ʽ
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
			//1.ʹ��ȫ�ֱ�������¼������ֱ����
			/*error = "�����û���������������";
			mav = new ModelAndView("redirect:/user/loginUI.mvc");*/
			//2.ʹ��ModelMap���ݲ�������¼������ͨ��request.getParameter('param');�ķ�ʽ��ȡ
			/*ModelMap map = new ModelMap();
			map.addAttribute("error", "�����û���������������!!");
			mav = new ModelAndView("redirect:/user/loginUI.mvc",map);*/
			//3.ʹ��RedirectAttributes��װ��������¼������@RequestParam(value = "error",defaultValue = "") String error��ȡ
//			redirectAttributes.addAttribute("error", "�����û���������������");
			redirectAttributes.addFlashAttribute("error", "�����û���������������");
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
		user.setUserId(users.get(users.size()-1).getUserId()+1); //Ϊuser���ñ��
		users.add(user); //�˴�ֻ��ģ������û�����û��ʵ����ӵ����ݿ���
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
	
	@InitBinder  //�����������͵���Ӧ�����Ĵ���
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
