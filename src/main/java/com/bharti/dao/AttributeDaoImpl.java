package com.bharti.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Attribute;

@Repository
public class AttributeDaoImpl implements AttributeDao{

	@Autowired private SessionFactory sessionFactory; 
	
	@Override
	public Attribute addAttribute(Attribute attribute) {
		this.sessionFactory.getCurrentSession().save(attribute);
		this.sessionFactory.getCurrentSession().flush();
		return attribute;
	}

	@Override
	public boolean updateAttribute(Attribute attribute) {
		this.sessionFactory.getCurrentSession().update(attribute);
		this.sessionFactory.getCurrentSession().flush();
		return true;
	}

	@Override
	public Attribute getAttribute(Integer attributeId) {
		return (Attribute)this.sessionFactory.getCurrentSession().get(Attribute.class, attributeId);
	}

	@SuppressWarnings("unchecked")
	public Attribute getAttribute(String attributeName) {
		List<Attribute> list = this.sessionFactory.getCurrentSession()
				.createCriteria(Attribute.class)
				.add(Restrictions.eq("attributeName", attributeName))
				.add(Restrictions.eq("active", Boolean.TRUE))
				.list();
		if(!list.isEmpty() ) {
			return list.get(0);
		}
		
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Attribute getAttribute(String attributeName, String attributeValue) {
		List<Attribute> list = this.sessionFactory.getCurrentSession()
				.createCriteria(Attribute.class)
				.add(Restrictions.eq("attributeName", attributeName))
				.add(Restrictions.eq("attributeValue", attributeValue))
				.add(Restrictions.eq("active", Boolean.TRUE))
				.list();
		if(!list.isEmpty() ) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public boolean deleteAttribute(Integer attributeId) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
