package com.bharti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.SubjectDao;
import com.bharti.domain.Subject;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService
{
	@Autowired private SubjectDao subjectDao;
	
	@Override
	public long addSubject(Subject subject)
	{
		return this.subjectDao.addSubject(subject);
	}
	
	@Override
	public boolean updateSubject(Subject subject)
	{
		return this.subjectDao.updateSubject(subject);
	}
	
	@Override
	public Subject getSubjectById(long sid)
	{
		return this.subjectDao.getSubjectById(sid);
	}

	@Override
	public Subject getSubjectById(String url)
	{
		return this.subjectDao.getSubjectById(url);
	}
	
	@Override
	public List<Subject> getSubjectsList(int first, int max)
	{
		return this.subjectDao.getSubjectsList(first, max);
	}
	
	@Override
	public List<Subject> getSubjectsList(int first, int max, String userid)
	{
		return this.subjectDao.getSubjectsList(first, max, userid);
	}
	
	@Override
	public List<Subject> getAllSubjectsList(int first, int max)
	{
		return this.subjectDao.getAllSubjectsList(first, max);
	}
	
	public List<Subject> getAllSubjectsList(int first, int max, String userid){
		return this.subjectDao.getAllSubjectsList(first, max, userid);
	}
	
	@Override
	public long countSubjects()
	{
		return this.subjectDao.countSubjects();
	}
	
	@Override
	public long countSubjects(String userid)
	{
		return this.subjectDao.countSubjects(userid);
	}
	
	@Override
	public List<Subject> getSubjectsListForHomePage()
	{
		return this.subjectDao.getSubjectsListForHomePage();
	}
	
	@Override
	public List<Subject> getRecentSubjects(int first, int max)
	{
		return this.subjectDao.getRecentSubjects(first, max);
	}
}
