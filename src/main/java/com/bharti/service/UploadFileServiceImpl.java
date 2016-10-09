package com.bharti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.UploadFileDao;
import com.bharti.domain.UploadFile;

@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService{
	
	@Autowired private UploadFileDao uploadFileDao;
	
	@Override
	public long addUploadFile(UploadFile uploadFile)
	{
		return this.uploadFileDao.addUploadFile(uploadFile);
	}
	
	@Override
	public boolean updateUploadFile(UploadFile uploadFile)
	{
		return this.uploadFileDao.updateUploadFile(uploadFile);
	}
	
	@Override
	public UploadFile getUploadFile(long fid)
	{
		return this.uploadFileDao.getUploadFile(fid);
	}
	
	@Override
	public List<UploadFile> getRecentUploadFiles(int first, int max)
	{
		return this.uploadFileDao.getRecentUploadFiles(first, max);
	}
	
	@Override
	public long countUploadedFiles()
	{
		return this.uploadFileDao.countUploadedFiles();
	}
}
