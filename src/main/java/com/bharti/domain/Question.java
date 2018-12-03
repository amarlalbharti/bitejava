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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question 
{
	private long qid;
	
	private String qustion;
	
	private Date createDate;

	private Date modifyDate;

	private Date publishDate;

	private Date deleteDate;
	
	private String userType;
	
	private int views;
	
	private String image;
	
	private Set<Tag> tags = new HashSet<>();
	
	private Set<Answers> answers = new HashSet<>();
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	@Column(nullable=false)
	@Lob
	public String getQustion() {
		return qustion;
	}

	public void setQustion(String qustion) {
		this.qustion = qustion;
	}
	
	
	@Column(nullable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@Column
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Column
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name="tag_question", 
                joinColumns={@JoinColumn(name="qid")}, 
                inverseJoinColumns={@JoinColumn(name="tid")})
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@OneToMany(mappedBy="question", cascade=CascadeType.ALL, fetch=FetchType.LAZY)  
	public Set<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answers> answers) {
		this.answers = answers;
	}

	
	
}
