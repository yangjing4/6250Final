package com.me.controller;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.me.dao.BlogDAO;
import com.me.dao.CategoryDAO;
import com.me.dao.CommentDAO;
import com.me.dao.ReplyDAO;
import com.me.dao.UserDAO;
import com.me.exception.BlogException;
import com.me.pojo.Blog;
import com.me.pojo.Category;
import com.me.pojo.Comment;
import com.me.pojo.Reply;
import com.me.pojo.User;

@Controller
@RequestMapping("/blog/*")
public class BlogController {
		
		@Autowired
		@Qualifier("blogDao")
		BlogDAO blogDao;
		
		@Autowired
		@Qualifier("categoryDao")
		CategoryDAO categoryDAO;
		
		@Autowired
		@Qualifier("userDao")
		UserDAO userDao;
		
		@Autowired
		@Qualifier("commentDao")
		CommentDAO commentDao;
		
		@Autowired
		@Qualifier("replyDao")
		ReplyDAO replyDao;
		

		@RequestMapping(value = "/blog/add", method = RequestMethod.POST)
		public ModelAndView addCategory(@ModelAttribute("blog") Blog blog, BindingResult result) throws Exception {

			try {			
				
				User u = userDao.get(blog.getPostedBy());
				blog.setUser(u);
				Date date = new Date();
				DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
				
				String formattedDate = dateFormat.format(date);
				blog.setDate(formattedDate);
				blog = blogDao.create(blog);
				
	            
	            for(Category c: blog.getCategories()){
	            	c = categoryDAO.get(c.getTitle());
	            	c.getBlogs().add(blog);
	            	categoryDAO.update(c);
	            }
				
				return new ModelAndView("success", "success", blog);
				
			} catch (BlogException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
		
		@RequestMapping(value = "/blog/list", method = RequestMethod.GET)
		public ModelAndView addCategory(HttpServletRequest request) throws Exception {

			try {
				ModelAndView mv = new ModelAndView();
				long userId= Long.valueOf(request.getParameter("userId"));
				User user= userDao.get(userId);
				List<Blog> blogs = blogDao.list();
				List<Category> cats= categoryDAO.list();
				mv.addObject("user", user);
				mv.addObject("blogs", blogs);
				mv.addObject("cats", cats);
				mv.setViewName("blogs-list");
				return mv;
			} catch (BlogException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
		
		@RequestMapping(value = "/blog/listAll", method = RequestMethod.GET)
		public ModelAndView listAll(HttpServletRequest request) throws Exception {

			try {
				ModelAndView mv = new ModelAndView();
				List<Blog> blogs = blogDao.list();
				List<Category> cats= categoryDAO.list();
				mv.addObject("blogs", blogs);
				mv.addObject("cats", cats);
				mv.setViewName("blog-list");
				return mv;
			} catch (BlogException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
		
		@RequestMapping(value = "/blog/mylist", method = RequestMethod.GET)
		public ModelAndView mylist(HttpServletRequest request) throws Exception {

			try {
				ModelAndView mv = new ModelAndView();
				long userId= Long.valueOf(request.getParameter("userId"));
				User user= userDao.get(userId);
				List<Blog> blogs = blogDao.listByUser(userId);
				mv.addObject("user", user);
				mv.addObject("blogs", blogs);
				mv.setViewName("user-dashboard");
				return mv;
			} catch (BlogException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
		
		@RequestMapping(value = "/blog/listByCategory", method = RequestMethod.GET)
		public ModelAndView listByCategory(HttpServletRequest request) throws Exception {

			try {
				Set<Blog> blogsByCat= new HashSet<Blog>();
				List<Category> cats= categoryDAO.list();
				ModelAndView mv = new ModelAndView();
				long catId= Long.valueOf(request.getParameter("catId"));
				List<Blog> blogs = blogDao.list();
				for(Blog blog: blogs) {
					Set<Category> categories =blog.getCategories();
					for(Category cat:categories) {
						if(cat.getCategoryId()==catId) {
							blogsByCat.add(blog);
						}
					}
				}
				mv.addObject("blogs", blogsByCat);
				mv.addObject("cats", cats);
				mv.setViewName("blogs-list");
				return mv;
			} catch (BlogException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
		
		@RequestMapping(value = "/blog/listAllByCategory", method = RequestMethod.GET)
		public ModelAndView listAllByCategory(HttpServletRequest request) throws Exception {

			try {
				Set<Blog> blogsByCat= new HashSet<Blog>();
				List<Category> cats= categoryDAO.list();
				ModelAndView mv = new ModelAndView();
				long catId= Long.valueOf(request.getParameter("catId"));
				List<Blog> blogs = blogDao.list();
				for(Blog blog: blogs) {
					Set<Category> categories =blog.getCategories();
					for(Category cat:categories) {
						if(cat.getCategoryId()==catId) {
							blogsByCat.add(blog);
						}
					}
				}
				mv.addObject("blogs", blogsByCat);
				mv.addObject("cats", cats);
				mv.setViewName("blog-list");
				return mv;
			} catch (BlogException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}

		@RequestMapping(value="/blog/add", method = RequestMethod.GET)
		public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
			ModelAndView mv = new ModelAndView();
			mv.addObject("categories", categoryDAO.list());			
			mv.addObject("blog", new Blog());
			mv.setViewName("blog-form");
			return mv;
		}
		
		@RequestMapping(value = "/blog/delete.htm", method = RequestMethod.GET)
		public ModelAndView deleteBlog(HttpServletRequest request) throws Exception {

			try {	
				String title =request.getParameter("title");
	            Blog blog= blogDao.get(title);
	            Set<Category> categories =blog.getCategories();
	            for(Category c:categories) {
	            	c.getBlogs().remove(blog);
	            }
	            Set<Comment> comms= blog.getComments();
	            for(Comment com:comms) {
	            	Set<Reply> replys = com.getReplys();
	            	for(Reply reply : replys) {
	            		replyDao.delete(reply);
	            	}
	            	commentDao.delete(com);
	            }
				blogDao.delete(blog);
				return new ModelAndView("delete-success", "blog", title);		
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}	
		}
		
		@RequestMapping(value = "/blog/update.htm", method = RequestMethod.GET)
		public ModelAndView updateBlog(HttpServletRequest request) throws Exception {

			String title =request.getParameter("title");
            Blog b= blogDao.get(title);
			return new ModelAndView("blog-update-form", "blog", b);
		}
		
		@RequestMapping(value = "/blog/detail.htm", method = RequestMethod.GET)
		public ModelAndView BlogDetail(HttpServletRequest request) throws Exception {
			int id =Integer.valueOf(request.getParameter("blogId"));
			long userId=Long.valueOf(request.getParameter("userId"));
            Blog b= blogDao.getbyID(id);
            User user=userDao.get(userId);
            List<Comment> comments =  commentDao.listByBlog(id);
			ModelAndView mv = new ModelAndView();
		    mv.addObject("blog",b);
		    mv.addObject("user", user);
		    mv.addObject("comments", comments);
//		    mv.addObject("replys", replys);
			mv.addObject("comment", new Comment());
			mv.addObject("reply", new Reply());
			mv.setViewName("blog-detail");
			return mv;
		}

		@RequestMapping(value = "/blog/update.htm", method = RequestMethod.POST)
		public ModelAndView updatedBlog(HttpServletRequest request) throws Exception {
			
			int id= Integer.valueOf(request.getParameter("id"));
			Blog b= blogDao.getbyID(id);
			String title =request.getParameter("title");
			String content =request.getParameter("content");
			b.setContent(content);
			b.setTitle(title);
            blogDao.update(b);
			return new ModelAndView("success");
		}
		
		@RequestMapping(value = "/blog/search", method = RequestMethod.POST)
		public ModelAndView searchresult(HttpServletRequest request) throws Exception {
			String query = request.getParameter("keyword");
	        String value = request.getParameter("option");
	        ModelAndView mv = new ModelAndView();
	        List<Category> cats= categoryDAO.list();
			mv.addObject("cats", cats);
			mv.setViewName("blogs-list");
	        if (value.equals("title")) {
	        	List<Blog> blogs= blogDao.listByTitle(query);
	        	mv.addObject("blogs", blogs);
	        	return mv;
	        }else if(value.equals("content")){
	        	List<Blog> blogs= blogDao.listByContent(query);
	        	mv.addObject("blogs", blogs);
	        	return mv;
	        }else if (value.equals("author")) {
	        	List<Blog> blogs= blogDao.listByAuthor(query);
	        	mv.addObject("blogs", blogs);
	        	return mv;	
	        }
			
	        return null;
		}
		
}