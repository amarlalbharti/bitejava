package com.bharti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="seo_keynote")
public class SeoKeynote {
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long seoId;
	
	@Column
	private String Title;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private String keywrds;
	
	@Column
	private String imageUrl;
	
	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="kid" , referencedColumnName="kid") 
	private Keynote keynote;
	
	public long getSeoId() {
		return seoId;
	}

	public void setSeoId(long seoId) {
		this.seoId = seoId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywrds() {
		return keywrds;
	}

	public void setKeywrds(String keywrds) {
		this.keywrds = keywrds;
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
