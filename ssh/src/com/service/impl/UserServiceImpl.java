package com.service.impl;

import java.util.List;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	public UserDao userDao;
	
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

}
