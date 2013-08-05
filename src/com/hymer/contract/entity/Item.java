package com.hymer.contract.entity;

import javax.persistence.Entity;

import com.hymer.core.BaseEntity;

@Entity
public class Item extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String itemCode; //产品编码
	private String itemName; //产品名称
	private String itemStandard; //规格
	private String itemUnit; //单位
	private Double itemPrice; //单价
	private String itemRemark; //备注
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
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
	public String getItemRemark() {
		return itemRemark;
	}
	public void setItemRemark(String remark) {
		this.itemRemark = remark;
	}
	
}
