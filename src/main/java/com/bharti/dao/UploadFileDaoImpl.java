package com.bharti.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Subject;
import com.bharti.domain.UploadFile;

@Repository
public class UploadFileDaoImpl implements UploadFileDao{
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public long addUploadFile(UploadFile uploadFile)
	{
		this.sessionFactory.getCurrentSession().save(uploadFile);
		this.sessionFactory.getCurrentSession().flush();
		return uploadFile.getFid();
	}
	
	@Override
	public boolean updateUploadFile(UploadFile uploadFile)
	{
		try 
		{
			
			this.sessionFactory.getCurrentSession().update(uploadFile);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public UploadFile getUploadFile(long fid)
	{
		return (UploadFile)this.sessionFactory.getCurrentSession().get(UploadFile.class, fid);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UploadFile> getRecentUploadFiles(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(UploadFile.class)
				.add(Restrictions.isNull("deleteDate"))
				.addOrder(Order.desc("createDate"))
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}
	
	public long countUploadedFiles()
	{
		long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(UploadFile.class)
				.add(Restrictions.isNull("deleteDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.rowCount())
				.uniqueResult();
		return count;
	}
	
}
