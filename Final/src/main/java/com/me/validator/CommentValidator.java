package com.me.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.me.pojo.Comment;

@Component
public class CommentValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Comment.class);
	}

	public void validate(Object obj, Errors errors) {
		Comment newComment = (Comment) obj;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
		// "error.invalid.category", "Category Required");
	}
}
