package com.bharti.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.TagDao;
import com.bharti.domain.Tag;

@Service
@Transactional
public class TagServiceImpl implements TagService{

	@Autowired private TagDao tagDao;
	
	@Override
	public long addTag(Tag tag) {
		return this.tagDao.addTag(tag);
	}

	@Override
	public Tag getTag(String tag)
	{
		return this.tagDao.getTag(tag);
	}
	
	@Override
	public List<Tag> searchTag(String tag) {
		return this.tagDao.searchTag(tag);
	}
	
	public List<Tag> getTagList(Set<String> tags)
	{
		return this.tagDao.getTagList(tags);
	}
	
	public List<Tag> getMostCommonTags(int max)
	{
		return this.tagDao.getMostCommonTags(max);
	}

}
