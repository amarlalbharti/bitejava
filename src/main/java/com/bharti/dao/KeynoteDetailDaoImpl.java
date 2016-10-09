package com.bharti.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.KeynoteDetail;

@Repository
public class KeynoteDetailDaoImpl implements KeynoteDetailDao{

	@Autowired private SessionFactory sessionFactory;
	
	public long addKeynoteDetail(KeynoteDetail keynoteDetail)
	{
		this.sessionFactory.getCurrentSession().save(keynoteDetail);
		this.sessionFactory.getCurrentSession().flush();
		return keynoteDetail.getKdid();
	}
	
	public boolean updateKeynoteDetail(KeynoteDetail keynoteDetail)
	{
		try 
		{
			this.sessionFactory.getCurrentSession().update(keynoteDetail);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public KeynoteDetail getKeynoteDetailByKnid(long knid)
	{
		return (KeynoteDetail) this.sessionFactory.getCurrentSession().get(KeynoteDetail.class, knid);
	}
	
	@SuppressWarnings("unchecked")
	public KeynoteDetail getKeynoteDetailByKid(long kid)
	{
		List<KeynoteDetail> list = this.sessionFactory.getCurrentSession().createCriteria(KeynoteDetail.class)
				.add(Restrictions.eq("keynote.kid", kid))
				.list();
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}

}
