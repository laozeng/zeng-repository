package com.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dao.BaseDao;
import com.dao.QueryHelper;
import com.sun.istack.internal.FinalArrayList;
import com.util.Pager;

@SuppressWarnings("all")
public abstract class BaseDaoImpl<T extends Serializable> extends HibernateDaoSupport implements BaseDao<T> {
	private Class<T> persistentClass;
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
    @SuppressWarnings("unchecked")  
    public BaseDaoImpl()
    {
        // 获取具体的实体类
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()  
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
  
	protected Class<T> getPersistentClass()  
    {
        return persistentClass;
    }
	
	protected String getPersistentName() {
		return persistentClass.getName().substring(persistentClass.getName().lastIndexOf(".") + 1);
	}

	public Serializable save(T t) {
		return getHibernateTemplate().save(t);
	}
	
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	public void delete(final String propertyName, final Object value) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				String hql = "delete from "+getPersistentName()+" where "+propertyName+" = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, value);
				return query.executeUpdate();
			}
		});
	}
	
	public void update(T t) {
		getHibernateTemplate().update(t);
		getHibernateTemplate().flush();
	}

	public T findSingle(final String propertyName, final Object value) {
			return (T) getHibernateTemplate().execute(new HibernateCallback<T>() {
				public T doInHibernate(Session session)
						throws HibernateException {
						String hql = "from "+getPersistentName() +" as " + getPersistentName() + " where "+
								getPersistentName()+"." + propertyName + "=:value";
						Query query = null;
						try{
						 query = session.createQuery(hql);
						}catch (Exception e) {
							e.printStackTrace();
						}
						query.setParameter("value", value);
						return (T) query.setMaxResults(1).uniqueResult();
				}
			});
	}
	
	@SuppressWarnings("unchecked")
	public T findSingleByPropertyList(final List<String> propertyName, final List<Object> value) {
		return  getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
				String hql = "from "+getPersistentName()+" as "+getPersistentName();
				for(int i=0;i<propertyName.size();i++){
				    String propertynametemp= propertyName.get(i);
					if(propertynametemp!=null){
						if(i==0){    
						      hql=hql+" where "+getPersistentName()+"." + propertynametemp + "= ? ";    
					     }else{   
					    	 hql=hql+" and "+getPersistentName()+"." +propertynametemp + "= ? ";
						}
					}
				}
				Query query = session.createQuery(hql);
				for(int i=0;i<propertyName.size();i++){
					query.setParameter(i, value.get(i));
				}
				return (T) query.setMaxResults(1).uniqueResult();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String hql="from "+getPersistentName();
		return (List<T>) getHibernateTemplate().find(hql);
	}
	

	public T get(Class<T> entityClass, final Serializable id) {
		return (T)getHibernateTemplate().get(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByPropertyList(final List<String> propertyName, final List<Object> value) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				String hql = "from "+getPersistentName()+" as "+getPersistentName();
				
				for(int i=0;i<propertyName.size();i++){
				    String propertynametemp= propertyName.get(i);
					if(propertynametemp!=null){
						if(i==0){    
						      hql=hql+" where "+getPersistentName()+"." + propertynametemp + " = ? ";    
					     }else{   
					    	 hql=hql+" and "+getPersistentName()+"." +propertynametemp + " = ? ";
						}
					}
				}
				
				Query query = session.createQuery(hql);
				for(int i=0;i<propertyName.size();i++){
					query.setParameter(i, value.get(i));
				}
				return query.list();
			}
		});
	}
	
	public List<T> findByProperty(final String propertyName, final Object value) {
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session)
					throws HibernateException {
					String hql = "from "+getPersistentName()+
							" as "+getPersistentName()+" where "+
							getPersistentName()+"." + propertyName + "=:value";
					Query query = null;
					try{
					 query = session.createQuery(hql);
					}catch (Exception e) {
						e.printStackTrace();
					}
					query.setParameter("value", value);
					return query.list();
			}
		});

	}
	
	
	public List<T> findByPropertyListAndOrder(final List<String> propertyName, final List<Object> value,
			final List<String> orderPropertyName,final List<String> order) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				String hql = setValue(propertyName, value, orderPropertyName, order);
				
				Query query = session.createQuery(hql);
				
				if(propertyName != null && value != null){
					for(int i=0;i<propertyName.size();i++){
						query.setParameter(i, value.get(i));
					}
				}
				
				return query.list();
			}
		});
	}
	
	public Pager<T> findPagerByPropertyListAndorder(final List<String> propertyName,final List<Object> value,final List<String> orderPropertyName,
			final List<String> order,final Integer pageIndex,final Integer pageSize){
		return getHibernateTemplate().execute(new HibernateCallback<Pager<T>>() {
			public Pager<T> doInHibernate(Session session) throws HibernateException {
				String hql = setValue(propertyName, value, orderPropertyName, order);
				
				Query query = session.createQuery(hql);
				
				if(propertyName != null && value != null){
					for(int i=0;i<propertyName.size();i++){
						query.setParameter(i, value.get(i));
					}
				}
				
				/*//第一种方式：使用sql语句，再映射成对象实体
				String sql = "select * from question";
				Query query = session.createSQLQuery(sql).addEntity(Question.class);
				//第二种方式：使用hql语句，直接映射成对象实体
				String hql = "from Question";
				Query query = session.createQuery(hql);*/
				
				//封装pager对象
				Pager<T> pager = new Pager<T>();
				pager.setTotalCount(query.list().size());
				query.setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize);
				pager.setResult(query.list());
				pager.setPageIndex(pageIndex);
				pager.setPageSize(pageSize);
			    return pager;
			}
		});
	}
	
	//构建hql语句，并且与之赋值
	private String setValue(List<String> propertyName,List<Object> value,List<String> orderPropertyName,List<String> order){
		String hql = "from "+getPersistentName()+" as "+getPersistentName();
		if(propertyName != null && value != null){
			for(int i=0;i<propertyName.size();i++){
			    String propertynametemp= propertyName.get(i);
			    Object propertyvaluetemp= value.get(i);
				if(propertynametemp!=null){
					if(i==0){    
					      hql=hql+" where "+getPersistentName()+"." + propertynametemp + "= ? ";    
				     }else{   
				    	 hql=hql+" and "+getPersistentName()+"." +propertynametemp + "= ? ";
					}
				}
			}
		}
		
		if(orderPropertyName != null && order != null){
			for(int i=0;i<orderPropertyName.size();i++){
				String orderPropertyNametemp= orderPropertyName.get(i);
				String ordertemp = order.get(i);
				if(orderPropertyNametemp!=null){
					if(i==0){
						hql = hql + " order by  " + getPersistentName() + "." + orderPropertyNametemp + " " + ordertemp;
					}else{
						hql = hql + " ,  " + getPersistentName() + "." + orderPropertyNametemp + " " + ordertemp;
					}
				}
			}
		}
		
		return hql;
	}
	
	//查询VO，即自定义的实体  如：CommentVO
	/*Query query = session.createSQLQuery("select u.user_id as userId ,c.comment_rating as rating,c.comment_id 
	 as commentId,c.comment_content as content,u.username as userName" +
	",u.user_img1 as userImg,c.comment_time as time from comment c INNER JOIN user u on u.user_id=c.comment_user_id " +
	"  where c.comment_business_id="+businessId+" order by c.comment_time desc")
	.addScalar("commentId",IntegerType.INSTANCE)
	.addScalar("userId",IntegerType.INSTANCE)
	.addScalar("userName",StringType.INSTANCE)
	.addScalar("rating",IntegerType.INSTANCE)
	.addScalar("userImg",StringType.INSTANCE)
	.addScalar("content",StringType.INSTANCE)
	.addScalar("time",TimestampType.INSTANCE)
	.setResultTransformer(Transformers.aliasToBean(CommentVO.class));*/

	
	public List<T> findLimitedByOrder(final int num,final String propertyName , final String order) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				String hql ="from "+getPersistentName()+" as "+getPersistentName()+ " order by "+getPersistentName()+"." + propertyName+ " " + order;
				Query query = session.createQuery(hql);
				query.setMaxResults(num);
				return query.list();
			}
		});
	}
	
	public List<T> findAllByOrder(String propertyName, String order) {
		String hql="from "+getPersistentName()+" as "+getPersistentName()+ " order by "+getPersistentName()+"." + propertyName+ " " + order;
		return (List<T>) getHibernateTemplate().find(hql);
	}
	
	protected <T> List<T> find(String hql) {
		return (List<T>) getHibernateTemplate().find(hql);
	}
	
	protected List<T> findBySql(final String sql){
		return getHibernateTemplate().execute(new HibernateCallback() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	
	/**
	 * @param hibernateTemplate
	 * @param hql
	 * @param valus
	 * @return 2008-07-19 add by liuyang
	 */
	protected List<T> find(final String hql, final Object[] values) {
		
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				for (int i = 0; values != null && i < values.length; i++) {
					query.setParameter(i, values[i]);
				}

				return query.list();
			}
		});

	}
	
	
	protected Object updateByHql(final String hql) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				
				return session.createQuery(hql).executeUpdate();
			}
		});
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	protected Integer updateByHql(final String hql,final String[] paramNames,final Object[] paramValues){
		return getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				
				Query q = session.createQuery(hql);
				for(int i = 0;i<paramValues.length;i++){
					//q.setParameter(i, objs[i]);
					if(paramValues[i] instanceof Collection){
						q.setParameterList(paramNames[i], (Collection)paramValues[i]);
					}else if(paramValues[i] instanceof Object[]){
						q.setParameterList(paramNames[i], (Object[])paramValues[i]);
					}else{
						q.setParameter(paramNames[i], paramValues[i]);
					}
				}
				
				return q.executeUpdate();
			}
				
		});
		
	}

	/**
	 * @param query
	 * @return
	 */
	protected int updateBySqlString(final String sql){
		
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				
				Query query = session.createSQLQuery(sql);
				return query.executeUpdate();
			}
		});
	}
	
	
	public void pageList(final QueryHelper qh) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(qh.getListQueryHql());
				List parameters = qh.getParameters();
				for (int i = 0; parameters != null && i < parameters.size(); i++) {
					query.setParameter(i, parameters.get(i));
				}
				
				qh.setRecordCount(query.list().size());
				
				query.setFirstResult((qh.getPageNo()-1)*qh.getPageSize()).setMaxResults(qh.getPageSize());
				qh.setResults(query.list());
				
				qh.getCountQueryHql();
				
				return null;
			}
		});
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public String generateSerial(String serialName, String prefix, String format) {
		// TODO Auto-generated method stub
		return null;
	}                                                                                                                                                                                      
}