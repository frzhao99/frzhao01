package com.symbio.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.HibernateUtils;

public abstract class AbstractServiceImpl<T,PK extends Serializable> implements BaseService<T, PK> {

    
    public abstract GenericDaoImpl<T,PK> getDao(); 
	
	@Override
	public void save(T entity) {		
		getDao().save(entity);
		getDao().flush();
	}

	@Override
	public void update(T entity) {	
		getDao().update(entity);
		getDao().flush();
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
		getDao().flush();
		
	}

	@Override
	public void delete(PK id) {
		getDao().delete(id);
		getDao().flush();
		
	}

	@Override
	public T get(PK id) {		
		return (T) getDao().get(id);
	}
	
	@Override
	public T getSyn(PK id) {		
		return (T) getDao().getSyn(id);
	}

	@Override
	public T find(PK id) {	
		return (T) getDao().find(id);
	}
	
	
	public Page<T> findPage(Page<T> pageData){
		return getDao().find(pageData);
	}
	
	public T findByField(String fieldName,String value){
		return getDao().findByField(fieldName, value);
	}
	
	public List<T> findList(String fieldName, Object fieldValue) {
		return 	getDao().findList(fieldName, fieldValue);
	}
	

	
	
}
