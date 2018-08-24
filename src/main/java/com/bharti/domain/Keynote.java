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


@Entity
@Table(name="keynotes")
public class Keynote
{
	private long kid;
	
	private String keynote;
	
	private String url;
	
	private int displayOrder;
	
	private boolean showOnHomePage;
	
	private Date publishDate;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private Date deleteDate;

	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getKid() {
		return kid;
	}

	public void setKid(long kid) {
		this.kid = kid;
	}

	@Column(nullable=false)
	public String getKeynote() {
		return keynote;
	}

	public void setKeynote(String keynote) {
		this.keynote = keynote;
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
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	private Subject subject;

	@ManyToOne  
    @JoinColumn(name = "subject_id")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
	private KeynoteDetail KeynoteDetail;
	
	@OneToOne(mappedBy="keynote", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	public KeynoteDetail getKeynoteDetail() {
		return KeynoteDetail;
	}

	public void setKeynoteDetail(KeynoteDetail keynoteDetail) {
		KeynoteDetail = keynoteDetail;
	}

	private Keynote parent_keynote;
	
	private Set<Keynote> childKeynote = new HashSet<Keynote>();

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent_id")
	public Keynote getParent_keynote() {
		return parent_keynote;
	}

	public void setParent_keynote(Keynote parent_keynote) {
		this.parent_keynote = parent_keynote;
	}

	@OneToMany(mappedBy="parent_keynote")
	public Set<Keynote> getChildKeynote() {
		return childKeynote;
	}

	public void setChildKeynote(Set<Keynote> childKeynote) {
		this.childKeynote = childKeynote;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
            return false;
        if (!(obj instanceof Keynote))
            return false;
        Keynote u = (Keynote) obj;
        return this.kid == 0 ? false : this.kid == u.getKid();
	}
	
	@Override
	public int hashCode() {
		return (int)this.kid;
	}
	
	@Override
	public String toString() {
		return String.valueOf(kid);
	}
}
