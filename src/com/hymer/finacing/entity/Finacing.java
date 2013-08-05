package com.hymer.finacing.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.hymer.core.BaseEntity;
import com.hymer.customer.entity.Customer;
import com.hymer.supplier.entity.Supplier;

@Entity
public class Finacing extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String finacingType; // 采购或者是销售purchase | sales
	@ManyToOne
	@JoinColumn(name = "supplierId")
	private Supplier supplier; // 采购时才有
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer; // 销售时才有

	@OneToMany(mappedBy = "finacingId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Payment> payments = new ArrayList<Payment>(); // 款项

	public String getFinacingType() {
		return finacingType;
	}

	public void setFinacingType(String finacingType) {
		this.finacingType = finacingType;
	}

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

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	
}
