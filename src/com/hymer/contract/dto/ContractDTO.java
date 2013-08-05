package com.hymer.contract.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.hymer.contract.entity.Contract;
import com.hymer.core.BaseEntity;
import com.hymer.core.DTO;
import com.hymer.core.util.JsonDateSerializer;

public class ContractDTO extends DTO {

	private Long id;
	private String contractType;
	private String modifyTimes = "0"; // 修改次数
	
	private String supplierBank; // 供应商开户行
	private String supplierBankAccount; // 供应商银行账号
	private String supplierTaxNo; // 供应商税务登记号
	private String customerBank; // 客户开户行
	private String customerBankAccount; // 客户银行账号
	private String customerTaxNo; // 客户税务登记号
	
	private Long supplierId;
	private String supplierName;
	private String supplierAddress;
	private String supplierLinkman;
	private String supplierMobile;
	private String supplierFax;
	private String supplierEmail;

	private Long customerId;
	private String customerName;
	private String customerAddress;
	private String customerLinkman;
	private String customerMobile;
	private String customerFax;
	private String customerEmail;
	
	private String content; // 合同内容、条款
	private String itemRemarks;
	private Double amount; //总金额
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date signDate; // 签订日期
	private int flag;
	private int status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSupplierLinkman() {
		return supplierLinkman;
	}
	public void setSupplierLinkman(String supplierLinkman) {
		this.supplierLinkman = supplierLinkman;
	}
	public String getSupplierMobile() {
		return supplierMobile;
	}
	public void setSupplierMobile(String supplierMobile) {
		this.supplierMobile = supplierMobile;
	}
	public String getSupplierFax() {
		return supplierFax;
	}
	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerLinkman() {
		return customerLinkman;
	}
	public void setCustomerLinkman(String customerLinkman) {
		this.customerLinkman = customerLinkman;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerFax() {
		return customerFax;
	}
	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	@Override
	public BaseEntity toEntity() {
		Contract entity = new Contract();
		entity.setId(id);
		entity.setContent(content);
		entity.setAmount(amount);
		entity.setSignDate(signDate);
		entity.setCustomerAddress(customerAddress);
		entity.setCustomerEmail(customerEmail);
		entity.setCustomerFax(customerFax);
		entity.setCustomerLinkman(customerLinkman);
		entity.setCustomerMobile(customerMobile);
		entity.setSupplierAddress(supplierAddress);
		entity.setSupplierEmail(supplierEmail);
		entity.setSupplierFax(supplierFax);
		entity.setSupplierLinkman(supplierLinkman);
		entity.setSupplierMobile(supplierMobile);
		entity.setContractType(contractType);
		entity.setSupplierName(supplierName);
		entity.setSupplierBank(supplierBank);
		entity.setSupplierBankAccount(supplierBankAccount);
		entity.setSupplierTaxNo(supplierTaxNo);
		entity.setCustomerName(customerName);
		entity.setCustomerBank(customerBank);
		entity.setCustomerBankAccount(customerBankAccount);
		entity.setCustomerTaxNo(customerTaxNo);
		entity.setModifyTimes(modifyTimes);
		entity.setItemRemarks(itemRemarks);
		return entity;
	}
	
	@Override
	public void fromEntity(BaseEntity entity) {
		Contract contract = (Contract) entity;
		this.modifyTimes = contract.getModifyTimes();
		this.contractType = contract.getContractType();
		this.content = contract.getContent();
		this.amount = contract.getAmount();
		if (contract.getContractType().equals(Contract.TYPE_PURCHASE)) {
			this.supplierId = contract.getSupplier().getId();
		} else if (contract.getContractType().equals(Contract.TYPE_SALES)) {
			this.customerId = contract.getCustomer().getId();
		}
		this.supplierName = contract.getSupplierName();
		this.supplierAddress = contract.getSupplierAddress();
		this.supplierEmail = contract.getSupplierEmail();
		this.supplierFax = contract.getSupplierFax();
		this.supplierLinkman = contract.getSupplierLinkman();
		this.supplierMobile = contract.getSupplierMobile();
		this.supplierBank = contract.getSupplierBank();
		this.supplierBankAccount = contract.getSupplierBankAccount();
		this.supplierTaxNo = contract.getSupplierTaxNo();
		
		this.customerName = contract.getCustomerName();
		this.customerAddress = contract.getCustomerAddress();
		this.customerEmail = contract.getCustomerEmail();
		this.customerFax = contract.getCustomerFax();
		this.customerLinkman = contract.getCustomerLinkman();
		this.customerMobile = contract.getCustomerMobile();
		this.customerBank = contract.getCustomerBank();
		this.customerBankAccount = contract.getCustomerBankAccount();
		this.customerTaxNo = contract.getCustomerTaxNo();
		this.itemRemarks = contract.getItemRemarks();
		this.id = contract.getId();
		this.signDate = contract.getSignDate();
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getSupplierBank() {
		return supplierBank;
	}
	public void setSupplierBank(String supplierBank) {
		this.supplierBank = supplierBank;
	}
	public String getSupplierBankAccount() {
		return supplierBankAccount;
	}
	public void setSupplierBankAccount(String supplierBankAccount) {
		this.supplierBankAccount = supplierBankAccount;
	}
	public String getSupplierTaxNo() {
		return supplierTaxNo;
	}
	public void setSupplierTaxNo(String supplierTaxNo) {
		this.supplierTaxNo = supplierTaxNo;
	}
	public String getCustomerBank() {
		return customerBank;
	}
	public void setCustomerBank(String customerBank) {
		this.customerBank = customerBank;
	}
	public String getCustomerBankAccount() {
		return customerBankAccount;
	}
	public void setCustomerBankAccount(String customerBankAccount) {
		this.customerBankAccount = customerBankAccount;
	}
	public String getCustomerTaxNo() {
		return customerTaxNo;
	}
	public void setCustomerTaxNo(String customerTaxNo) {
		this.customerTaxNo = customerTaxNo;
	}
	public String getModifyTimes() {
		return modifyTimes;
	}
	public void setModifyTimes(String modifyTimes) {
		this.modifyTimes = modifyTimes;
	}
	public String getItemRemarks() {
		return itemRemarks;
	}
	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}
	
}
