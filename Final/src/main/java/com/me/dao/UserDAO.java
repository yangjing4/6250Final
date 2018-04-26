package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.User;
import com.me.exception.BlogException;
import com.me.exception.CategoryException;
import com.me.exception.UserException;
import com.me.pojo.Blog;
import com.me.pojo.Category;
import com.me.pojo.Email;
import com.me.pojo.Person;



public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String useremail, String password) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where email.emailAddress = :useremail and password = :password");
			q.setString("useremail", useremail);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + useremail, e);
		}
	}
	
	public Email get(String useremail){
		try {
			begin();
			Query q = getSession().createQuery("from Email where emailAddress = :useremail");
			q.setString("useremail", useremail);
			Email email = (Email) q.uniqueResult();
			commit();
			return email;
		}catch(Exception e){
			rollback();
			System.out.println(e.getMessage());
		}
			return null;
		
	}
	
	public User get(long userId){
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
			q.setLong("personID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e);
		}
		return null;
	}
	

	public User register(User u) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			
			Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(), u.getPassword(),u.getType());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setType(u.getType());
			
			
			user.setEmail(email);
			email.setUser(user);
			getSession().save(user);
			
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	public boolean updateUser(String email) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where email.emailAddress = :useremail");
			q.setString("useremail", email);
			User user = (User) q.uniqueResult();
			if(user!=null){
				user.setStatus(1);
				getSession().update(user);
				commit();
				return true;
			}else{
				return false;
			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	
	}
	
		public List<User> list() throws UserException{
		    	
		    	try {
		            begin();
		            Query q = getSession().createQuery("from User");
		            List<User> users = q.list();
		            commit();
		            return users;
		        } catch (HibernateException e) {
		            rollback();
		            throw new UserException("Could not delete user", e);
		        }
		    	
		    }
		public void delete(User user) throws CategoryException {
	        try {
	            begin();
//	            Query q = getSession().createQuery("from Person where personID = :userId");
//	            q.setLong("userId", user.getPersonID());
//	            Person person = (Person) q.uniqueResult();
//	            Email email= user.getEmail();
//	            getSession().delete(email);
//	            getSession().delete(person);
	            getSession().delete(user);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new CategoryException("Could not delete the user", e);
	        }
	    }
		
		public boolean update(long userId,String username,String firstName,String lastName,String password,String email) throws Exception {
			try {
				begin();
				Query q = getSession().createQuery("from User where personID = :userId");
				q.setLong("userId", userId);
			    User user = (User) q.uniqueResult();
				if(user!=null){
					Email email1 = user.getEmail();
					
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setUsername(username);
					user.setPassword(password);
					email1.setEmailAddress(email);
					user.setEmail(email1);
					email1.setUser(user); 
					
					getSession().update(user);
					commit();
					return true;
				}else{
					return false;
				}
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Exception while creating user: " + e.getMessage());
			}
		
		}
		
	


}