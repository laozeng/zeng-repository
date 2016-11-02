package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.bean.Question;
import com.dao.DemoDao;
import com.util.Pager;

@Repository
@SuppressWarnings("all")
public class DemoDaoImpl extends BaseDaoImpl<Question> implements DemoDao {

	public Pager<Question> getQuestions(final Integer pageIndex,
			final Integer pageSize) {
		return findPagerByPropertyListAndorder(null, null, null, null, pageIndex, pageSize);
		/*return getHibernateTemplate().execute(new HibernateCallback() {
			public Pager<Question> doInHibernate(Session session) throws HibernateException {
				Pager<Question> pager = new Pager<Question>();
				//第一种方式：使用sql语句，再映射成对象实体
//				String sql = "select * from question";
//				Query query = session.createSQLQuery(sql).addEntity(Question.class);
				//第二种方式：使用hql语句，直接映射成对象实体
				String hql = "from Question";
				Query query = session.createQuery(hql);
				pager.setPageIndex(pageIndex);
				pager.setPageSize(pageSize);
				pager.setTotalCount(query.list().size());
				query.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize);
				pager.setResult(query.list());
				return pager;
			}
		});*/
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DemoDao demoDao =(DemoDao) context.getBean("demoDaoImpl");
		Pager<Question> pager = demoDao.getQuestions(1, 5);
		for (Question quesion: pager.getResult()) {
			System.out.println(quesion.getQuestionName()+"-->"+quesion.getQuestionAnswer());
		}
		System.out.println("TotalCount:"+pager.getTotalCount());
		System.out.println("TotalPage:"+pager.getTotalPage());
	}
}
