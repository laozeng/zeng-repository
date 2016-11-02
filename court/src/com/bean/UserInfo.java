package com.bean;

public class UserInfo {
	private Integer id;
	private String name;
	private Integer age;
	private String phone;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UserInfo(Integer id, String name, Integer age, String phone) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
	
	public UserInfo(String name, Integer age, String phone) {
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
}
