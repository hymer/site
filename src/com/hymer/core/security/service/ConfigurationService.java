package com.hymer.core.security.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hymer.core.BaseEntity;
import com.hymer.core.CommonService;
import com.hymer.core.Configuration;
import com.hymer.core.ServiceException;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.security.dao.PreferencesDAO;
import com.hymer.core.security.entity.Preferences;

@Service
public class ConfigurationService extends CommonService {

	@Autowired
	private PreferencesDAO preferencesDAO;

	public void initPreferences() {
		List<Preferences> all = preferencesDAO.getAll();
		if (all.isEmpty()) {
			Map<String, String> map = Configuration.getInitConfigs();
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key);
				preferencesDAO.save(new Preferences(key, value));
			}
		} else {
			refreshPreferences();
		}
	}

	public void refreshPreferences() {
		List<Preferences> all = preferencesDAO.getAll();
		Configuration.clear();
		for (Preferences p : all) {
			Configuration.put(p.getKey(), p.getValue());
		}
	}
	
	public ResponseJSON query(QueryObject queryObject) {
		List<Condition> realConditions = new ArrayList<Condition>();
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getValue() == null
					|| !StringUtils.hasText(condition.getValue().toString())) {
				continue;
			}
			if (condition.getKey().equals("key") || condition.getKey().equals("value")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			}
		}
		queryObject.setConditions(realConditions);
		ResponseJSON json = preferencesDAO.getAll(queryObject);
		return json;
	}

	public Preferences getPreferenceById(Long id) {
		return preferencesDAO.getById(id);
	}
	
	@Override
	public <T extends BaseEntity> void saveOrUpdate(T model) {
		super.saveOrUpdate(model);
		refreshPreferences();
	}

	@Override
	public <T extends BaseEntity> void update(T model) {
		super.update(model);
		refreshPreferences();
	}
	
	public void deletePreferenceById(Long id) {
		delete(preferencesDAO.getById(id));
	}
	
	@Override
	public <T extends BaseEntity> void delete(T model) {
		preferencesDAO.delete(model);
		refreshPreferences();
	}
	

	public void saveOrUpdatePreferences(String key, String value, String removed)
			throws ServiceException {
		if (!StringUtils.hasText(key)) {
			return;
		}
		List<Preferences> preferences = preferencesDAO
				.getByProperty("key", key);
		Preferences p = null;
		if (preferences.isEmpty()) {
			if (!"true".equals(removed)) {
				p = new Preferences();
				p.setKey(key);
				p.setValue(value);
				preferencesDAO.save(p);
			}
		} else if (preferences.size() == 1) {
			p = preferences.get(0);
			if (!"true".equals(removed)) {
				p.setValue(value);
				preferencesDAO.update(p);
			} else {
				preferencesDAO.delete(p);
			}
		} else {
			throw new ServiceException("该配置项重复，请先删除重要配置项！");
		}
	}

}
