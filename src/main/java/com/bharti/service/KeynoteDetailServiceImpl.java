package com.bharti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.KeynoteDetailDao;
import com.bharti.domain.KeynoteDetail;

@Service
@Transactional
public class KeynoteDetailServiceImpl implements KeynoteDetailService
{
	@Autowired private KeynoteDetailDao keynoteDetailDao;
	
	public long addKeynoteDetail(KeynoteDetail keynoteDetail)
	{
		return this.keynoteDetailDao.addKeynoteDetail(keynoteDetail);
	}
	
	public boolean updateKeynoteDetail(KeynoteDetail keynoteDetail)
	{
		return this.keynoteDetailDao.updateKeynoteDetail(keynoteDetail);
	}
	
	public KeynoteDetail getKeynoteDetailByKnid(long knid)
	{
		return this.keynoteDetailDao.getKeynoteDetailByKnid(knid);
	}
	
	public KeynoteDetail getKeynoteDetailByKid(long kid)
	{
		return this.keynoteDetailDao.getKeynoteDetailByKid(kid);
	}
	
}
