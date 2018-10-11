package com.bharti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.CommentsDao;
import com.bharti.domain.Comments;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

	@Autowired private CommentsDao commentsDao;  
	
	@Override
	public Long addComment(Comments comment) {
		return this.commentsDao.addComment(comment);
	}

	@Override
	public boolean updateComment(Comments comment) {
		return this.commentsDao.updateComment(comment);
	}

	@Override
	public Comments getComment(Long commentId) {
		return this.commentsDao.getComment(commentId);
	}

	@Override
	public List<Comments> getCommentByKeynote(Long kid) {
		return this.commentsDao.getCommentByKeynote(kid);
	}
	
	@Override
	public List<Comments> getCommentByUserid(String userid, int first, int max){
		return this.commentsDao.getCommentByUserid(userid, first, max);
	}

}
