package com.service;

import com.bean.Question;
import com.util.Pager;

public interface DemoService {
	public Pager<Question> getQuestions(Integer pageIndex,Integer pageSize);
}
