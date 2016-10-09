package com.bharti.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="keynote_detail")
public class KeynoteDetail 
{
	private long kdid;
	
	private String detail;
	
	private Date createDate;

	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getKdid() {
		return kdid;
	}

	public void setKdid(long kdid) {
		this.kdid = kdid;
	}

	@Lob
	@Column(nullable=false)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(nullable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	private Keynote keynote;

	@OneToOne(cascade=CascadeType.ALL)
	public Keynote getKeynote() {
		return keynote;
	}

	public void setKeynote(Keynote keynote) {
		this.keynote = keynote;
	}
	
}
