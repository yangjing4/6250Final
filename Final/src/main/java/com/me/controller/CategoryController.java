package com.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.me.dao.CategoryDAO;
import com.me.exception.CategoryException;
import com.me.pojo.Category;
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

		@InitBinder
		private void initBinder(WebDataBinder binder) {
			binder.setValidator(categoryValidator);
		}

		@RequestMapping(value = "/category/add", method = RequestMethod.POST)
		public ModelAndView addCategory(@ModelAttribute("category")Category category,BindingResult result) throws Exception {
			
//			categoryValidator.validate(category, result);
			ModelAndView mv = new ModelAndView();
			
			if (result.hasErrors()) {
				mv.addObject("category", category);
				mv.setViewName("category-form");
				return mv;
			}

			try {				
				category = categoryDao.create(category.getTitle());
			} catch (CategoryException e) {
				System.out.println(e.getMessage());
				mv.addObject("errorMessage", "error while login");
				mv.setViewName("error");
				return mv;
			}
			mv.setViewName("success");
			mv.addObject("category-success", category);
			mv.addObject("message", "category added successful");
		    return mv;
			
		}

		@RequestMapping(value="/category/add", method = RequestMethod.GET)
		public ModelAndView initializeForm() throws Exception {			
			return new ModelAndView("category-form", "category", new Category());
		}


}