package com.dao;

import com.bean.Question;
import com.util.Pager;

public interface DemoDao extends BaseDao<Question>{
	public Pager<Question> getQuestions(Integer pageIndex,Integer pageSize);
}
