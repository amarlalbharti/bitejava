package com.bharti.dao;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Keynote;

@Repository
public class KeynoteDaoImpl implements KeynoteDao
{
	@Autowired private SessionFactory sessionFactory;
	
	public long addKeynote(Keynote keynote)
	{
		this.sessionFactory.getCurrentSession().save(keynote);
		this.sessionFactory.getCurrentSession().flush();
		return keynote.getKid();
	}
	
	public boolean updateKeynote(Keynote keynote)
	{
		try 
		{
			this.sessionFactory.getCurrentSession().update(keynote);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Keynote getKeynoteById(long kid)
	{
		return (Keynote)this.sessionFactory.getCurrentSession().get(Keynote.class, kid);
	}
	
	@SuppressWarnings("unchecked")
	public Keynote getKeynoteByUrl(String url)
	{
		List<Keynote> list = this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.add(Restrictions.eq("url", url))
				.setMaxResults(1)
				.list();
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getKeynoteList(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
			.add(Restrictions.isNull("deleteDate"))
			.addOrder(Order.asc("displayOrder"))
			.setFirstResult(first)
			.setMaxResults(max)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getKeynoteList(long sid)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.createAlias("subject", "subAlias")
				.add(Restrictions.eq("subAlias.sid", sid))
				.add(Restrictions.isNull("parent_keynote"))
				.addOrder(Order.asc("displayOrder"))
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.isNotNull("publishDate"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getKeynoteListByUrl(String sub_url)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.createAlias("subject", "subAlias")
				.add(Restrictions.eq("subAlias.url", sub_url))
				.add(Restrictions.isNull("parent_keynote"))
				.addOrder(Order.asc("displayOrder"))
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.isNotNull("publishDate"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getAllKeynoteList(long sid)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.createAlias("subject", "subAlias")
				.add(Restrictions.eq("subAlias.sid", sid))
				.add(Restrictions.isNull("parent_keynote"))
				.addOrder(Order.asc("displayOrder"))
				.add(Restrictions.isNull("deleteDate"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getAllKeynoteList(long sid, long parent_kid)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.createAlias("subject", "subAlias")
				.add(Restrictions.eq("subAlias.sid", sid))
				.add(Restrictions.eq("parent_keynote.kid", parent_kid))
				.addOrder(Order.asc("displayOrder"))
				.add(Restrictions.isNull("deleteDate"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public Keynote getKeynoteWithChildByUrl(String url)
	{
		List<Keynote> list = this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.add(Restrictions.eq("url", url))
//				.setFetchMode("childKeynote", FetchMode.JOIN)
				.list();
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getChildKeynoteList(long parent_kid)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.add(Restrictions.eq("parent_keynote.kid", parent_kid))
				.addOrder(Order.asc("displayOrder"))
				.add(Restrictions.isNotNull("publishDate"))
				.add(Restrictions.isNull("deleteDate"))
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Keynote> getKeynotesForHomePage(long sid)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.createAlias("subject", "subAlias")
				.add(Restrictions.eq("subAlias.sid", sid))
				.add(Restrictions.eq("showOnHomePage", true))
				.add(Restrictions.isNotNull("publishDate"))
				.addOrder(Order.asc("displayOrder"))
				.add(Restrictions.isNull("deleteDate"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getRecentKeynotes(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.addOrder(Order.asc("createDate"))
				.add(Restrictions.isNull("deleteDate"))
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Keynote> getRecentPublishedKeynotes(int first, int max){
		return this.sessionFactory.getCurrentSession().createCriteria(Keynote.class)
				.addOrder(Order.desc("publishDate"))
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.eq("showOnHomePage", true))
				.add(Restrictions.isNotNull("publishDate"))
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}
	
}
