package com.hymer.core.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hymer.core.BaseEntity;

@Entity
@Table(name = "tb_core_preferences")
public class Preferences extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Preferences() {
	}

	public Preferences(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Column(name = "pkey")
	private String key;
	@Column(name = "pvalue")
	private String value;
	private boolean disabled = false;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
