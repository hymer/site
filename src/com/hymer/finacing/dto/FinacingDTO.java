package com.hymer.finacing.dto;

import com.hymer.core.BaseEntity;
import com.hymer.core.DTO;

public class FinacingDTO extends DTO {
	
	private Long id;
	private String finacingType;
	private Long companyId;
	private String companyName;
	private Double contractAmount; //合同金额
	private Double paidAmount; //已付/已收金额
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFinacingType() {
		return finacingType;
	}

	public void setFinacingType(String finacingType) {
		this.finacingType = finacingType;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	@Override
	public BaseEntity toEntity() {
		// TODO Auto-generated method stub
		return super.toEntity();
	}

	@Override
	public void fromEntity(BaseEntity entity) {
		// TODO Auto-generated method stub
		super.fromEntity(entity);
	}

}
