package com.bharti.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bharti.domain.Registration;
import com.bharti.domain.UserRole;

@Entity
@Table(name="login_info")
public class LoginInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6233333810273158850L;

	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lid;
	
	@Column(name = "userid", nullable=false, unique=true)
	private String userid;
	
	@Column(name = "username", unique=true)
	private String username;
	
	@Column(name = "password", nullable=false)
	private String password;
	
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@Column(name = "forgotpwdid")
	private String forgotpwdid;
	
	@Column(name = "active", nullable=false)
	private String isActive;
	
	
	@OneToMany(mappedBy="loginInfo", fetch = FetchType.EAGER, cascade=CascadeType.ALL)  
	private Set<UserRole> roles = new HashSet();

	@OneToOne(mappedBy="loginInfo")
    private Registration registration;


	

	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getForgotpwdid() {
		return forgotpwdid;
	}
	public void setForgotpwdid(String forgotpwdid) {
		this.forgotpwdid = forgotpwdid;
	}
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
}
