package com.bharti.service;

import java.util.List;

import com.bharti.domain.Subject;

public interface SubjectService 
{
	public long addSubject(Subject subject);
	
	public boolean updateSubject(Subject subject);
	
	public Subject getSubjectById(long sid);
	
	public Subject getSubjectById(String url);
	
	public List<Subject> getSubjectsList(int first, int max);
	
	public List<Subject> getSubjectsList(int first, int max, String userid);
	
	public List<Subject> getAllSubjectsList(int first, int max);

	public List<Subject> getAllSubjectsList(int first, int max, String userid);
	
	public long countSubjects();
	
	public long countSubjects(String userid);
	
	public List<Subject> getSubjectsListForHomePage();
	
	public List<Subject> getRecentSubjects(int first, int max);
}
