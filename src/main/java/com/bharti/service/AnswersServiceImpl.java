package com.bharti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.AnswersDao;
import com.bharti.domain.Answers;

@Service
@Transactional
public  class AnswersServiceImpl implements AnswersService{

	@Autowired private AnswersDao answersDao;
	
	@Override
	public long addAnswer(Answers answers) {
		return this.answersDao.addAnswer(answers);
	}

	@Override
	public boolean updateAnswer(Answers answers) {
		return this.answersDao.updateAnswer(answers);
	}

	@Override
	public Answers getAnswerById(long ansId) {
		return this.answersDao.getAnswerById(ansId);
	}

	@Override
	public List<Answers> getAnswerByQid(long qid) {
		return this.answersDao.getAnswerByQid(qid);
	}

}
