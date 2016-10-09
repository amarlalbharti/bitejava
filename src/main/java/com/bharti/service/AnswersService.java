package com.bharti.service;

import java.util.List;

import com.bharti.domain.Answers;

public interface AnswersService 
{
	public long addAnswer(Answers answers);
	
	public boolean updateAnswer(Answers answers);
	
	public Answers getAnswerById(long ansId);
	
	public List<Answers> getAnswerByQid(long qid);

}
