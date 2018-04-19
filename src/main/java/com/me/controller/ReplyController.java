package com.me.controller;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.BlogDAO;
import com.me.dao.CommentDAO;
import com.me.dao.ReplyDAO;
import com.me.dao.UserDAO;
import com.me.exception.CommentException;
import com.me.exception.ReplyException;
import com.me.pojo.Blog;
import com.me.pojo.Comment;
import com.me.pojo.Reply;
import com.me.pojo.User;


@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("replyDao")
	ReplyDAO replyDao;
	
	@Autowired
	@Qualifier("commentDao")
	CommentDAO commentDao;
	
	
	

	
	
	@RequestMapping(value = "/reply/add", method = RequestMethod.POST)
	public ModelAndView addCategory(HttpServletRequest request, @ModelAttribute("reply")Reply reply, BindingResult result) throws Exception {

		try {

			long userId= Long.valueOf(request.getParameter("userId"));
			User u = userDao.get(userId);
			reply.setUser(u);
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
			String formattedDate = dateFormat.format(date);
			reply.setDate(formattedDate);
			long commentId= Long.valueOf(request.getParameter("commentId"));
			Comment com= commentDao.get(commentId);
			reply.setComment(com);

			reply = replyDao.create(reply);
			
			return new ModelAndView("success", "success", reply);
			
		} catch (ReplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/reply/delete.htm", method = RequestMethod.GET)
	public ModelAndView deleteComment(HttpServletRequest request) throws Exception {

		try {	
			long id =Long.valueOf(request.getParameter("id"));
            Reply re= replyDao.get(id);
            Comment comm= re.getComment();
            comm.getReplys().remove(re);
            commentDao.update(comm);
            replyDao.delete(re);
			return new ModelAndView("delete-success", "blog", re);		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}	
	}

}
