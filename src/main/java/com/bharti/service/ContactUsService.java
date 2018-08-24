package com.bharti.service;

import com.bharti.domain.ContactUs;
import com.bharti.exception.TrasactionException;

public interface ContactUsService {
	public int add(ContactUs contectUs);
	
	public void update(ContactUs contectUs);
	
	public ContactUs get(int contectUsId);
	
	
}
