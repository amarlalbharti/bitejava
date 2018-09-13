package com.bharti.service;

import com.bharti.domain.SeoKeynote;

public interface SeoKeynoteService {
	
	public long addSeoKeynote(SeoKeynote seoKeynote);
	
	public boolean updateSeoKeynote(SeoKeynote seoKeynote);
	
	public SeoKeynote getSeoKeynote(long seoId);
}
