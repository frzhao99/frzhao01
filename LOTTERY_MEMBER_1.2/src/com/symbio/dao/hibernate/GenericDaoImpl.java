package com.symbio.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bcai.domain.MemberWallet;
import com.symbio.commons.Compositor;
import com.symbio.commons.Filtration;
import com.symbio.commons.Page;
import com.symbio.utils.HibernateUtils;
import com.symbio.utils.ReflectionUtils;

/**
 * Hibernate操作数据库实现类
 * @author Fred
 *
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, Serializable> {	
    
	protected Logger logger = LoggerFactory.getLogger(getClass()); 

	

	protected Class<T> entityClass;
	
	public GenericDaoImpl(){		
		this.entityClass =ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	
	
	@Override
	public void save(T entity) {
	     getHibernateTemplate().save(entity);		     
	}
	
	@Override
	public T get(Serializable id) {
		
		return getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
		
	}

	@Override
	public void delete(T entity) {		
		getHibernateTemplate().delete(entity);
	
		
	}

	@Override
	public void delete(Serializable id) {
		this.delete(getHibernateTemplate().get(entityClass, id));		
	}

	@Override
	public T find(Serializable id) {	
		return (T) getSession().load(entityClass, id);		
	}

	@Override
	public T findByField(String fieldName, Object fieldValue) {
		Criterion criterion = Restrictions.eq(fieldName, fieldValue);		
		return (T) HibernateUtils.createCriteria(this.getSession(), entityClass, criterion).uniqueResult();
	}

	@Override
	public List<T> findList(String fieldName, Object fieldValue) {
		Criterion criterion = Restrictions.eq(fieldName, fieldValue);
		return HibernateUtils.createCriteria(getSession(), entityClass, criterion).list();		
	}

	@Override
	public List<T> findList(Filtration... filtrations) {
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrations);
		return criteria.list();
	}

	@Override
	public List<T> findList(List<Filtration> filtrationList) {
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrationList);
		return criteria.list();
	}

	@Override
	public List<T> findList(Compositor compositor, Filtration... filtrations) {
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrations);
		//设置排序
		criteria = HibernateUtils.setCompositorParameter(criteria, compositor);
		return criteria.list();
	}

	@Override
	public List<T> findList(Compositor compositor,
			List<Filtration> filtrationList) {
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrationList);
		//设置排序
		criteria = HibernateUtils.setCompositorParameter(criteria, compositor);
		return criteria.list();
	}

	@Override
	public List<T> findAll() {		
		return findList();
	}

	@Override
	public List<T> findAll(Compositor compositor) {	
		return findList(compositor);
	}

	@Override
	public Page<T> find(Page<T> pageData) {
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		HibernateUtils.setParameter(criteria, pageData);
		pageData.setResult(criteria.list());
		return pageData;
	}

	@Override
	public List<T> findListByIds(List<Serializable> idList) {
		if (idList != null && idList.size() >= 1)
		{
			Criterion criterion = Restrictions.in("id", idList);
			return HibernateUtils.createCriteria(getSession(), entityClass, criterion).list();
		} else
		{
			return null;
		}
	}

	@Override
	public <X> X find(String hql, Object... values) {	
		return (X) HibernateUtils.createQuery(getSession(), hql, values).uniqueResult();
	}

	@Override
	public <X> X find(String hql, Map<String, ?> values) {
		return (X) HibernateUtils.createQuery(getSession(), hql, values).uniqueResult();
	}

	@Override
	public <X> List<X> findList(String list,String hql, Object... values) {
		return HibernateUtils.createQuery(getSession(), hql, values).list();
	}

	@Override
	public <X> List<X> findList(String hql, Map<String, ?> values) {
		return HibernateUtils.createQuery(getSession(), hql, values).list();
	}

	@Override
	public int batchExecute(String hql, Object... values) {
		return HibernateUtils.createQuery(getSession(), hql, values).executeUpdate();
	}

	@Override
	public int batchExecute(String hql, Map<String, ?> values) {
		return HibernateUtils.createQuery(getSession(), hql, values).executeUpdate();
	}

	@Override
	public List find(String sql) {
		return getSession().createSQLQuery(sql).list();
	}



	@Override
	public void flush() {
	    getSession().flush();
	    getSession().clear();
	    
	}



	@Override
	public <X> List<X> findList(String hql, Object... values) {
		return HibernateUtils.createQuery(getSession(), hql, values).list();
	}



	@Override
	public T findByFieldSyn(String sql) {
		 Query query = getSession().createQuery(sql);
		 query.setLockOptions(LockOptions.UPGRADE); 
		 List<T> mws = query.list();
		 if(mws==null ||mws.size()<1){
			 return null;
		 }
		 return mws.get(0);
		
	}



	@Override
	public T getSyn(Serializable id) {
	
		return (T) this.getSession().get(entityClass,id,LockOptions.UPGRADE);
	}




	



}
