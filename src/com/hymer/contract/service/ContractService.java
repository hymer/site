package com.hymer.contract.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hymer.contract.dao.ContractDAO;
import com.hymer.contract.dao.ItemInContractDAO;
import com.hymer.contract.dto.ContractDTO;
import com.hymer.contract.dto.ItemInContractDTO;
import com.hymer.contract.entity.Contract;
import com.hymer.contract.entity.ItemInContract;
import com.hymer.core.CommonService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.customer.dao.CustomerDAO;
import com.hymer.customer.entity.Customer;
import com.hymer.finacing.dao.FinacingDAO;
import com.hymer.finacing.entity.Finacing;
import com.hymer.supplier.dao.SupplierDAO;
import com.hymer.supplier.entity.Supplier;

@Service
public class ContractService extends CommonService {
	@Autowired
	private ContractDAO contractDAO;
	@Autowired
	private SupplierDAO supplierDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private ItemInContractDAO itemInContractDAO;
	@Autowired
	private FinacingDAO finacingDAO;

	public Contract getById(Long id) {
		return contractDAO.getById(id);
	}

	public void addContract(ContractDTO contract, Set<ItemInContract> items) {
		Contract entity = (Contract) contract.toEntity();
		Supplier supplier = null;
		Customer customer = null;
		Finacing finacing = null;
		boolean existFinacing = false;
		Condition condition = new Condition();
		condition.setOperator(Condition.EQ);
		condition.setValueType(Long.class);
		if (contract.getContractType().equals(Contract.TYPE_PURCHASE)) {
			supplier = supplierDAO.getById(contract.getSupplierId());
			condition.setKey("supplier.id");
			condition.setValue(contract.getSupplierId());
			List<Finacing> tmp = finacingDAO.getByCondition(condition);
			if (tmp.isEmpty()) {
				finacing = new Finacing();
				finacing.setFinacingType(Contract.TYPE_PURCHASE);
				finacing.setSupplier(supplier);
			} else {
				existFinacing = true;
			}
		} else if (contract.getContractType().equals(Contract.TYPE_SALES)) {
			customer = customerDAO.getById(contract.getCustomerId());
			condition.setKey("customer.id");
			condition.setValue(contract.getCustomerId());
			List<Finacing> tmp = finacingDAO.getByCondition(condition);
			if (tmp.isEmpty()) {
				finacing = new Finacing();
				finacing.setFinacingType(Contract.TYPE_SALES);
				finacing.setCustomer(customer);
			} else {
				existFinacing = true;
			}
		}
		entity.setSupplier(supplier);
		entity.setCustomer(customer);

		contractDAO.save(entity);
		if (!existFinacing) {
			finacingDAO.save(finacing);
		}

		for (ItemInContract item : items) {
			item.setContract(entity);
			itemInContractDAO.save(item);
			entity.getItems().add(item);
		}
		contractDAO.update(entity);
	}

	public void updateContract(ContractDTO contract, Set<ItemInContract> items) {
		Contract entity = getById(contract.getId());
		entity.setContent(contract.getContent());
		entity.setCustomerAddress(contract.getCustomerAddress());
		entity.setCustomerEmail(contract.getCustomerEmail());
		entity.setCustomerFax(contract.getCustomerFax());
		entity.setCustomerLinkman(contract.getCustomerLinkman());
		entity.setCustomerMobile(contract.getCustomerMobile());
		entity.setSignDate(contract.getSignDate());
		entity.setStatus(contract.getStatus());
		entity.setSupplierAddress(contract.getSupplierAddress());
		entity.setSupplierEmail(contract.getSupplierEmail());
		entity.setSupplierFax(contract.getSupplierFax());
		entity.setSupplierLinkman(contract.getSupplierLinkman());
		entity.setSupplierMobile(contract.getSupplierMobile());
		entity.setFlag(contract.getFlag());
		entity.setAmount(contract.getAmount());
		entity.setSupplierName(contract.getSupplierName());
		entity.setCustomerName(contract.getCustomerName());
		entity.setSupplierBank(contract.getSupplierBank());
		entity.setSupplierBankAccount(contract.getSupplierBankAccount());
		entity.setSupplierTaxNo(contract.getSupplierTaxNo());
		entity.setCustomerBank(contract.getCustomerBank());
		entity.setCustomerBankAccount(contract.getCustomerBankAccount());
		entity.setCustomerTaxNo(contract.getCustomerTaxNo());
		entity.setModifyTimes(contract.getModifyTimes());
		entity.setItemRemarks(contract.getItemRemarks());

		List<ItemInContract> items2 = new ArrayList<ItemInContract>();
		for (ItemInContract item : items) {
			if (item.getId() != null) {
				ItemInContract tmp = itemInContractDAO.getById(item.getId());
				tmp.setItemCount(item.getItemCount());
				tmp.setItemName(item.getItemName());
				tmp.setItemPrice(item.getItemPrice());
				tmp.setItemRemark(item.getItemRemark());
				tmp.setItemStandard(item.getItemStandard());
				tmp.setItemTotalPrice(item.getItemTotalPrice());
				tmp.setItemUnit(item.getItemUnit());

				tmp.setContract(entity);
				itemInContractDAO.update(tmp);

				items2.add(tmp);
			} else {
				item.setContract(entity);
				itemInContractDAO.save(item);
				items2.add(item);
			}
		}
		for (ItemInContract old : entity.getItems()) {
			boolean exists = false;
			for (ItemInContract now : items2) {
				if (old.getId().equals(now.getId())) {
					exists = true;
				}
			}
			if (!exists) {
				itemInContractDAO.delete(old);
			}
		}
		entity.setItems(items2);
		contractDAO.update(entity);
	}

	@SuppressWarnings("unchecked")
	public ResponseJSON query(QueryObject queryObject) {
		List<Condition> realConditions = new ArrayList<Condition>();
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getValue() == null
					|| !StringUtils.hasText(condition.getValue().toString())) {
				continue;
			}
			if (condition.getKey().equals("contractType")) {
				realConditions.add(condition);
			} else if (condition.getKey().equals("supplierName")) {
				condition.setKey("supplier.supplierName");
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("customerName")) {
				condition.setKey("customer.customerName");
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("supplierLinkman")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("customerLinkman")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("customerName")) {
				condition.setKey("customer.customerName");
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			}
		}
		queryObject.setConditions(realConditions);
		ResponseJSON json = contractDAO.getAll(queryObject);
		List<ContractDTO> dtos = new ArrayList<ContractDTO>();
		for (Contract entity : (List<Contract>) json.getData().get("data")) {
			ContractDTO dto = new ContractDTO();
			dto.fromEntity(entity);
			dtos.add(dto);
		}
		json.getData().put("data", dtos);
		return json;
	}

	@SuppressWarnings("unchecked")
	public ResponseJSON queryItem(QueryObject queryObject) {
		List<Condition> realConditions = new ArrayList<Condition>();
		
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getValue() == null
					|| !StringUtils.hasText(condition.getValue().toString())) {
				continue;
			}
			if (condition.getKey().equals("itemCode") || condition.getKey().equals("itemName")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("contractType")){
				condition.setKey("contract.contractType");
				realConditions.add(condition);
			}
		}
		queryObject.setConditions(realConditions);
		ResponseJSON json = itemInContractDAO.getAll(queryObject);
		List<ItemInContractDTO> dtos = new ArrayList<ItemInContractDTO>();
		for (ItemInContract entity : (List<ItemInContract>) json.getData().get(
				"data")) {
			ItemInContractDTO dto = new ItemInContractDTO();
			dto.fromEntity(entity);
			dto.setContractId(entity.getContract().getId());
			dto.setContractType(entity.getContract().getContractType());
			dto.setSupplierName(entity.getContract().getSupplierName());
			dtos.add(dto);
		}
		json.getData().put("data", dtos);
		return json;
	}

	public void deleteById(Long id) {
		Contract contract = contractDAO.getById(id);
		contractDAO.delete(contract);
	}

}
