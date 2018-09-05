package com.bharti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.KeynoteDao;
import com.bharti.domain.Keynote;

@Service
@Transactional
public class KeynoteServiceImpl implements KeynoteService
{
	@Autowired private KeynoteDao keynoteDao;
	
	public long addKeynote(Keynote keynote)
	{
		return this.keynoteDao.addKeynote(keynote);
	}
	
	public boolean updateKeynote(Keynote keynote)
	{
		return this.keynoteDao.updateKeynote(keynote);
	}
	
	public Keynote getKeynoteById(long kid)
	{
		return this.keynoteDao.getKeynoteById(kid);
	}
	
	public Keynote getKeynoteByUrl(String url)
	{
		return this.keynoteDao.getKeynoteByUrl(url);
	}
	
	public List<Keynote> getKeynoteList(int first, int max)
	{
		return this.keynoteDao.getKeynoteList(first, max);
	}
	public List<Keynote> getKeynoteList(long sid)
	{
		return this.keynoteDao.getKeynoteList(sid);
	}

	public List<Keynote> getKeynoteListByUrl(String sub_url)
	{
		return this.keynoteDao.getKeynoteListByUrl(sub_url);
	}
	
	public List<Keynote> getAllKeynoteList(long sid)
	{
		return this.keynoteDao.getAllKeynoteList(sid);
	}
	
	public List<Keynote> getAllKeynoteList(long sid, long parent_kid)
	{
		return this.keynoteDao.getAllKeynoteList(sid, parent_kid);
	}
	
	public Keynote getKeynoteWithChildByUrl(String url)
	{
		return this.keynoteDao.getKeynoteWithChildByUrl(url);
	}
	
	public List<Keynote> getChildKeynoteList(long parent_kid)
	{
		return this.keynoteDao.getChildKeynoteList(parent_kid);
	}
	
	public List<Keynote> getKeynotesForHomePage(long sid)
	{
		return this.keynoteDao.getKeynotesForHomePage(sid);
	}
	
	public List<Keynote> getRecentKeynotes(int first, int max)
	{
		return this.keynoteDao.getRecentKeynotes(first, max);
	}
	
	public List<Keynote> getRecentPublishedKeynotes(int first, int max){
		return this.keynoteDao.getRecentPublishedKeynotes(first, max);
	}
}
