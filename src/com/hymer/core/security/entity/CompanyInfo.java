package com.hymer.core.security.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.hymer.core.BaseEntity;

@Entity
@Table(name = "tb_security_companyinfo")
@JsonIgnoreProperties("user")
public class CompanyInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 50)
	private String realName; // 真实姓名
	@Column(length = 2)
	private String gender; // 性别
	@Column(length = 100)
	private String position; // 职位
	@Column(length = 20)
	private String telephone; // 座机
	@Column(length = 20)
	private String fax; // 传真
	@Column(length = 20)
	private String mobile; // 手机
	@Column(length = 200)
	private String email; // 电子邮箱

	/******** 企业用户 ********/
	@Column(length = 200)
	private String companyName;
	@Column(length = 50)
	private String linkman;
	@Column(length = 100)
	private String area;
	@Column(length = 100)
	private String orgNature;
	@Column(length = 100)
	private String lealPerson; // 法人
	private Double registeredCapital; // 注册资金
	@Column(length = 50)
	private String registeredNo; // 工商注册号
	private Integer totalStaffs; // 员工总数
	@Column(length = 500)
	private String companyAddress; // 公司地址
	@Column(length = 20)
	private String postCode; // 邮编
	
	@Column(length=200)
	private String bank; // 供应商开户行
	@Column(length=100)
	private String bankAccount; // 供应商银行账号
	@Column(length=50)
	private String taxNo; // 供应商税务登记号

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, mappedBy = "info")
	private User user;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrgNature() {
		return orgNature;
	}

	public void setOrgNature(String orgNature) {
		this.orgNature = orgNature;
	}

	public String getLealPerson() {
		return lealPerson;
	}

	public void setLealPerson(String lealPerson) {
		this.lealPerson = lealPerson;
	}

	public Double getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getRegisteredNo() {
		return registeredNo;
	}

	public void setRegisteredNo(String registeredNo) {
		this.registeredNo = registeredNo;
	}

	public Integer getTotalStaffs() {
		return totalStaffs;
	}

	public void setTotalStaffs(Integer totalStaffs) {
		this.totalStaffs = totalStaffs;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	
}
