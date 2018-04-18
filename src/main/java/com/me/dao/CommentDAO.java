package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.BlogException;
import com.me.exception.CommentException;
import com.me.pojo.Blog;
import com.me.pojo.Comment;
import com.me.pojo.User;

public class CommentDAO extends DAO{
	
	 public Comment create(Comment comment)
	            throws CommentException {
	        try {
	            begin();            
	            getSession().save(comment);     
	            commit();
	            return comment;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create advert", e);
	            throw new CommentException("Exception while creating comment: " + e.getMessage());
	        }
	    }
	 
	 public List<Comment> listByBlog(int id) throws CommentException{
	    	
	    	try {
	            begin();
	            Query q = getSession().createQuery("from Comment where blog.id=:id");
	            q.setInteger("id", id);
	            List<Comment> comments = q.list();
	            commit();
	            return comments;
	        } catch (HibernateException e) {
	            rollback();
	            throw new CommentException("Could not list comments", e);
	        }
	    	
	    }
	 
	 
	 public Comment get(long commentId){
			try {
				begin();
				Query q = getSession().createQuery("from Comment where commentID= :commentId");
				q.setLong("commentId", commentId);		
				Comment com = (Comment) q.uniqueResult();
				commit();
				return com;
			} catch (HibernateException e) {
				rollback();
				System.out.println(e);
			}
			return null;
		}

}
