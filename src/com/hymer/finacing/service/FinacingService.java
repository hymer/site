package com.hymer.finacing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hymer.contract.dao.ContractDAO;
import com.hymer.contract.entity.Contract;
import com.hymer.core.CommonService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.finacing.dao.FinacingDAO;
import com.hymer.finacing.dao.PaymentDAO;
import com.hymer.finacing.dto.FinacingDTO;
import com.hymer.finacing.dto.PaymentDTO;
import com.hymer.finacing.entity.Finacing;
import com.hymer.finacing.entity.Payment;

@Service
public class FinacingService extends CommonService {

	@Autowired
	private FinacingDAO finacingDAO;
	@Autowired
	private PaymentDAO paymentDAO;
	@Autowired
	private ContractDAO contractDAO;

	public Finacing getById(Long id) {
		return finacingDAO.getById(id);
	}

	public void addFinacing(Finacing finacing) {
		finacingDAO.save(finacing);
	}

	public void updateFinacing(Finacing finacing) {
		Finacing entity = getById(finacing.getId());
		finacingDAO.update(entity);
	}

	@SuppressWarnings("unchecked")
	public ResponseJSON query(QueryObject queryObject) {
		List<Condition> realConditions = new ArrayList<Condition>();
		String finacingType = null;
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getKey().equals("finacingType")) {
				finacingType = (String) condition.getValue();
				realConditions.add(condition);
				break;
			}
		}
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getValue() == null
					|| !StringUtils.hasText(condition.getValue().toString())) {
				continue;
			}
			if (condition.getKey().equals("companyName")) {
				if (finacingType.equals(Contract.TYPE_PURCHASE)) {
					condition.setKey("supplier.companyName");
				} else if (finacingType.equals(Contract.TYPE_SALES)) {
					condition.setKey("customer.companyName");
				}
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("linkman")) {
				if (finacingType.equals(Contract.TYPE_PURCHASE)) {
					condition.setKey("supplier.linkman");
				} else if (finacingType.equals(Contract.TYPE_SALES)) {
					condition.setKey("customer.linkman");
				}
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			}
		}
		queryObject.setConditions(realConditions);
		ResponseJSON json = finacingDAO.getAll(queryObject);

		List<FinacingDTO> dtos = new ArrayList<FinacingDTO>();
		for (Finacing finacing : (List<Finacing>) json.getData().get("data")) {
			FinacingDTO dto = new FinacingDTO();
			dto.setId(finacing.getId());
			dto.setFinacingType(finacing.getFinacingType());
			Condition condition = new Condition();
			condition.setOperator(Condition.EQ);
			if (finacingType.equals(Contract.TYPE_PURCHASE)) {
				dto.setCompanyId(finacing.getSupplier().getId());
				dto.setCompanyName(finacing.getSupplier().getCompanyName());
				condition.setKey("supplier.id");
				condition.setValue(finacing.getSupplier().getId());
			} else if (finacingType.equals(Contract.TYPE_SALES)) {
				dto.setCompanyId(finacing.getCustomer().getId());
				dto.setCompanyName(finacing.getCustomer().getCompanyName());
				condition.setKey("customer.id");
				condition.setValue(finacing.getCustomer().getId());
			}
			Condition condition2 = new Condition("contractType",
					finacing.getFinacingType());
			List<Contract> contracts = contractDAO.getByCondition(condition, condition2);
			double contractAmount = 0.0;
			for (Contract contract : contracts) {
				contractAmount += contract.getAmount();
			}
			dto.setContractAmount(contractAmount);

			double paidAmount = 0;
			for (Payment payment : finacing.getPayments()) {
				paidAmount += payment.getAmount();
			}
			dto.setPaidAmount(paidAmount);

			dtos.add(dto);
		}
		json.getData().put("data", dtos);
		return json;
	}

	public void deleteById(Long id) {
		Finacing finacing = finacingDAO.getById(id);
		finacingDAO.delete(finacing);
	}

	public Finacing viewFinacing(Long id) {
		Finacing finacing = finacingDAO.getById(id);
		// List<Condition> conditions = new ArrayList<Condition>();
		// Condition condition = new Condition();
		// if (Contract.TYPE_PURCHASE.equals(finacing.getFinacingType())) {
		// condition.setKey("supplier.id");
		// condition.setValue(finacing.getSupplier().getId());
		// } else {
		// condition.setKey("customer.id");
		// condition.setValue(finacing.getCustomer().getId());
		// }
		// Condition condition2 = new Condition("contractType",
		// finacing.getFinacingType());
		// conditions.add(condition);
		// conditions.add(condition2);
		// List<Contract> contracts = contractDAO.getByConditions(conditions);
		// finacing.getPayments();
		return finacing;
	}

	public List<Contract> getContractByFinacing(Finacing finacing) {
		List<Condition> conditions = new ArrayList<Condition>();
		Condition condition = new Condition();
		if (Contract.TYPE_PURCHASE.equals(finacing.getFinacingType())) {
			condition.setKey("supplier.id");
			condition.setValue(finacing.getSupplier().getId());
		} else {
			condition.setKey("customer.id");
			condition.setValue(finacing.getCustomer().getId());
		}
		Condition condition2 = new Condition("contractType",
				finacing.getFinacingType());
		conditions.add(condition);
		conditions.add(condition2);
		List<Contract> contracts = contractDAO.getByConditions(conditions);
		for (Contract contract : contracts) {
			for (Payment payment:contract.getPayments()) {
				log.info(payment.getId());
			}
		}
		return contracts;
	}

	public void saveOrUpdatePayment(PaymentDTO payment) {
		if (null != payment.getId()) {
			Payment entity = paymentDAO.getById(payment.getId());
			paymentDAO.update(entity);
		} else {
			Payment entity = (Payment) payment.toEntity();
			Contract contract = contractDAO.getById(payment.getContractId());
			entity.setContract(contract);
			paymentDAO.save(entity);
		}
	}

	public void deletePaymentById(Long id) {
		Payment payment = paymentDAO.getById(id);
		paymentDAO.delete(payment);
	}

}
