package com.hymer.contract.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hymer.contract.dao.ItemDAO;
import com.hymer.contract.entity.Item;
import com.hymer.core.CommonService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;

@Service
public class ItemService extends CommonService {

	@Autowired
	private ItemDAO itemDAO;

	public Item getById(Long id) {
		return itemDAO.getById(id);
	}
	
	public ResponseJSON query(QueryObject queryObject) {
		List<Condition> realConditions = new ArrayList<Condition>();
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getValue() == null
					|| !StringUtils.hasText(condition.getValue().toString())) {
				continue;
			}
			if (condition.getKey().equals("itemCode")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			}
			if (condition.getKey().equals("itemName")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			}
		}
		queryObject.setConditions(realConditions);
		ResponseJSON json = itemDAO.getAll(queryObject);
		return json;
	}
	
	public void deleteById(Long id) {
		Item item = itemDAO.getById(id);
		itemDAO.delete(item);
	}

	public void deleteByIds(Set<Long> ids) {
		for (Long id : ids) {
			itemDAO.deleteById(id);
		}
	}
	
}
