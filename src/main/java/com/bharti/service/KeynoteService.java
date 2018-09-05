package com.bharti.service;

import java.util.List;

import com.bharti.domain.Keynote;

public interface KeynoteService 
{
	public long addKeynote(Keynote keynote);
	
	public boolean updateKeynote(Keynote keynote);
	
	public Keynote getKeynoteById(long kid);
	
	public Keynote getKeynoteByUrl(String url);
	
	public List<Keynote> getKeynoteList(int first, int max);
	
	public List<Keynote> getKeynoteList(long sid);
	
	public List<Keynote> getKeynoteListByUrl(String sub_url);
	
	public List<Keynote> getAllKeynoteList(long sid);
	
	public List<Keynote> getAllKeynoteList(long sid, long parent_kid);
	
	public Keynote getKeynoteWithChildByUrl(String url);
	
	public List<Keynote> getChildKeynoteList(long parent_kid);
	
	public List<Keynote> getKeynotesForHomePage(long sid);
	
	public List<Keynote> getRecentKeynotes(int first, int max);
	
	public List<Keynote> getRecentPublishedKeynotes(int first, int max);
}
