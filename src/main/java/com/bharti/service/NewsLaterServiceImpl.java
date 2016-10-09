package com.bharti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.NewsLaterDao;
import com.bharti.domain.NewsLater;

@Service
@Transactional
public class NewsLaterServiceImpl implements NewsLaterService
{
	@Autowired private NewsLaterDao newsLaterDao;
	
	@Override
	public long addNewsLater(NewsLater newsLater)
	{
		return newsLaterDao.addNewsLater(newsLater);
	}
	
	@Override
	public boolean updateNewsLater(NewsLater newsLater)
	{
		return newsLaterDao.updateNewsLater(newsLater);
	}
	
	@Override
	public NewsLater getNewsLater(String email)
	{
		return newsLaterDao.getNewsLater(email);
	}

	
}
