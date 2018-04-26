package com.me.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.BlogDAO;
import com.me.dao.CategoryDAO;
import com.me.dao.CommentDAO;
import com.me.dao.ReplyDAO;
import com.me.dao.UserDAO;
import com.me.exception.BlogException;
import com.me.exception.CommentException;
import com.me.pojo.Blog;
import com.me.pojo.Category;
import com.me.pojo.Comment;
import com.me.pojo.Reply;
import com.me.pojo.User;
import com.me.validator.UserValidator;



@Controller
@RequestMapping("/comment/*")
public class CommentController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("blogDao")
	BlogDAO blogDao;
	
	@Autowired
	@Qualifier("commentDao")
	CommentDAO commentDao;
	
	@Autowired
	@Qualifier("replyDao")
	ReplyDAO replyDao;
	

	
	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	public ModelAndView addCategory(HttpServletRequest request,@ModelAttribute("comment")Comment comment, BindingResult result) throws Exception {
			
		ModelAndView mv = new ModelAndView();
		long userId= Long.valueOf(request.getParameter("userId"));
		User u = userDao.get(userId);
		try {

			
			comment.setUser(u);
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
			
			String formattedDate = dateFormat.format(date);
			comment.setDate(formattedDate);
			
			
//			Blog b=comment.getBlog();
			int id = Integer.valueOf(request.getParameter("blogId"));
			Blog b= blogDao.getbyID(id);
			System.out.println(b.getId());
			comment.setBlog(b);
			comment = commentDao.create(comment);
			mv.addObject("user", u);
			mv.addObject("message", "comment added successfully");
			mv.setViewName("success");
			return mv;
			
		} catch (CommentException e) {
			System.out.println(e.getMessage());
			mv.addObject("user", u);
			mv.addObject("errorMessage", "error while login");
			mv.setViewName("error");
			return mv;
		}
		
		
	}
	
	@RequestMapping(value = "/comment/delete.htm", method = RequestMethod.GET)
	public ModelAndView deleteComment(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		long userId= Long.valueOf(request.getParameter("userId"));
		User user = userDao.get(userId);

		try {	
			long id =Long.valueOf(request.getParameter("id"));
            Comment comm= commentDao.get(id);
            Blog blog= comm.getBlog();
            Set<Comment> comms= blog.getComments();
            comms.remove(comm);
            blogDao.update(blog);
            Set<Reply> replys = comm.getReplys();
            for(Reply re: replys) {
            	replys.remove(re);
            	replyDao.delete(re);
            }
            commentDao.delete(comm);
            mv.addObject("user", user);
            mv.addObject("message", "comment deleted successfully");
            mv.setViewName("delete-success");
			return mv;	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mv.addObject("user", user);
			mv.addObject("errorMessage", "error while login");
			mv.setViewName("error");
			return mv;
		}	
	}
	
	@RequestMapping(value = "/comment/error",method=RequestMethod.GET)
	public ModelAndView error() throws Exception {
		return new ModelAndView("injectionError");	
	}
	

}
