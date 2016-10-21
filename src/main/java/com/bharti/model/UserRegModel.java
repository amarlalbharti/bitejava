package com.bharti.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserRegModel implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9109891689929237489L;
	
	@NotBlank(message="Name is required !")
	private String fullName;
	
	@NotEmpty(message="Email id is required !")
	@Email(message="Please enter valid email !")
	private String email;
	
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z]).{6,20}",message="Please enter valid password")
	private String password;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
