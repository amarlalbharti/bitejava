package com.bharti.service;

import com.bharti.domain.NewsLater;

public interface NewsLaterService 
{
	public long addNewsLater(NewsLater newsLater);
	
	public boolean updateNewsLater(NewsLater newsLater);
	
	public NewsLater getNewsLater(String email);

}
