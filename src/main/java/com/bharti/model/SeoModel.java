package com.bharti.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.bharti.domain.Keynote;

public class SeoModel {

	@NotEmpty(message="SEO Title is required")
	private String title;
	
	@NotEmpty(message="SEO description is required")
	private String description;
	
	@NotEmpty(message="SEO keywrds is required")
	private String keywords;
	
	private String imageUrl;
	
	private Keynote keynote;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Keynote getKeynote() {
		return keynote;
	}

	public void setKeynote(Keynote keynote) {
		this.keynote = keynote;
	}
	
	
}
