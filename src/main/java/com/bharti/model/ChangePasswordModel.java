package com.bharti.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordModel {
	
	@NotEmpty(message="Old password is required !")
	private String oldPwd;
	
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z]).{6,20}",message="Please enter valid password !")
	private String newPwd;
	
	@NotEmpty(message="Password does not matched !")
	private String confPwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfPwd() {
		return confPwd;
	}

	public void setConfPwd(String confPwd) {
		this.confPwd = confPwd;
	}
	
	
	
}
