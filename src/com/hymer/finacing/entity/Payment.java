package com.hymer.finacing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.hymer.contract.entity.Contract;
import com.hymer.core.BaseEntity;
import com.hymer.core.util.JsonDateSerializer;

@Entity
public class Payment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long finacingId; // 属于哪个公司的财务
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contractId")
	private Contract contract; // 属于哪个合同
	private String paymentType; // purchase | sales
	private boolean refund = false; //是否为退款
	private Double amount; // 涉及金额,永远为正
	@Column(length=1024)
	private String remarks; //付/收款说明、备注
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dealDate; // 付/收款时间

	public Long getFinacingId() {
		return finacingId;
	}

	public void setFinacingId(Long finacingId) {
		this.finacingId = finacingId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public boolean isRefund() {
		return refund;
	}

	public void setRefund(boolean refund) {
		this.refund = refund;
	}

}
