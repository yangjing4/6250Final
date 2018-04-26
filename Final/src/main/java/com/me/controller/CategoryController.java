package com.me.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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
import com.me.dao.CategoryDAO;
import com.me.dao.UserDAO;
import com.me.exception.CategoryException;
import com.me.pojo.Category;
import com.me.pojo.User;
import com.me.validator.CategoryValidator;

@Controller
@RequestMapping("/category/*")
public class CategoryController {

		@Autowired
		@Qualifier("categoryValidator")
		CategoryValidator categoryValidator;
		
		@Autowired
		@Qualifier("categoryDao")
		CategoryDAO categoryDao;
		
		@Autowired
		@Qualifier("userDao")
		UserDAO userDao;


		@RequestMapping(value = "/category/add", method = RequestMethod.POST)
		public ModelAndView addCategory(@ModelAttribute("category") Category category,HttpServletRequest request,BindingResult result) throws Exception {
//			System.out.println(category.getTitle());
			ModelAndView mv = new ModelAndView();
			Category c = categoryDao.get(category.getTitle());
			long userId= Long.valueOf(request.getParameter("userId"));
			User user= userDao.get(userId);
			if(c!=null) {
				mv.addObject("user", "user");
				mv.addObject("errorMessage", "category has been added");
				mv.setViewName("error");
				return mv;
			}else if (result.hasErrors()) {
				mv.addObject("user", "user");
				mv.addObject("category", category);
				mv.setViewName("category-form");
				return mv;
			}

			try {				
				category = categoryDao.create(category.getTitle());
			} catch (CategoryException e) {
				System.out.println(e.getMessage());
				mv.addObject("user", "user");
				mv.addObject("errorMessage", "error while login");
				mv.setViewName("error");
				return mv;
			}
			mv.setViewName("success");
			mv.addObject("user", user);
			mv.addObject("category-success", category);
			mv.addObject("message", "category added successful");
		    return mv;
			
		}

		@RequestMapping(value="/category/add", method = RequestMethod.GET)
		public ModelAndView initializeForm(HttpServletRequest request) throws Exception {	
			ModelAndView mv = new ModelAndView();
			long userId= Long.valueOf(request.getParameter("userId"));
			User user= userDao.get(userId);
			mv.addObject("user", user);
			mv.addObject("category", new Category());
			mv.setViewName("category-form");		
			return mv;
		}
		
		@RequestMapping(value = "/category/error",method=RequestMethod.GET)
		public ModelAndView error() throws Exception {
			return new ModelAndView("injectionError");	
		}
		
				


}