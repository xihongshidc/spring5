package com.dc.springTest.dependencysource.validation;

import com.dc.springTest.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.AbstractPropertyBindingResult;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/23 11:28
 * @see Errors
 * @see AbstractPropertyBindingResult
 */
public class ErrorsMessageDemo {
	public static void main(String[] args) {
		//创建User对象
		User user = new User();
		user.setName("张三");
		//  声明一个 BeanPropertyBindingResult 继承了一个Errors 和 BindingResult
		BeanPropertyBindingResult beanPropertyBindingResult = new BeanPropertyBindingResult(user,"user");

		beanPropertyBindingResult.reject("user required");
		beanPropertyBindingResult.rejectValue("name","name required");
		List<ObjectError> allErrors = beanPropertyBindingResult.getAllErrors();

		//allErrors = fieldErrors + globalErrors
		List<FieldError> fieldErrors = beanPropertyBindingResult.getFieldErrors();
		List<ObjectError> globalErrors = beanPropertyBindingResult.getGlobalErrors();

		MessageSource defaultMessageSource = getDefaultMessageSource();
		for (ObjectError allError : allErrors) {
			String message = defaultMessageSource.getMessage(allError.getCode(), allError.getArguments(), Locale.getDefault());
			System.out.println(message);
		}
	}

	static MessageSource getDefaultMessageSource() {
		StaticMessageSource staticMessageSource = new StaticMessageSource();
		staticMessageSource.addMessage("user required", Locale.getDefault(),"user 不存在");
		staticMessageSource.addMessage("name required", Locale.getDefault(),"name属性不存在");
		staticMessageSource.addMessage("age requried", Locale.getDefault(),"age属性不存在");
		return staticMessageSource;
	}
}
