package com.hymer.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hymer.core.CommonService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.customer.dao.CustomerDAO;
import com.hymer.customer.entity.Customer;

@Service
public class CustomerService extends CommonService {

	@Autowired
	private CustomerDAO customerDAO;

	public Customer getById(Long id) {
		return customerDAO.getById(id);
	}
	
	public void addCustomer(Customer customer) {
		customerDAO.save(customer);
	}
	
	public void updateCustomer(Customer customer) {
		Customer entity = getById(customer.getId());
		entity.setAddress(customer.getAddress());
		entity.setEmail(customer.getEmail());
		entity.setMobile(customer.getMobile());
		entity.setFax(customer.getFax());
		entity.setDescription(customer.getDescription());
		entity.setLinkman(customer.getLinkman());
		entity.setCompanyName(customer.getCompanyName());
		customerDAO.update(entity);
	}
	
	public ResponseJSON query(QueryObject queryObject) {
		List<Condition> realConditions = new ArrayList<Condition>();
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getValue() == null
					|| !StringUtils.hasText(condition.getValue().toString())) {
				continue;
			}
			if (condition.getKey().equals("companyName")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("linkman")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			}
		}
		queryObject.setConditions(realConditions);
		ResponseJSON json = customerDAO.getAll(queryObject);
		return json;
	}

	public void deleteById(Long id) {
		Customer customer = customerDAO.getById(id);
		customerDAO.delete(customer);
	}

}
