package com.bharti.dao;

import java.util.List;

import com.bharti.domain.Answers;

public interface AnswersDao 
{
	public long addAnswer(Answers answers);
	
	public boolean updateAnswer(Answers answers);
	
	public Answers getAnswerById(long ansId);
	
	public List<Answers> getAnswerByQid(long qid);
	
	
}
