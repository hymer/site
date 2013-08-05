package com.hymer.contract.dto;

import com.hymer.contract.entity.ItemInContract;
import com.hymer.core.BaseEntity;
import com.hymer.core.DTO;

public class ItemInContractDTO extends DTO {

	private Long id;
	private String itemCode; //编码
	private String itemName; // 产品名称
	private String itemStandard; // 规格
	private String itemUnit; // 单位
	private Double itemPrice; // 单价
	private Integer itemCount; // 数量
	private String itemRemark; // 备注
	private Long contractId; // 合同ID
	private String contractType; //合同类型
	private String supplierName; // 供应商名称

	@Override
	public BaseEntity toEntity() {
		// TODO Auto-generated method stub
		return super.toEntity();
	}

	@Override
	public void fromEntity(BaseEntity entity) {
		ItemInContract itemInContract = (ItemInContract) entity;
		id = itemInContract.getId();
		itemName = itemInContract.getItemName();
		itemCode = itemInContract.getItemCode();
		itemStandard =  itemInContract.getItemStandard();
		itemUnit = itemInContract.getItemUnit();
		itemPrice = itemInContract.getItemPrice();
		itemCount = itemInContract.getItemCount();
		itemRemark = itemInContract.getItemRemark();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemStandard() {
		return itemStandard;
	}

	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

}
