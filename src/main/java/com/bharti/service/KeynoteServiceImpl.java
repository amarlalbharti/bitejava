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
	
	@Override
	public long addKeynote(Keynote keynote)
	{
		return this.keynoteDao.addKeynote(keynote);
	}
	
	@Override
	public boolean updateKeynote(Keynote keynote)
	{
		return this.keynoteDao.updateKeynote(keynote);
	}
	
	@Override
	public Keynote getKeynoteById(long kid)
	{
		return this.keynoteDao.getKeynoteById(kid);
	}
	
	@Override
	public Keynote getKeynoteByUrl(String url)
	{
		return this.keynoteDao.getKeynoteByUrl(url);
	}
	
	@Override
	public List<Keynote> getKeynoteList(int first, int max)
	{
		return this.keynoteDao.getKeynoteList(first, max);
	}
	
	@Override
	public List<Keynote> getKeynoteList(long sid)
	{
		return this.keynoteDao.getKeynoteList(sid);
	}

	@Override
	public List<Keynote> getKeynoteListByUrl(String sub_url)
	{
		return this.keynoteDao.getKeynoteListByUrl(sub_url);
	}
	
	@Override
	public List<Keynote> getAllKeynoteList(long sid)
	{
		return this.keynoteDao.getAllKeynoteList(sid);
	}
	
	@Override
	public List<Keynote> getAllKeynoteList(long sid, String userid)
	{
		return this.keynoteDao.getAllKeynoteList(sid, userid);
	}
	
	@Override
	public List<Keynote> getAllKeynoteList(long sid, long parent_kid)
	{
		return this.keynoteDao.getAllKeynoteList(sid, parent_kid);
	}
	
	@Override
	public List<Keynote> getAllKeynoteList(long sid, long parent_kid, String userid)
	{
		return this.keynoteDao.getAllKeynoteList(sid, parent_kid, userid);
	}
	
	@Override
	public Keynote getKeynoteWithChildByUrl(String url)
	{
		return this.keynoteDao.getKeynoteWithChildByUrl(url);
	}
	
	@Override
	public List<Keynote> getChildKeynoteList(long parent_kid)
	{
		return this.keynoteDao.getChildKeynoteList(parent_kid);
	}
	
	@Override
	public List<Keynote> getKeynotesForHomePage(long sid)
	{
		return this.keynoteDao.getKeynotesForHomePage(sid);
	}
	
	@Override
	public List<Keynote> getRecentKeynotes(int first, int max)
	{
		return this.keynoteDao.getRecentKeynotes(first, max);
	}
	
	@Override
	public List<Keynote> getRecentPublishedKeynotes(int first, int max){
		return this.keynoteDao.getRecentPublishedKeynotes(first, max);
	}
	
	@Override
	public List<Keynote> getRecentKeynotesForSeo(int first, int max){
		return this.keynoteDao.getRecentKeynotesForSeo(first, max);
	}
	
	public Long countKeynote() {
		return this.keynoteDao.countKeynote();
	}
	
	public Long countKeynote(long sid) {
		return this.keynoteDao.countKeynote(sid);
	}
	
}
