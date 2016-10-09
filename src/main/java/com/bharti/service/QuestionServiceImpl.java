package com.bharti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.QuestionDao;
import com.bharti.domain.Question;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

	@Autowired private QuestionDao questionDao;
	
	@Override
	public long addQuestion(Question question)
	{
		return this.questionDao.addQuestion(question);
	}
	
	@Override
	public boolean updateQuestion(Question question)
	{
		return this.questionDao.updateQuestion(question);
	}
	
	@Override
	public Question getQuestionById(long qid)
	{
		return this.questionDao.getQuestionById(qid);
	}
	
	@Override
	public Question getQuestionByUrl(String url)
	{
		return this.questionDao.getQuestionByUrl(url);
	}
	
	@Override
	public List<Question> getRecentAdminQuestions(int first, int max)
	{
		return this.questionDao.getRecentAdminQuestions(first, max);
	}
	
	@Override
	public long countAdminQuestions()
	{
		return this.questionDao.countAdminQuestions();
	}
	
	@Override
	public List<Question> getAdminQuestionsByTag(long tid,int first, int max)
	{
		return this.questionDao.getAdminQuestionsByTag(tid, first , max);
	}
	
	@Override
	public long countAdminQuestionsByTag(long tid)
	{
		return this.questionDao.countAdminQuestionsByTag(tid);
	}
	
	@Override
	public boolean increamentQuestionViewsByQid(long qid)
	{
		return this.questionDao.increamentQuestionViewsByQid(qid);
	}
	
	@Override
	public List<Question> getRecentQuestions(int first, int max)
	{
		return this.questionDao.getRecentQuestions(first, max);
	}
	
	@Override
	public List<Question> getFeaturedQuestions(int first, int max)
	{
		return this.questionDao.getFeaturedQuestions(first, max);
	}
	
	@Override
	public List<Question> getMostViewedQuestions(int first, int max)
	{
		return this.questionDao.getMostViewedQuestions(first, max);
	}
	
	
}
