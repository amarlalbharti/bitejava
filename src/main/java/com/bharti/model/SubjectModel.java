package com.bharti.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class SubjectModel {

	private long sid;
	
	@NotEmpty(message="Subject is required")
	private String subject;
	
	private String url;
	
	
	@NotEmpty(message="Display Order is required")
	private String displayOrder;

	private boolean showOnHomePage;
	
	private MultipartFile subject_image;
	
	
	
	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public boolean isShowOnHomePage() {
		return showOnHomePage;
	}

	public void setShowOnHomePage(boolean showOnHomePage) {
		this.showOnHomePage = showOnHomePage;
	}

	public MultipartFile getSubject_image() {
		return subject_image;
	}

	public void setSubject_image(MultipartFile subject_image) {
		this.subject_image = subject_image;
	}
	
	
	
}
