package com.bharti.dao;

import com.bharti.domain.Attribute;

public interface AttributeDao {
	
	public Attribute addAttribute(Attribute attribute);
	
	public boolean updateAttribute(Attribute attribute);
	
	public Attribute getAttribute(Integer attributeId);
	
	public Attribute getAttribute(String attributeName);
	
	public Attribute getAttribute(String attributeName, String attributeValue);
	
	public boolean deleteAttribute(Integer attributeId);
	
	
}
