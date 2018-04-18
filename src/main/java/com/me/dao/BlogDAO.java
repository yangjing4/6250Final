package com.me.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.BlogException;
import com.me.exception.CategoryException;
import com.me.pojo.Blog;
import com.me.pojo.Category;
import com.me.pojo.User;

public class BlogDAO extends DAO {
	
	public Blog getbyID(int id){
		try {
			begin();
			Query q = getSession().createQuery("from Blog where id= :id");
			q.setLong("id", id);		
			Blog blog = (Blog) q.uniqueResult();
			commit();
			return blog;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e);
		}
		return null;
	}
	public Blog get(String title){
		try {
			begin();
			Query q = getSession().createQuery("from Blog where title= :title");
			q.setString("title", title);		
			Blog blog = (Blog) q.uniqueResult();
			commit();
			return blog;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e);
		}
		return null;
	}

    public Blog create(Blog blog)
            throws BlogException {
        try {
            begin();            
            getSession().save(blog);     
            commit();
            return blog;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advert", e);
            throw new BlogException("Exception while creating blog: " + e.getMessage());
        }
    }

    public void delete(Blog blog)
            throws BlogException {
        try {
            begin();
            getSession().delete(blog);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new BlogException("Could not delete blog", e);
        }
    }
    
    public void update(Blog blog) throws BlogException {
        try {
            begin();
            getSession().update(blog);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new BlogException("Could not save the blog", e);
        }
    }
    
    public List<Blog> list() throws BlogException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Blog ");
            List<Blog> blogs = q.list();
            commit();
            return blogs;
        } catch (HibernateException e) {
            rollback();
            throw new BlogException("Could not get blogs", e);
        }
    	
    }
    
    public List<Blog> listByUser(long userId) throws BlogException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Blog where user.personID=:userId");
            q.setLong("userId", userId);
            List<Blog> blogs = q.list();
            commit();
            return blogs;
        } catch (HibernateException e) {
            rollback();
            throw new BlogException("Could not delete blog", e);
        }
    	
    }
    
    
    
    
}