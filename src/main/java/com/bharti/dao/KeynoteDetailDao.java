package com.bharti.dao;

import com.bharti.domain.KeynoteDetail;

public interface KeynoteDetailDao
{
	public long addKeynoteDetail(KeynoteDetail keynoteDetail);
	
	public boolean updateKeynoteDetail(KeynoteDetail keynoteDetail);
	
	public KeynoteDetail getKeynoteDetailByKnid(long knid);
	
	public KeynoteDetail getKeynoteDetailByKid(long kid);
	
	
}
