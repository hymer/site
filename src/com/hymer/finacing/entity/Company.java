package com.hymer.finacing.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.hymer.core.BaseEntity;

@MappedSuperclass
public abstract class Company extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String companyName;
	private String linkman;
	private String mobile;
	private String fax;
	private String email;
	private String address;
	@Column(length=1024)
	private String description;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
