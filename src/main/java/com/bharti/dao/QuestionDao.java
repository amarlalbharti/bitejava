package com.bharti.dao;

import java.util.List;

import com.bharti.domain.Question;

public interface QuestionDao 
{
	public long addQuestion(Question question);
	
	public boolean updateQuestion(Question question);
	
	public Question getQuestionById(long qid);
	
	public Question getQuestionByUrl(String url);
	
	public List<Question> getRecentAdminQuestions(int first, int max);
	
	public List<Question> getAdminQuestionsByTag(long tid,int first, int max);
	
	public long countAdminQuestions();
	
	public long countAdminQuestionsByTag(long tid);
	
	public boolean increamentQuestionViewsByQid(long qid);
	
	public List<Question> getRecentQuestions(int first, int max);
	
	public List<Question> getFeaturedQuestions(int first, int max);
	
	public List<Question> getMostViewedQuestions(int first, int max);
	
	
	
}
