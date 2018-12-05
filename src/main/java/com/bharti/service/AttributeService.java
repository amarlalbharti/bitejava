package com.bharti.service;

import com.bharti.domain.Attribute;

public interface AttributeService {
	
	public Attribute addAttribute(Attribute attribute);
	
	public boolean updateAttribute(Attribute attribute);
	
	public Attribute getAttribute(Integer attributeId);
	
	public Attribute getAttribute(String attributeName);
	
	public Attribute getAttribute(String attributeName, String attributeValue);
	
	public boolean deleteAttribute(Integer attributeId);
	
}
