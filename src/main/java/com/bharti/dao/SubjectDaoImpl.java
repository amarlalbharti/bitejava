package com.bharti.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Keynote;
import com.bharti.domain.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao
{
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public long addSubject(Subject subject)
	{
		this.sessionFactory.getCurrentSession().save(subject);
		this.sessionFactory.getCurrentSession().flush();
		return subject.getSid();
	}
	
	@Override
	public boolean updateSubject(Subject subject)
	{
		try 
		{
			this.sessionFactory.getCurrentSession().update(subject);
			this.sessionFactory.getCurrentSession().flush();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	@Override
	public Subject getSubjectById(long sid)
	{
		return (Subject)this.sessionFactory.getCurrentSession().get(Subject.class, sid);
	}
	
	@SuppressWarnings("unchecked")
	public Subject getSubjectById(String url)
	{
		List<Subject> list = this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.add(Restrictions.eq("url", url))
				.setMaxResults(1)
				.list();
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsList(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.isNotNull("publishDate"))
				.addOrder(Order.asc("displayOrder"))
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsList(int first, int max, String userid)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.createAlias("loginInfo", "logAlias")
				.add(Restrictions.eq("logAlias.userid", userid))
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.isNotNull("publishDate"))
				.addOrder(Order.asc("displayOrder"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAllSubjectsList(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.add(Restrictions.isNull("deleteDate"))
				.addOrder(Order.asc("displayOrder"))
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAllSubjectsList(int first, int max, String userid){
		return this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.createAlias("loginInfo", "logAlias")
				.add(Restrictions.eq("logAlias.userid", userid))
				.add(Restrictions.isNull("deleteDate"))
				.addOrder(Order.asc("displayOrder"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}
	
	
	@Override
	public long countSubjects()
	{
		return (Long)this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.add(Restrictions.isNull("deleteDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	@Override
	public long countSubjects(String userid)
	{
		return (Long)this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.createAlias("loginInfo", "logAlias")
				.add(Restrictions.eq("logAlias.userid", userid))
				 .add(Restrictions.isNull("deleteDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Subject> getSubjectsListForHomePage()
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.add(Restrictions.eq("showOnHomePage", true))
				.add(Restrictions.isNotNull("publishDate"))
				.addOrder(Order.asc("displayOrder"))
				.add(Restrictions.isNull("deleteDate"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Subject> getRecentSubjects(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Subject.class)
				.addOrder(Order.desc("createDate"))
				.add(Restrictions.isNull("deleteDate"))
				.list();
	}
}
