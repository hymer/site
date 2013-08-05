package com.hymer.contract.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hymer.core.BaseEntity;

@Entity
public class ItemInContract extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contractId")
	private Contract contract; //合同
	
	private String itemCode; //产品编码
	private String itemName; //产品名称
	private String itemStandard; //规格
	private String itemUnit; //单位
	private Double itemPrice; //单价
	private Integer itemCount; //数量
	private Double itemTotalPrice;//金额
	private String itemRemark; //备注
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String name) {
		this.itemName = name;
	}
	public String getItemStandard() {
		return itemStandard;
	}
	public void setItemStandard(String standard) {
		this.itemStandard = standard;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String unit) {
		this.itemUnit = unit;
	}
	public Double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Double price) {
		this.itemPrice = price;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer count) {
		this.itemCount = count;
	}
	public String getItemRemark() {
		return itemRemark;
	}
	public void setItemRemark(String remark) {
		this.itemRemark = remark;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public Double getItemTotalPrice() {
		return itemTotalPrice;
	}
	public void setItemTotalPrice(Double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
}
