package com.dao;

import java.io.Serializable;
import java.util.List;

import com.util.Pager;


/**
 * 基础数据访问对象
 *
 */
public interface BaseDao<T extends Serializable>{
	
	/**
	 * 保存对象
	 * @param t
	 */
	Serializable save(T t);
	
	/**
	 * 根据传入的实体添加或更新对象
	 * @param entity
	 */
	void saveOrUpdate(T entity);
	
	/**
	 * 删除对象
	 * @param t
	 */
	void delete(T t);
	
	/**
	 * 根据条件删除对象
	 * @param targetName
	 * @param propertyName
	 * @param value
	 */
	void delete(String propertyName,Object value);
	
	/**
	 * 更新
	 * @param <T>
	 * @param t
	 */
	void update(T t);
	/**
	 * 读一条记录
	 * @param <T>
	 * @param targetName 类名
	 * @param propertyName 字段
	 * @param value 字段值
	 * @return
	 */
	T findSingle(String propertyName,Object value);
	
	/**
	 * 根据参数列表读取一条记录
	 * @author TuTianli
	 * @param propertyName
	 * @param value
	 * @return
	 */
	T findSingleByPropertyList(final List<String> propertyName, final List<Object> value);

	/**
	 * 读所有记录
	 * @param <T>
	 * @param targetName
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 读所有记录，并排序
	 * @param <T>
	 * @param targetName
	 * @param propertyName 字段
	 * @param order 
	 * @return
	 */
	List<T> findAllByOrder(String propertyName,String order);
	
	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	T get(Class<T> entityName, Serializable id);
	
	/**
	 * 根据条件读取记录
	 * @param <T>
	 * @param targetName
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<T> findByPropertyList(List<String> propertyName, List<Object> value);
	
	/**
	 * 根据单一条件读取记录 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<T> findByProperty(final String propertyName, final Object value);
	
	/**
	 * 根据条件读取记录并排序
	 * @param <T>
	 * @param targetName
	 * @param propertyName
	 * @param value
	 * @param orderPropertyName
	 * @param order
	 * @return
	 */
	List<T> findByPropertyListAndOrder(List<String> propertyName, List<Object> value,List<String> orderPropertyName, List<String> order);
	
	/**
	 * 分页查询， 根据条件读取记录并按照多条件排序
	 * @param propertyName
	 * @param value
	 * @param orderPropertyName
	 * @param order
	 * @return
	 */
	Pager<T> findPagerByPropertyListAndorder(List<String> propertyName, List<Object> value,List<String> orderPropertyName, List<String> order,Integer pageIndex,Integer pageSize);
	
	/**
	 * 获取指定条数的记录
	 * @param <T>
	 * @param targetName
	 * @param propertyName
	 * @param num
	 * @param order
	 * @return
	 */
	List<T> findLimitedByOrder(int num,String propertyName, String order);
	
	/**
	 * 分页查询
	 * @param hql  查询语句及条件
	 */
	void pageList(final QueryHelper qh);
	
	/**
	 * 将session缓存同步到数据库
	 * 
	 * @author TuTianli
	 */
	void flush();
	
	/**
	 * 根据指定的编码规则生成下一个ID
	 * 方法参数都来自参数表
	 * @param serialName ID字段名
	 * @param prefix ID前缀
	 * @param format 进制-10进制-16进制
	 * @return
	 * @author TuTianli
	 */
	String generateSerial(String serialName, String prefix, String format);
}
