package com.symbio.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.symbio.commons.Page;
import com.symbio.utils.HibernateUtils;

/**
 * 定义业务层接口，接口中包括常用使用数据库的CDAU
 * @author Fred
 *
 */
public interface BaseService<T,PK extends Serializable>{
	/**
	 * 新增对象
	 */
	public void save(T entity);

	/**
	 * 修改对象.
	 */
	public void update(T entity);

	/**
	 * 删除对象.
	 */
	public void delete(T entity);

	/**
	 * 删除对象.
	 */
	public void delete(PK id);
	
	/**
	 * 根据主键ID获取对象，用hibernate的get方法加载
	 * @param id
	 * @return
	 */
	public T get(PK id);
	
	public T getSyn(PK id);
	
	
	/**
	 * 根据主键ID获取对象，用hibernate的load方法加载
	 */
	public T find(PK id);
	
	public Page<T> findPage(Page<T> pageData);
	
	public T findByField(String fieldName,String value);

	
	

}
