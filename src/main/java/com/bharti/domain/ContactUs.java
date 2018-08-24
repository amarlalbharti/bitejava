package com.bharti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact_us")
public class ContactUs extends CommonDomain implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7128199581261158837L;

	
	@Id
	@Column(name="contactus_id",nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int contactUsId;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="comment", nullable=false)
	private String comment;

	@Column(name="mark_as_read")
	private Boolean markAsRead;
	
	public int getContactUsId() {
		return contactUsId;
	}

	public void setContactUsId(int contactUsId) {
		this.contactUsId = contactUsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getMarkAsRead() {
		return markAsRead;
	}

	public void setMarkAsRead(Boolean markAsRead) {
		this.markAsRead = markAsRead;
	}
	
	
}
