package com.bharti.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactUsModel {
	
	
	@NotEmpty(message="Name is required !")
	private String name;
	
	@NotEmpty(message="Email is required !")
	@Email(message="Please enter valid email !")
	private String email;
	
	@Pattern(regexp="(^$|[0-9]{10})", message="Please enter valid mobile number !")
	private String mobile;
	
	@NotEmpty(message="Comment is required !")
	private String comment;

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
	
	
	
	
}
