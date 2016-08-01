package com.symbio.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.symbio.commons.Compositor;
import com.symbio.commons.Filtration;
import com.symbio.commons.Page;

/**
 * 
 * dao基类. 1：该类封装了最常见数据库操作的方法，不同的ORM映射框架可以实现该接口，实现项目通用Dao
 * 2：当你有多个sessionFactory时，你也可以在你的子类中重写setSessionFactory()方法
 * 
 * @author Fred
 * @param <T>
 * 
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * 新增对象.
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

	/**
	 * 
	 * 按属性查找唯一对象,匹配方式为相等.	
	 * @param fieldName 实体字段名
	 * @param fieldValue 实体属性值
	 * @return 如果匹配到则返回匹配到的实体，反之返回空
	 */
	public T findByField(String fieldName, Object fieldValue);
	
	public T findByFieldSyn(String sql);

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 * @param fieldName 实体字段名
	 * @param fieldValue 实体字段要匹配的值
	 * @return 与该实体值相等的实体列表
	 */
	public List<T> findList(String fieldName, Object fieldValue);

	/**
	 * 按照过滤条件对象查找对象列表.
	 */
	public List<T> findList(Filtration... filtrations);

	/**
	 * 按照过滤条件对象查找对象列表.
	 */
	public List<T> findList(List<Filtration> filtrationList);

	/**
	 * 按照过滤条件对象查找对象列表，支持排序.
	 */
	public List<T> findList(Compositor compositor, Filtration... filtrations);

	/**
	 * 按照过滤条件对象查找对象列表，支持排序.
	 */
	public List<T> findList(Compositor compositor,
			List<Filtration> filtrationList);

	/**
	 * 获取全部对象.
	 */
	public List<T> findAll();

	/**
	 * 获取全部对象,支持排序.
	 * 
	 * @param compositor
	 *            排序条件
	 * @return
	 */
	public List<T> findAll(Compositor compositor);

	/**
	 * 分页查询.
	 */
	public Page<T> find(Page<T> pageData);

	/**
	 * 按id列表获取对象.
	 */
	public List<T> findListByIds(List<PK> idList);

	// --------------------------------------------------------------------------------------------------
	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql
	 *            "from Users where name=? and password=?"
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> X find(String hql, Object... values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql
	 *            "from Users where name=:name and password=:password"
	 * @param values
	 *            命名参数,按名称绑定.
	 * @return
	 */
	public <X> X find(String hql, Map<String, ?> values);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql
	 *            "from Users where name=? and password=?"
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> List<X> findList(String hql, Object... values);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql
	 *            "from Users where name=:name and password=:password"
	 * @param values
	 *            命名参数,按名称绑定.
	 * @return
	 */
	public <X> List<X> findList(String hql, Map<String, ?> values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Object... values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Map<String, ?> values);

	// --------------------------------------------------------------------------------------------------
	/**
	 * 本地SQL进行修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public List find(String sql);
	
	/**
	 * 刷新当前事物，提交数据
	 */
	public void flush();

	<X> List<X> findList(String list, String hql, Object[] values);
}
