package com.bharti.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Question;
import com.bharti.domain.Subject;

@Repository
public class QuestionDaoImpl implements QuestionDao{

	@Autowired private SessionFactory sessionFactory;  
	
	@Override
	public long addQuestion(Question question) {
		this.sessionFactory.getCurrentSession().save(question);
		this.sessionFactory.getCurrentSession().flush();
		return question.getQid();
	}

	@Override
	public boolean updateQuestion(Question question) {
		try {
			this.sessionFactory.getCurrentSession().update(question);
			this.sessionFactory.getCurrentSession().flush();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Question getQuestionById(long qid) {
		
		List<Question> list = this.sessionFactory.getCurrentSession().createCriteria(Question.class)
				.add(Restrictions.eq("qid", qid))
//				.setFetchMode("tags", FetchMode.JOIN)
//				.setFetchMode("answers", FetchMode.JOIN)
//				.addOrder(Order.asc("qid"))
				
				.list();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Question getQuestionByUrl(String url) 
	{
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getRecentAdminQuestions(int first, int max) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
	    criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("qid")))));
	    criteria.add(Restrictions.eq("userType", "admin"));
	    criteria.addOrder(Order.desc("createDate"));
	    criteria.setFirstResult(first);
	    criteria.setMaxResults(max);
		List<Object[]> idList = criteria.list();
		
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0) {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
            criteria.add(Restrictions.in("qid", longList));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFetchMode("tags", FetchMode.JOIN);
            criteria.setFetchMode("answers", FetchMode.JOIN);
            criteria.addOrder(Order.desc("createDate"));
        } else {
            return new ArrayList<Question>();
        }
		return criteria.list();
	}
	
	@Override
	public List<Question> getAdminQuestionsByTag(long tid, int first, int max) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
	    criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("qid")))));
	    criteria.createAlias("tags", "tagAlias");
	    criteria.add(Restrictions.eq("tagAlias.tid", tid));
	    criteria.add(Restrictions.eq("userType", "admin"));

	    criteria.setFirstResult(first);
		criteria.setMaxResults(max);

		List<Object[]> idList = criteria.list();
		List<Long> longList = new ArrayList<Long>();
		for (Object[] long1 : idList) {
			Object[] record = long1;
			longList.add((Long) record[0]);
		}

		if (longList.size() > 0) {
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
			criteria.add(Restrictions.in("qid", longList));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setFetchMode("tags", FetchMode.JOIN);
			criteria.setFetchMode("answers", FetchMode.JOIN);
		} else {
			return new ArrayList<Question>();
		}
		return criteria.list();
	}
	
	@Override
	public long countAdminQuestions() {
		return (Long)this.sessionFactory.getCurrentSession().createCriteria(Question.class)
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.eq("userType", "admin"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	@Override
	public long countAdminQuestionsByTag(long tid) {
		return (Long)this.sessionFactory.getCurrentSession().createCriteria(Question.class)
				.createAlias("tags", "tagAlias")
			    .add(Restrictions.eq("tagAlias.tid", tid))
				.add(Restrictions.eq("userType", "admin"))
				.add(Restrictions.isNull("deleteDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	@Override
	public boolean increamentQuestionViewsByQid(long qid) {
		try {
			int st = this.sessionFactory.getCurrentSession().createSQLQuery("UPDATE question  SET views = views + 1  WHERE qid ="+qid+";").executeUpdate();
			if(st > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public List<Question> getRecentQuestions(int first, int max) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
	    criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("qid")))));
	    criteria.add(Restrictions.isNull("deleteDate"));
	    criteria.add(Restrictions.isNotNull("publishDate"));
	    criteria.setFirstResult(first);
	    criteria.setMaxResults(max);
		List<Object[]> idList = criteria.list();
		
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0) {
			criteria = this.sessionFactory.getCurrentSession()
					.createCriteria(Question.class);
			criteria.add(Restrictions.in("qid", longList));
			criteria.addOrder(Order.desc("publishDate"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setFetchMode("tags", FetchMode.JOIN);
			criteria.setFetchMode("answers", FetchMode.JOIN);

		} else {
			return new ArrayList<Question>();
		}

		return criteria.list();
				
	}
	
	public List<Question> getFeaturedQuestions(int first, int max) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
	    criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("qid")))));
	    criteria.add(Restrictions.isNull("deleteDate"));
	    criteria.add(Restrictions.isNotNull("publishDate"));
	    criteria.setFirstResult(first);
	    criteria.setMaxResults(max);

		List<Object[]> idList = criteria.list();
		
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }
		if (longList.size() > 0) {
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
			criteria.add(Restrictions.in("qid", longList));
			criteria.addOrder(Order.desc("publishDate"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setFetchMode("tags", FetchMode.JOIN);
			criteria.setFetchMode("answers", FetchMode.JOIN);
		} else {
			return new ArrayList<Question>();
		}

		return criteria.list();
					
	}
	
	public List<Question> getMostViewedQuestions(int first, int max)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
	    criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("qid")))));
	    criteria.add(Restrictions.isNull("deleteDate"));
	    criteria.add(Restrictions.isNotNull("publishDate"));
	    System.out.println("date : " + cal.getTime());
	    criteria.add(Restrictions.gt("createDate", cal.getTime()));
	    criteria.addOrder(Order.desc("views"));
	    criteria.setFirstResult(first);
	    criteria.setMaxResults(max);
		List<Object[]> idList = criteria.list();
		
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }
		if (longList.size() > 0) {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
            criteria.add(Restrictions.in("qid", longList));
            criteria.addOrder(Order.desc("views"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFetchMode("tags", FetchMode.JOIN);
            criteria.setFetchMode("answers", FetchMode.JOIN);
		} else {
			return new ArrayList<Question>();
		}
		return criteria.list();
	}
	
	
	
}
