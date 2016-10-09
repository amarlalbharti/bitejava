package com.bharti.dao;

import com.bharti.domain.NewsLater;

public interface NewsLaterDao
{
	public long addNewsLater(NewsLater newsLater);
	
	public boolean updateNewsLater(NewsLater newsLater);
	
	public NewsLater getNewsLater(String email);
	
}
