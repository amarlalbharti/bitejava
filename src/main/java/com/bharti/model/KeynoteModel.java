package com.bharti.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.bharti.domain.Keynote;
import com.bharti.domain.Subject;

public class KeynoteModel
{
	private long kid;
	
	
	private Subject subject;
	
	@NotEmpty(message="KeyNote is required")
	private String keynote;
	
	@NotEmpty(message="Display order is required")
	private String displayOrder;

	private boolean showOnHomePage;
	
	
	@NotEmpty(message="Detail is required")
	private String knDetail;

	private Keynote parent;

	private String seoDescription;

	public long getKid() {
		return kid;
	}

	public void setKid(long kid) {
		this.kid = kid;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getKeynote() {
		return keynote;
	}

	public void setKeynote(String keynote) {
		this.keynote = keynote;
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

	public String getKnDetail() {
		return knDetail;
	}

	public void setKnDetail(String knDetail) {
		this.knDetail = knDetail;
	}
	
	public Keynote getParent() {
		return parent;
	}

	public void setParent(Keynote parent) {
		this.parent = parent;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}
	
	
	
}
