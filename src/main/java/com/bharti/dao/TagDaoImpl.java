package com.bharti.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Tag;

@Repository
public class TagDaoImpl implements TagDao
{
	@Autowired private SessionFactory sessionFactory; 

	@Override
	public long addTag(Tag tag) {
		this.sessionFactory.getCurrentSession().save(tag);
		this.sessionFactory.getCurrentSession().flush();
		return tag.getTid();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tag getTag(String tag)
	{
		List<Tag> list = this.sessionFactory.getCurrentSession().createCriteria(Tag.class)
				.add(Restrictions.eq("tag", tag))
				.list();
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> searchTag(String tag) 
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Tag.class)
				.add(Restrictions.like("tag", "%"+tag+"%"))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getTagList(Set<String> tags) 
	{
		if(tags != null && !tags.isEmpty())
		{
			return this.sessionFactory.getCurrentSession().createCriteria(Tag.class)
					.add(Restrictions.in("tag", tags))
					.list();
		}
		return new ArrayList<Tag>();
	}

	@SuppressWarnings("unchecked")
	public List<Tag> getMostCommonTags(int max)
	{
		String sql = "select t.* from tag t LEFT JOIN tag_question tq ON t.tid = tq.tid GROUP BY t.tid HAVING COUNT(tq.tid) > 0 ORDER BY COUNT(tq.tid) DESC limit "+ max;
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Tag.class).list();
		
	}
	
}
