package com.bharti.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.SeoKeynote;

@Repository
public class SeoKeynoteDaoImpl implements SeoKeynoteDao{
	
	@Autowired private SessionFactory sessionFactory;
	
	public long addSeoKeynote(SeoKeynote seoKeynote) {
		this.sessionFactory.getCurrentSession().save(seoKeynote);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		return seoKeynote.getSeoId();
	}
	
	public boolean updateSeoKeynote(SeoKeynote seoKeynote) {
		this.sessionFactory.getCurrentSession().update(seoKeynote);
		return true;
	}
	
}
