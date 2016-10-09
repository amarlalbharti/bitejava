package com.bharti.service;

import com.bharti.domain.KeynoteDetail;

public interface KeynoteDetailService {
	public long addKeynoteDetail(KeynoteDetail keynoteDetail);
	
	public boolean updateKeynoteDetail(KeynoteDetail keynoteDetail);
	
	public KeynoteDetail getKeynoteDetailByKnid(long knid);
	
	public KeynoteDetail getKeynoteDetailByKid(long kid);
	
}
