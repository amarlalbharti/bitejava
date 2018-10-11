package com.bharti.service;

import java.util.List;

import com.bharti.domain.Comments;

public interface CommentService {
	
	public Long addComment(Comments comment);
	
	public boolean updateComment(Comments comment);
	
	public Comments getComment(Long commentId);
	
	public List<Comments> getCommentByKeynote(Long kid);
	
	public List<Comments> getCommentByUserid(String userid, int first, int max);
}
