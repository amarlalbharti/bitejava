package com.bharti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comments extends CommonDomain {
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long commentId;
	
	@Column(nullable=false)
	private String comment;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String email;

	@ManyToOne  
    @JoinColumn(name = "kid")
	private Keynote keynote;
	
	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Keynote getKeynote() {
		return keynote;
	}

	public void setKeynote(Keynote keynote) {
		this.keynote = keynote;
	}
	
	
}
