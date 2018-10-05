package com.bharti.domain;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="subjects")
public class Subject 
{
	private long sid;
	
	private String subject;
	
	private String url;
	
	private int displayOrder;
	
	private boolean showOnHomePage;
	
	private String subject_image;
	
	private Date publishDate;
	
	private Date createDate;
	
	private Date deleteDate;
	
	private Subject parent;
	
	private Set<Subject> childSubjects = new HashSet<>();

	private LoginInfo loginInfo;
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	@Column(nullable=false)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(nullable=false)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(nullable=false)
	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Column
	public boolean isShowOnHomePage() {
		return showOnHomePage;
	}

	public void setShowOnHomePage(boolean showOnHomePage) {
		this.showOnHomePage = showOnHomePage;
	}

	@Column
	public String getSubject_image() {
		return subject_image;
	}

	public void setSubject_image(String subject_image) {
		this.subject_image = subject_image;
	}

	@Column
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(nullable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@ManyToOne  
    @JoinColumn(name = "parent_id")
	public Subject getParent() {
		return parent;
	}

	public void setParent(Subject parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY)  
	public Set<Subject> getChildSubjects() {
		return childSubjects;
	}

	public void setChildSubjects(Set<Subject> childSubjects) {
		this.childSubjects = childSubjects;
	}

	private Set<Keynote> keynotes = new HashSet<>();

	@OneToMany(mappedBy="subject", cascade=CascadeType.ALL, fetch=FetchType.LAZY)  
	public Set<Keynote> getKeynotes() {
		return keynotes;
	}

	public void setKeynotes(Set<Keynote> keynotes) {
		this.keynotes = keynotes;
	}
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="userid" , referencedColumnName="userid") 
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}
