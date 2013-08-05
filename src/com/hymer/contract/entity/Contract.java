package com.hymer.contract.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.hymer.core.BaseEntity;
import com.hymer.customer.entity.Customer;
import com.hymer.finacing.entity.Payment;
import com.hymer.supplier.entity.Supplier;

@Entity
public class Contract extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static final String TYPE_PURCHASE = "purchase";// 采购合同
	public static final String TYPE_SALES = "sales";// 销售合同

	private Double amount; // 总金额
	private String contractType; // 销售合同/采购合同
	private String modifyTimes = "0"; // 修改次数

	private String supplierBank; // 供应商开户行
	private String supplierBankAccount; // 供应商银行账号
	private String supplierTaxNo; // 供应商税务登记号
	private String customerBank; // 客户开户行
	private String customerBankAccount; // 客户银行账号
	private String customerTaxNo; // 客户税务登记号

	@ManyToOne
	@JoinColumn(name = "supplierId")
	private Supplier supplier;
	private String supplierName;
	private String supplierAddress;
	private String supplierLinkman;
	private String supplierMobile;
	private String supplierFax;
	private String supplierEmail;

	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	private String customerName;
	private String customerAddress;
	private String customerLinkman;
	private String customerMobile;
	private String customerFax;
	private String customerEmail;

	@OneToMany(targetEntity = ItemInContract.class, mappedBy = "contract", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("id")
	private List<ItemInContract> items = new ArrayList<ItemInContract>();
	
	@OneToMany(targetEntity = Payment.class, mappedBy = "contract", cascade = CascadeType.ALL)
	@OrderBy("id")
	private List<Payment> payments = new ArrayList<Payment>();

	@Column(length = 10240)
	private String content; // 合同内容、条款
	@Column(length = 1024)
	private String itemRemarks; // 空白行备注
	private Date signDate; // 签订日期
	private int status = 0; // 状态

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<ItemInContract> getItems() {
		return items;
	}

	public void setItems(List<ItemInContract> items) {
		this.items = items;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getModifyTimes() {
		return modifyTimes;
	}

	public void setModifyTimes(String modifyTimes) {
		this.modifyTimes = modifyTimes;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItemRemarks() {
		return itemRemarks;
	}

	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

}
