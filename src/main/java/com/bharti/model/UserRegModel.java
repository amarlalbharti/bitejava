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
	private String name;
	
	@NotEmpty(message="Email id is required !")
	@Email(message="Please enter valid email !")
	private String userid;
	
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z]).{6,20}",message="Please enter valid password")
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
