package com.bharti.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Answers;

@Repository
public class AnswersDaoImpl implements AnswersDao{

	@Autowired private SessionFactory sessionFactory; 
	
	@Override
	public long addAnswer(Answers answers) {
		this.sessionFactory.getCurrentSession().save(answers);
		this.sessionFactory.getCurrentSession().flush();
		return answers.getAnsId();
	}

	@Override
	public boolean updateAnswer(Answers answers) {
		try 
		{
			this.sessionFactory.getCurrentSession().update(answers);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Answers getAnswerById(long ansId) {
		return (Answers)this.sessionFactory.getCurrentSession().get(Answers.class, ansId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answers> getAnswerByQid(long qid) 
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Answers.class)
				.add(Restrictions.eq("question.qid", qid))
				.list();
	}

}
