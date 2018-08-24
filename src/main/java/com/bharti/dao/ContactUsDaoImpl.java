package com.bharti.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.ContactUs;
import com.bharti.exception.TrasactionException;

@Repository
public class ContactUsDaoImpl implements ContactUsDao {
	
	@Autowired private SessionFactory sessionFactory;
	
	public int add(ContactUs contectUs) throws TrasactionException{
		this.sessionFactory.getCurrentSession().save(contectUs);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		return contectUs.getContactUsId();
	}
	
	public void update(ContactUs contectUs) throws TrasactionException{
		this.sessionFactory.getCurrentSession().save(contectUs);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
	}
	
	public ContactUs get(int contectUsId) throws TrasactionException{
		return (ContactUs)this.sessionFactory.getCurrentSession().get(ContactUs.class, contectUsId);
	}
	
	
}
