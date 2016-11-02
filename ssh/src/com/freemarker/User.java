package com.freemarker;

public class User {
	private Integer userId;
	private String username;
	private String password;
	private String address;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(Integer userId, String username, String password, String address) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.address = address;
	}
	public User() {
	}
}
