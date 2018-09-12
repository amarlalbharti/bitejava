package com.bharti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.SeoKeynoteDao;
import com.bharti.domain.SeoKeynote;

@Service
@Transactional
public class SeoKeynoteServiceImpl implements SeoKeynoteService{
	
	@Autowired private SeoKeynoteDao seoKeynoteDao;
	
	public long addSeoKeynote(SeoKeynote seoKeynote) {
		return this.seoKeynoteDao.addSeoKeynote(seoKeynote);
	}
	
	public boolean updateSeoKeynote(SeoKeynote seoKeynote) {
		return this.seoKeynoteDao.updateSeoKeynote(seoKeynote);
	}
	
}
