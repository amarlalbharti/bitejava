package com.bharti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.AttributeDao;
import com.bharti.domain.Attribute;

@Service
@Transactional
public class AttributeServiceImpl implements AttributeService{

	@Autowired private AttributeDao attributeDao;
	@Override
	public Attribute addAttribute(Attribute attribute) {
		return this.attributeDao.addAttribute(attribute);
	}

	@Override
	public boolean updateAttribute(Attribute attribute) {
		return this.attributeDao.updateAttribute(attribute);
	}

	@Override
	public Attribute getAttribute(Integer attributeId) {
		return this.attributeDao.getAttribute(attributeId);
	}

	@Override
	public Attribute getAttribute(String attributeName) {
		return this.attributeDao.getAttribute(attributeName);
	}
	
	@Override
	public Attribute getAttribute(String attributeName, String attributeValue) {
		return this.attributeDao.getAttribute(attributeName, attributeValue);
	}

	@Override
	public boolean deleteAttribute(Integer attributeId) {
		return this.attributeDao.deleteAttribute(attributeId);
	}
	
}
