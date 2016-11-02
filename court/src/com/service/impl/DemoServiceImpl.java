package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Question;
import com.dao.DemoDao;
import com.service.DemoService;
import com.util.Pager;
@Service
@Transactional
public class DemoServiceImpl implements DemoService {
	@Autowired
	private DemoDao demoDao;
	
	public Pager<Question> getQuestions(Integer pageIndex, Integer pageSize) {
		return demoDao.getQuestions(pageIndex, pageSize);
	}

}
