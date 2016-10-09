package com.bharti.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.NewsLater;

@Repository
public class NewsLaterDaoImpl implements NewsLaterDao
{
	@Autowired private SessionFactory sessionFactory;
	
	public long addNewsLater(NewsLater newsLater)
	{
		this.sessionFactory.getCurrentSession().save(newsLater);
		this.sessionFactory.getCurrentSession().flush();
		return newsLater.getNid();
	}
	
	public boolean updateNewsLater(NewsLater newsLater)
	{
		try 
		{
			this.sessionFactory.getCurrentSession().update(newsLater);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public NewsLater getNewsLater(String email)
	{
		List<NewsLater> list = this.sessionFactory.getCurrentSession().createCriteria(NewsLater.class)
				.add(Restrictions.eq("email", email))
				.list();
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}

}
