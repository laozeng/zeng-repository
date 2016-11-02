package com.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.dao.UserDao;
import com.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	public HibernateTemplate hibernateTemplate;
	
	public User getUserById(Integer id) {
		return hibernateTemplate.get(User.class, id);
	}

	public List<User> getUsers() {
		return (List<User>) hibernateTemplate.find("select obj from User obj");
	}

	public void updateUser(User user) {
		hibernateTemplate.update(user);
	}

	public void deleteUser(User user) {
		hibernateTemplate.delete(user);
	}

	public void addUser(User user) {
		hibernateTemplate.save(user);
	}
}
