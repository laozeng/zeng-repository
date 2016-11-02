package com.webservice;

import java.util.List;

import com.entity.UserInfo;

public interface UserService {
	abstract Integer insert(UserInfo userInfo);

	 abstract UserInfo query(Integer id);

	 abstract Integer update(UserInfo userInfo);

	 abstract Integer delete(UserInfo userInfo);
	
	 abstract List<UserInfo> queryList();
	
	 abstract Long save5(final UserInfo user); 
}
