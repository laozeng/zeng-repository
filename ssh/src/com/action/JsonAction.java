package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dao.UserDao;
import com.entity.User;
import com.util.JsonDateValueProcessor;

/**
 * json������
 */
@Controller
public class JsonAction extends BaseAction{
	@Autowired
	private UserDao userDao;
	private String address;
	private User user;
	private List<User> users = new ArrayList<User>();
	private Map<String, User> userMap = new HashMap<String, User>();
	private String param = "";
	
	/*public JsonAction(){
		try {
		    //����������д������ᱨ��
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	public String getResultByParam(){
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
		return "getResultByParam";
	}
	
	public String jsonUI(){
		return "jsonUI";
	}
	
	//struts���ɷ�����ȡjson����
	public String getString(){
		address = "�人����";
		return "getString";
	}
	
	public String getBean(){
		user = userDao.getUserById(1000000);
		return "getBean";
	}
	
	public String getList(){
		users = userDao.getUsers();
		return "getList";
	}
	
	public String getMap(){
		users = userDao.getUsers();
		if(users != null && users.size() > 0){
			for (User user : users) {
				userMap.put(user.getUserEmail(), user);
			}
		}
		return "getMap";
	}
	
	
	//��ͳ��ʽ��ȡjson����
	public void getStringCommon(){
		address = "�人����";
		getPrintWriter().print(address);
	}
	
	public void getBeanCommon(){
		user = userDao.getUserById(1000000);
		JSONObject object = JSONObject.fromObject(user,getConfig());
		getPrintWriter().print(object);
	}
	
	public void getListCommon(){
		users = userDao.getUsers();
		JSONArray array = JSONArray.fromObject(users,getConfig());
		getPrintWriter().print(array);
	}
	
	public void getMapCommon(){
		users = userDao.getUsers();
		if(users != null && users.size() > 0){
			for (int i=0;i<users.size();i++) {
				//�˴�ע�⣺map�ļ�����Ϊ��
				userMap.put("user"+i, users.get(i));
			}
		}
		JSONObject object = new JSONObject();
		JSONArray array = JSONArray.fromObject(userMap,getConfig());
		object.put("array", array); //��װmap
		object.put("size", userMap.size());
		getPrintWriter().print(object);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
