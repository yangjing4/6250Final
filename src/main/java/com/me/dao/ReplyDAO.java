package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.CommentException;
import com.me.exception.ReplyException;
import com.me.pojo.Comment;
import com.me.pojo.Reply;

public class ReplyDAO extends DAO{
	
	 public Reply create(Reply reply)
	            throws ReplyException {
	        try {
	            begin();            
	            getSession().save(reply);     
	            commit();
	            return reply;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create advert", e);
	            throw new ReplyException("Exception while creating reply: " + e.getMessage());
	        }
	    }
	 
	 public List<Reply> listByComment(int id) throws ReplyException{
	    	
	    	try {
	            begin();
	            Query q = getSession().createQuery("from Reply where comment.commentID=:id");
	            q.setInteger("id", id);
	            List<Reply> replys = q.list();
	            commit();
	            return replys;
	        } catch (HibernateException e) {
	            rollback();
	            throw new ReplyException("Could not list eplys", e);
	        }
	    	
	    }

}
