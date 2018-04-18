package com.me.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.me.pojo.Blog;

@Component
public class BlogValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Blog.class);
	}

	public void validate(Object obj, Errors errors) {
		Blog newAdvert = (Blog) obj;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
		// "error.invalid.category", "Category Required");
	}
}
