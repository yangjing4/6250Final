package com.me.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.me.pojo.Reply;

@Component
public class ReplyValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Reply.class);
	}

	public void validate(Object obj, Errors errors) {
		Reply newReply = (Reply) obj;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
		// "error.invalid.category", "Category Required");
	}
}
