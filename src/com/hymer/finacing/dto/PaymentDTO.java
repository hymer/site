package com.hymer.finacing.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.hymer.core.BaseEntity;
import com.hymer.core.DTO;
import com.hymer.core.util.JsonDateSerializer;
import com.hymer.finacing.entity.Payment;

public class PaymentDTO extends DTO {

	private Long id;
	private Long finacingId; // 属于哪个公司的财务
	private Long contractId;
	private String paymentType; // purchase | sales
	private boolean refund = false; //是否为退款
	private Double amount; // 涉及金额,永远为正
	private String remarks; //付/收款说明、备注
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dealDate; // 付/收款时间
	
	
	@Override
	public BaseEntity toEntity() {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setDealDate(dealDate);
		payment.setFinacingId(finacingId);
		payment.setPaymentType(paymentType);
		payment.setRemarks(remarks);
		payment.setRefund(refund);
		return payment;
	}
	@Override
	public void fromEntity(BaseEntity entity) {
		Payment payment = (Payment) entity;
		setAmount(payment.getAmount());
		setDealDate(payment.getDealDate());
		setFinacingId(payment.getFinacingId());
		setId(payment.getId());
		setPaymentType(payment.getPaymentType());
		setRemarks(payment.getRemarks());
		setRefund(payment.isRefund());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFinacingId() {
		return finacingId;
	}
	public void setFinacingId(Long finacingId) {
		this.finacingId = finacingId;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public boolean isRefund() {
		return refund;
	}
	public void setRefund(boolean refund) {
		this.refund = refund;
	}
	
}
