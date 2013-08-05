package com.hymer.supplier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hymer.core.CommonService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.supplier.dao.SupplierDAO;
import com.hymer.supplier.entity.Supplier;

@Service
public class SupplierService extends CommonService {

	@Autowired
	private SupplierDAO supplierDAO;

	public Supplier getById(Long id) {
		return supplierDAO.getById(id);
	}
	
	public void addSupplier(Supplier supplier) {
		supplierDAO.save(supplier);
	}
	
	public void updateSupplier(Supplier supplier) {
		Supplier entity = getById(supplier.getId());
		entity.setAddress(supplier.getAddress());
		entity.setEmail(supplier.getEmail());
		entity.setMobile(supplier.getMobile());
		entity.setFax(supplier.getFax());
		entity.setDescription(supplier.getDescription());
		entity.setLinkman(supplier.getLinkman());
		entity.setCompanyName(supplier.getCompanyName());
		supplierDAO.update(entity);
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
		ResponseJSON json = supplierDAO.getAll(queryObject);
		return json;
	}

	public void deleteById(Long id) {
		Supplier supplier = supplierDAO.getById(id);
		supplierDAO.delete(supplier);
	}

}
