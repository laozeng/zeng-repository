package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDao{
	public User getUserById(Integer id);
	
	public List<User> getUsers();
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public void addUser(User user);
}