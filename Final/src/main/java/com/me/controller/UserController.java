package com.me.controller;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.Captcha;
import com.me.dao.BlogDAO;
import com.me.dao.CategoryDAO;
import com.me.dao.CommentDAO;
import com.me.dao.ReplyDAO;
import com.me.dao.UserDAO;
import com.me.exception.BlogException;
import com.me.exception.UserException;
import com.me.pojo.Blog;
import com.me.pojo.Category;
import com.me.pojo.Comment;
import com.me.pojo.Person;
import com.me.pojo.Reply;
import com.me.pojo.User;
import com.me.validator.UserValidator;



/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("blogDao")
	BlogDAO blogDao;
	
	@Autowired
	@Qualifier("replyDao")
	ReplyDAO replyDao;
	
	@Autowired
	@Qualifier("commentDao")
	CommentDAO commentDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected ModelAndView goToIndex(HttpServletRequest request, ModelMap map) throws Exception {
		
		try {			
			
			List<Blog> blogs = blogDao.list();
			List<Category> cats= categoryDao.list();
			map.addAttribute("blogs", blogs);
			map.addAttribute("cats", cats);
			return new ModelAndView("index","map",map);
		} catch (BlogException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while load");
		}	
	}
	
	@RequestMapping(value = "/user/visitor", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-dashboard";
	}
	
	@RequestMapping(value = "/user/manager", method = RequestMethod.GET)
	protected String goToManagerHome(HttpServletRequest request) throws Exception {
		return "manager-dashboard";
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	public String showLoginForm() {
		return "user-login";
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	public ModelAndView handleLoginForm(HttpServletRequest request,ModelMap map) {
		
		HttpSession session = (HttpSession) request.getSession();
		System.out.print("loginUser");

		String useremail = request.getParameter("useremail");
		String password = request.getParameter("password");
		try {
			User u = userDao.get(useremail, password);
			
			if (u != null && u.getStatus() == 1) {
				if(u.getType().equals("Manager")) {				
					session.setAttribute("user", u);
					return new ModelAndView("manager-dashboard");
				}else if(u.getType().equals("Visitor")){
					session.setAttribute("user", u);
					List<Blog> blogs =blogDao.listByUser(u.getPersonID());
					session.setAttribute("blogs", blogs);
					return new ModelAndView("user-dashboard");
				}
			} else if (u != null && u.getStatus() == 0) {
				map.addAttribute("errorMessage", "Please activate your account to login!");
				return new ModelAndView("error"); 
			} else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("error"); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}

	
	@RequestMapping(value = "/user/create.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("user-create-form", "user", new User());

	}

	@RequestMapping(value = "/user/create.htm", method = RequestMethod.POST)
	public String handleCreateForm(HttpServletRequest request,@ModelAttribute("user") User user, BindingResult result, ModelMap map) {
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		HttpSession session = request.getSession();
		validator.validate(user, result);
		if (captcha.validate(captchaCode)) {
			if(!(result.hasErrors())) {
			String useremail = user.getEmail().getEmailAddress();
			if(!(useremail==null)) {
			try {
				user.setStatus(0);
				User u = userDao.register(user);
				Random rand = new Random();
				int randomNum1 = rand.nextInt(5000000);
				int randomNum2 = rand.nextInt(5000000);
				try {
					String str = "http://localhost:8080/Final/user/validateemail.htm?useremail=" + useremail+ "&key1="
							+ randomNum1 + "&key2=" + randomNum2;
					session.setAttribute("key1", randomNum1);
					session.setAttribute("key2", randomNum2);
					sendEmail(useremail,
							"Click on this link to activate your account : "+ str);
				} catch (Exception e) {
					System.out.println("Email cannot be sent");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
			} else {
			map.addAttribute("errorMessage", "Invalid Captcha!");
			return "user-create-form";
		}

		return "user-created";
	}

	@RequestMapping(value = "/user/forgotpassword.htm", method = RequestMethod.GET)
	public String getForgotPasswordForm(HttpServletRequest request) {
		
		return "forgot-password";
	}

	@RequestMapping(value = "/user/forgotpassword.htm", method = RequestMethod.POST)
	public String handleForgotPasswordForm(HttpServletRequest request) {
		String useremail = request.getParameter("useremail");
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		if (captcha.validate(captchaCode)) {
			com.me.pojo.Email email = userDao.get(useremail);
			if(!(email==null)) {
				int emailId = (int) email.getId();
				User user = userDao.get(emailId);
				sendEmail(useremail, "Your password is : " + user.getPassword());
				return "forgot-password-success";	
			}
			   return "error";	
		} else {
			request.setAttribute("captchamsg", "Captcha not valid");
			return "forgot-password";
		}
	}

	@RequestMapping(value = "user/resendemail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String useremail = request.getParameter("useremail");
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			String str = "http://localhost:8080/Final/user/validateemail.htm?email=" + useremail + "&key1=" + randomNum1
					+ "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(useremail,
					"Click on this link to activate your account : "+ str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}
		
		return "user-created";
	}

	public void sendEmail(String useremail, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// exist
			email.setSubject("Password Reminder");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}

	@RequestMapping(value = "user/validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request,ModelMap map) {

		// The user will be sent the following link when the use registers
		// This is the format of the email
		// http://hostname:8080/lab10/user/validateemail.htm?email=useremail&key1=<random_number>&key2=<body
		// of the email that when user registers>
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String username= request.getParameter("username");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1") );
		System.out.println(session.getAttribute("key2") );
		
		
		if ((Integer)(session.getAttribute("key1")) == key1 && ((Integer)session.getAttribute("key2"))== key2) {
			try {
				System.out.println("HI________");
				boolean updateStatus = userDao.updateUser(username);
				if (updateStatus) {
					return "user-login";
				} else {

					return "error";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Link expired , generate new link");
			map.addAttribute("resendLink", true);
			return "error";
		}
		return "user-login";

	}
	
	
	@RequestMapping(value = "/user/list.htm", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception {

		try {			
			
			List<User> users = userDao.list();
			return new ModelAndView("user-list", "users", users);
			
		} catch (UserException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}	
	}
	
	@RequestMapping(value = "/user/delete.htm", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) throws Exception {

		try {	
			long id =Long.valueOf(request.getParameter("userId")); 
			User user = userDao.get(id);
			
			Set<Blog> blogs = user.getBlogs();
			for(Blog b: blogs) {
				Set<Category> categories =b.getCategories();
				for(Category c:categories) {
	            	c.getBlogs().remove(b);
	            }
				blogDao.delete(b);
			}
			Set<Reply> replys = user.getReplys();
			for(Reply r:replys) {
				replyDao.delete(r);
			}
			Set<Comment> comments = user.getComments();
			for(Comment c:comments) {
				commentDao.delete(c);
			}
			
			userDao.delete(user);	
			return new ModelAndView("delete-success", "user", id);		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}	
	}
	
	@RequestMapping(value = "/user/update.htm", method = RequestMethod.GET)
	public ModelAndView updateUser(HttpServletRequest request) throws Exception {

		int id =Integer.valueOf(request.getParameter("userId"));
        User u= userDao.get(id);
		return new ModelAndView("user-update-form", "user", u);
	}

	@RequestMapping(value = "/user/update.htm", method = RequestMethod.POST)
	public ModelAndView updatedUser(HttpServletRequest request) throws Exception {
		
		int id =Integer.valueOf(request.getParameter("personID"));        
        String username =request.getParameter("username");
		String firstName =request.getParameter("firstName");
		String lastName =request.getParameter("lastName");
		String emailaddress =request.getParameter("email.emailAddress");
		System.out.println(emailaddress);
		String password=request.getParameter("password");

        userDao.update(id,username,firstName,lastName,password,emailaddress);

		
		return new ModelAndView("user-success","user",username);
	}
}