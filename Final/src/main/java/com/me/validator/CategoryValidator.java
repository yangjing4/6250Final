package com.me.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.dao.CategoryDAO;
import com.me.exception.CategoryException;
import com.me.pojo.Category;

@Component
public class CategoryValidator implements Validator {

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	public boolean supports(Class aClass) {
		return Category.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Category cat = (Category) obj;
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.category", "Category Required");
		
		if (errors.hasErrors()) {
            return;//Skip the rest of the validation rules
        }
        
	
		try {		
			Category c = categoryDao.get("Cc");
			System.out.println(c.getTitle());
			if(c !=null)
				errors.rejectValue("title", "error.invalid.category", "Category already Exists");
			
		} catch (CategoryException e) {
			System.err.println("Exception in Category Validator");
		}
		
		
		
	
	}
}
