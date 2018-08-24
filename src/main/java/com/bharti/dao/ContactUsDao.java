package com.bharti.dao;

import com.bharti.domain.ContactUs;
import com.bharti.exception.TrasactionException;

public interface ContactUsDao {
	public int add(ContactUs contectUs)  throws TrasactionException;
	
	public void update(ContactUs contectUs)  throws TrasactionException;
	
	public ContactUs get(int contectUsId)  throws TrasactionException;
	
	
}
