package com.hymer.core;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO implements ICommonDAO {
	
	protected Log log = LogFactory.getLog(getClass());

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <T extends BaseEntity> T save(T model) {
		if (model.getCreateTime() == null) {
			model.setCreateTime(new Date());
		}
		getSession().save(model);
		return model;
	}

	@Override
	public <T extends BaseEntity> void saveOrUpdate(T model) {
		if (model.getCreateTime() == null) {
			model.setCreateTime(new Date());
		}
		getSession().saveOrUpdate(model);
	}

	@Override
	public <T extends BaseEntity> void update(T model) {
		getSession().update(model);
	}

	/**
	 * 物理删除
	 */
	@Override
	public <T extends BaseEntity> void delete(T model) {
		getSession().delete(model);
	}
	
	/**
	 * 将flag设置为-1，标记为已删除，用于业务数据 
	 */
	@Override
	public <T extends BaseEntity> void deleteLogic(T model) {
		model.setFlag(-1);
		getSession().update(model);
	}

	public List<?> executeHqlQuery(String hql) {
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	public Object executeHqlUnique(String hql) {
		Query query = getSession().createQuery(hql);
		return query.uniqueResult();
	}

	public List<?> executeHqlQuery(String hql, Map<String, Object> parameters) {
		Query query = getSession().createQuery(hql);
		setParameters(query, parameters);
		return query.list();
	}

	public int executeHqlUpdate(String hql, Map<String, Object> parameters) {
		Query query = getSession().createQuery(hql);
		setParameters(query, parameters);
		return query.executeUpdate();
	}

	public Object executeSqlQuery(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.list();
	}

	public Object executeSqlUnique(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.uniqueResult();
	}

	public Object executeSqlQuery(String sql, Map<String, Object> parameters) {
		SQLQuery query = getSession().createSQLQuery(sql);
		setParameters(query, parameters);
		return query.list();
	}

	public int executeSqlUpdate(String sql, Map<String, Object> parameters) {
		SQLQuery query = getSession().createSQLQuery(sql);
		setParameters(query, parameters);
		return query.executeUpdate();
	}

	protected void setParameter(Query query, String paramName, Object paramValue) {
		if (paramValue instanceof String) {
			query.setString(paramName, (String) paramValue);
		} else if (paramValue instanceof Integer) {
			query.setInteger(paramName, (Integer) paramValue);
		} else if (paramValue instanceof Long) {
			query.setLong(paramName, (Long) paramValue);
		} else if (paramValue instanceof Double) {
			query.setDouble(paramName, (Double) paramValue);
		} else if (paramValue instanceof Boolean) {
			query.setBoolean(paramName, (Boolean) paramValue);
		} else if (paramValue instanceof Date) {
			// TODO 难道这是bug 使用setParameter不行？？
			query.setTimestamp(paramName, (Date) paramValue);
		} else if (paramValue instanceof Collection) {
			query.setParameterList(paramName, (Collection<?>) paramValue);
		} else {
			query.setParameter(paramName, paramValue);
		}
	}

	protected void setParameters(Query query, Map<String, Object> paramMap) {
		if (paramMap != null) {
			Iterator<String> keys = paramMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = paramMap.get(key);
				setParameter(query, key, value);
			}
		}
	}

}
