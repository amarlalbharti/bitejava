package com.bharti.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attribute")
public class Attribute {
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer attributeId;
	
	@Column(name="attribute_name" ,nullable=false)
	private String attributeName;
	
	@Column(name="attribute_value" ,nullable=false)
	private String attributeValue;
	
	@Column(name="active" ,nullable=false)
	private Boolean active;

	@Column(name="modify_date")
	private Date modifyDate;
	
	@Column(name="create_date" ,nullable=false)
	private Date createDate;
	
	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	
	
}
