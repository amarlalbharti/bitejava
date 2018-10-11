package com.bharti.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.domain.Comments;

@Repository
public class CommentsDaoImpl implements CommentsDao{
	
	@Autowired private SessionFactory sessionFactory;
	
	public Long addComment(Comments comment) {
		this.sessionFactory.getCurrentSession().save(comment);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		return comment.getCommentId();
	}
	
	public boolean updateComment(Comments comment) {
		this.sessionFactory.getCurrentSession().update(comment);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		return true;
	}
	
	public Comments getComment(Long commentId) {
		return (Comments) this.sessionFactory.getCurrentSession().get(Comments.class, commentId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Comments> getCommentByKeynote(Long kid){
		return this.sessionFactory.getCurrentSession().createCriteria(Comments.class)
				.createAlias("keynote", "knAlias")
				.add(Restrictions.eq("knAlias.kid", kid))
				.add(Restrictions.isNull("deleteDate"))
				.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Comments> getCommentByUserid(String userid, int first, int max){
		return this.sessionFactory.getCurrentSession().createCriteria(Comments.class)
				.add(Restrictions.eq("createBy", userid))
				.add(Restrictions.isNull("deleteDate"))
				.addOrder(Order.desc("createDate"))
				.list();
	}
}
