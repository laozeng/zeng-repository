package com.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于辅助拼接HQL语句
 * 
 * @author 
 * 
 */
/**
 * @author Administrator
 *
 */
public class QueryHelper {
	private String fromClause; // FROM子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句
	private int pageNo = 1;//页面
	private int recordCount=0;//总记录数
	private int pageSize = 10;//默认每页记录数
	private List results = new ArrayList();//结果
	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class<?> clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 */
	public QueryHelper(Class<?> clazz) {
		fromClause = "FROM " + clazz.getSimpleName() + " ";
	}
	
	/**
	 * 生成From子句
	 * 
	 * @param hql hql查询语句
	 */
	public QueryHelper(String hql) {
		fromClause = hql + " ";
	}
	
	public QueryHelper() {
		
	}
	
	
	/**
	 * 添加from子句
	 * @param fromHql
	 * @return
	 */
	public QueryHelper addFrom(String fromHql){
		if(fromClause==null||!"".equals(fromClause)){
			fromClause = fromHql;
		}
		return this;
	}
	
	

	/**
	 * 拼接Where子句
	 * 
	 * @param condition
	 * @param params
	 */
	public QueryHelper addCondition(String condition, Object... params) {
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}
		
		if (params != null) {
			for (Object p : params) {
				parameters.add(p);
			}
		}

		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接Where子句
	 * 
	 * @param append
	 * @param condition
	 * @param params
	 */
	public QueryHelper addCondition(boolean append, String condition, Object... params) {
		if (append) {
			addCondition(condition, params);
		}
		return this;
	}
	
	/**
	 * 如果第一个参数为true，则拼接Where子句
	 * @param append
	 * @param condition
	 * @return
	 */
	public QueryHelper addCondition(boolean append, String condition) {
		if (append) {
			if (whereClause.length() == 0) {
				whereClause = " WHERE " + condition;
			} else {
				whereClause += " AND " + condition;
			}
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 *            参与排序的属性名
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public QueryHelper addOrderProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接OrderBy子句
	 * 
	 * @param append
	 * @param propertyName
	 * @param asc
	 */
	public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc) {
		if (append) {
			addOrderProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 * 
	 * @return
	 */
	public String getListQueryHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 * 
	 * @return
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取HQL中的参数值列表
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}
}
