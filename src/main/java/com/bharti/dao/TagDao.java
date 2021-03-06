package com.bharti.dao;

import java.util.List;
import java.util.Set;

import com.bharti.domain.Tag;

public interface TagDao 
{
	public long addTag(Tag tag);
	
	public Tag getTag(String tag);
	
	public List<Tag> searchTag(String tag);
	
	public List<Tag> getTagList(Set<String> tags);
	
	public List<Tag> getMostCommonTags(int max);
}
