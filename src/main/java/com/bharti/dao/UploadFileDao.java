package com.bharti.dao;

import java.util.List;

import com.bharti.domain.UploadFile;

public interface UploadFileDao
{
	public long addUploadFile(UploadFile uploadFile);
	
	public boolean updateUploadFile(UploadFile uploadFile);
	
	public UploadFile getUploadFile(long fid);
	
	public List<UploadFile> getRecentUploadFiles(int first, int max);
	
	public long countUploadedFiles();
	
}
