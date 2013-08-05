package com.hymer.core;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.metamodel.source.hbm.Helper.ValueSourcesAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.hymer.core.model.Condition;
import com.hymer.core.model.PageInfo;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;

@SuppressWarnings("all")
public abstract class DAOHibernate<T extends BaseEntity, PK extends java.io.Serializable>
		extends CommonDAO implements IDAO<T, PK> {
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(DAOHibernate.class);

	private final Class<T> entityClass;

	private final String DEFAULT_ALIAS = "_default_";
	private final String FROM;
	private final String WHERE;
	private final String COUNT_PRE;

	@SuppressWarnings("unchecked")
	public DAOHibernate() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		FROM = "from " + this.entityClass.getSimpleName() + " " + DEFAULT_ALIAS
				+ " ";
		WHERE = " where 1=1 ";
		COUNT_PRE = "select count(*) ";
	}

	@Override
	public T getById(PK id) {
		return (T) getSession().get(this.entityClass, id);
	}

	@Override
	public List<T> getByProperty(String propertyName, Object propertyValue) {
		String paramKey = formatParamKey(propertyName, 0);
		String hql = FROM + " where " + DEFAULT_ALIAS + "." + propertyName
				+ "=:" + paramKey;
		Query query = getSession().createQuery(hql);
		setParameter(query, paramKey, propertyValue);
		return query.list();
	}
	
	@Override
	public List<T> getByCondition(Condition... conditions) {
		List<Condition> cs = new ArrayList<Condition>();
		for (Condition condition : conditions) {
			cs.add(condition);
		}
		return getByConditions(cs);
	}

	@Override
	public List<T> getByCondition(String sortString, Condition... conditions) {
		List<Condition> cs = new ArrayList<Condition>();
		for (Condition condition : conditions) {
			cs.add(condition);
		}
		return getByConditions(cs, sortString);
	}

	@Override
	public List<T> getByCondition(Condition condition) {
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(condition);
		return getByConditions(conditions);
	}

	@Override
	public List<T> getByConditions(List<Condition> conditions) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String conditionHql = processConditions(conditions, paramMap);
		return this.executeQuery(FROM + WHERE + conditionHql, -1, -1, paramMap);
	}

	@Override
	public List<T> getByCondition(Condition condition, String sortString) {
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(condition);
		return getByConditions(conditions, sortString);
	}
	
	@Override
	public List<T> getByConditions(List<Condition> conditions, String sortString) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String conditionHql = processConditions(conditions, paramMap);
		String hql = FROM + WHERE + conditionHql + " order by " + sortString;
		return this.executeQuery(hql, -1, -1, paramMap);
	}

	@Override
	public List<T> getAll() {
		Query query = getSession().createQuery(FROM);
		return query.list();
	}

	@Override
	public List<T> getAll(String orderString) {
		Query query = getSession().createQuery(FROM + " ORDER BY " + orderString);
		return query.list();
	}

	@Override
	public ResponseJSON getAll(QueryObject queryObject) {
		ResponseJSON json = new ResponseJSON();
		if (queryObject != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String conditionHql = processConditions(
					queryObject.getConditions(), paramMap);
			String countHql = COUNT_PRE + FROM + WHERE + conditionHql;
			String sortString = (queryObject.getSortinfo() != null) ? queryObject
					.getSortinfo().toString() : " order by " + DEFAULT_ALIAS
					+ ".id desc ";
			String queryHql = FROM + WHERE + conditionHql + sortString;

			int totalRecords = 0;
			totalRecords = unique(countHql, Number.class, paramMap).intValue();
			PageInfo pageInfo = queryObject.getPageinfo();
			int currentPage = pageInfo.getCurrentPage();
			int limit = pageInfo.getRecordsPerPage();
			List<T> datas = executeQuery(queryHql, currentPage, limit, paramMap);
			pageInfo.setTotalRecords(totalRecords);
			json.put("data", datas);
			json.put("pageinfo", pageInfo);
			json.put("sortinfo", queryObject.getSortinfo());
		} else {
			json.setResult(false);
			json.setMsg("the parameter is not correct, could not execute this query.");
		}
		return json;
	}

	/**
	 * 如果key形如：user.id， 则处理成：user_id_0形式， 如果Condition有多个，则传入index，
	 * 处理成：user_id_index形式
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	protected String formatParamKey(String key, int index) {
		String paramKey = null;
		if (key.indexOf('.') != -1) {
			paramKey = key.replace(".", "_") + "_" + index;
		} else {
			paramKey = key + "_" + index;
		}
		return paramKey;
	}

	protected String processConditions(List<Condition> conditions,
			Map<String, Object> paramMap) {
		StringBuffer temp = new StringBuffer("");
		int index = 0;
		for (Condition condition : conditions) {
			String key = condition.getKey();
			Object value = condition.getValue();
			if (StringUtils.hasText(key) && value != null) {
				String paramKey = formatParamKey(key, index);
				// if operator is ' between ', deal with it.
				if (condition.getOperator().equals(Condition.BETWEEN)) {
					String param1 = paramKey + "0";
					String param2 = paramKey + "1";
					temp.append(" and " + DEFAULT_ALIAS + "."
							+ condition.getKey() + condition.getOperator()
							+ ":" + param1 + " and :" + param2);
					if (value instanceof String) {
						Object[] values = value.toString().split(
								Condition.JOIN_SYMBOL);
						Object[] vs = new Object[2];
						if (condition.getValueType().equals(Integer.class)) {
							vs[0] = Integer.parseInt(values[0].toString());
							vs[1] = Integer.parseInt(values[1].toString());
						} else if (condition.getValueType().equals(Long.class)) {
							vs[0] = Long.parseLong(values[0].toString());
							vs[1] = Long.parseLong(values[1].toString());
						} else if (condition.getValueType()
								.equals(Double.class)) {
							vs[0] = Double.parseDouble(values[0].toString());
							vs[1] = Double.parseDouble(values[1].toString());
						} else {
							vs = values;
						}
						paramMap.put(param1, values[0]);
						paramMap.put(param2, values[1]);
					} else if (value instanceof Collection) {
						paramMap.put(param1, ((Collection) value).toArray()[0]);
						paramMap.put(param2, ((Collection) value).toArray()[1]);
					}
				} else {
					// if operator is ' like ', add pre-end symbol '%'.
					if (condition.getOperator().equals(Condition.LIKE)) {
						value = Condition.LIKE_SYMBOL + value.toString()
								+ Condition.LIKE_SYMBOL;
						// if operator is ' in ', change the string(joined with
						// commas) to a collection.
					} else if (condition.getOperator().equals(Condition.IN)) {
						if (value instanceof String) {
							String[] arrays = value.toString().split(
									Condition.JOIN_SYMBOL);
							List<Object> list = new ArrayList<Object>();
							if (condition.getValueType().equals(Long.class)) {
								for (String string : arrays) {
									list.add(Long.parseLong(string));
								}
							} else if (condition.getValueType().equals(
									Integer.class)) {
								for (String string : arrays) {
									list.add(Integer.parseInt(string));
								}
							} else if (condition.getValueType().equals(
									Double.class)) {
								for (String string : arrays) {
									list.add(Double.parseDouble(string));
								}
							} else {
								for (String string : arrays) {
									list.add(string);
								}
							}
							value = list;
						}
						// if operator is ' between ', do sth.
					} else {
						if (condition.getValueType().equals(Long.class)
								&& (condition.getValue() instanceof String)) {
							condition.setValue(Long
									.parseLong((String) condition.getValue()));
						} else if (condition.getValueType().equals(
								Integer.class)
								&& (condition.getValue() instanceof String)) {
							condition.setValue(Integer
									.parseInt((String) condition.getValue()));
						} else if (condition.getValueType()
								.equals(Double.class)
								&& (condition.getValue() instanceof String)) {
							condition
									.setValue(Double
											.parseDouble((String) condition
													.getValue()));
						} else if (condition.getValueType().equals(
								Boolean.class)
								&& (condition.getValue() instanceof String)) {
							condition
									.setValue(Boolean
											.parseBoolean((String) condition
													.getValue()));
						}
					}
					temp.append(" and " + DEFAULT_ALIAS + "."
							+ condition.getKey() + condition.getOperator()
							+ ":" + paramKey);
					paramMap.put(paramKey, value);
				}
			}
			index++;
		}
		return temp.toString();
	}

	@Override
	public boolean exists(PK id) {
		return getById(id) != null;
	}

	protected <T> List<T> executeQuery(final String hql, final int currentPage,
			final int recordsPerPage, final Map<String, Object> paramMap) {
		log.info("hql=" + hql);
		Query query = getSession().createQuery(hql);
		setParameters(query, paramMap);
		if (currentPage > -1 && recordsPerPage > -1) {
			query.setMaxResults(recordsPerPage);
			int start = (currentPage - 1) * recordsPerPage;
			if (start != 0) {
				query.setFirstResult(start);
			}
		}
		if (currentPage < 0) {
			query.setFirstResult(0);
		}
		List<T> results = query.list();
		return results;
	}

	/**
	 * 根据查询条件返回唯一一条记录
	 */
	protected <O> O unique(final String hql, Class<O> resultType,
			final Map<String, Object> paramMap) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramMap);
		return (O) query.setMaxResults(1).uniqueResult();
	}
	
	@Override
	public void deleteByIdLogic(PK id) {
		T t = getById(id);
		t.setFlag(-1);
		getSession().update(t);
	}

	@Override
	public void deleteById(PK id) {
		getSession().delete(getById(id));
	}

}
