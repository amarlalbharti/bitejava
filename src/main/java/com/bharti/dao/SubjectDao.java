package com.bharti.dao;

import java.util.List;

import com.bharti.domain.Subject;

public interface SubjectDao
{
	public long addSubject(Subject subject);
	
	public boolean updateSubject(Subject subject);
	
	public Subject getSubjectById(long sid);
	
	public Subject getSubjectById(String url);
	
	public List<Subject> getSubjectsList(int first, int max);
	
	public List<Subject> getAllSubjectsList(int first, int max);
	
	public long countSubjects();
	
	public List<Subject> getSubjectsListForHomePage();

	public List<Subject> getRecentSubjects(int first, int max);

}
