package com.bharti.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.ContactUsDao;
import com.bharti.domain.ContactUs;
import com.bharti.exception.TrasactionException;

@Service
@Transactional
public class ContactUsServiceImpl implements ContactUsService{

	private static final Logger LOGGER = Logger.getLogger(ContactUsServiceImpl.class);
	@Autowired private ContactUsDao contactUsDao;
	
	public int add(ContactUs contectUs){
		try {
			return this.contactUsDao.add(contectUs);
		}catch (TrasactionException e) {
			LOGGER.error("Exception while adding contact us ", e);
		}
		return 0;
	}
	
	public void update(ContactUs contectUs){
		try {
			this.contactUsDao.update(contectUs);
		}catch (TrasactionException e) {
			LOGGER.error("Exception while updating contact us ", e);
		}
		
	}
	
	public ContactUs get(int contectUsId){
		try {
			return this.contactUsDao.get(contectUsId);
		}catch (TrasactionException e) {
			LOGGER.error("Exception while updating contact us ", e);
		}
		return null;
	}
	
	
}
