package com.dc.springTest.dependencysource.validation;

import com.dc.springTest.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

import static com.dc.springTest.dependencysource.validation.ErrorsMessageDemo.getDefaultMessageSource;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/23 12:14
 * @see Validator
 */
public class ValidationDemo {
	public static void main(String[] args) {

		UserValidation userValidation = new UserValidation();
		//user 对象
		User user = new User();

		user.setName("111");
//		user.setAge("11111");

		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(user, "user");

		//框架会封装调用该方法,
		boolean supports = userValidation.supports(user.getClass());
		System.out.println(supports);

		//框架会封装调用该方法
		userValidation.validate(user,bindingResult);
		MessageSource defaultMessageSource = getDefaultMessageSource();
		for (ObjectError allError : bindingResult.getAllErrors()) {
			System.out.println(defaultMessageSource.getMessage(allError.getCode(), allError.getArguments(), Locale.getDefault()));
		}
	}

	static class UserValidation implements Validator{

		@Override
		public boolean supports(Class<?> clazz) {
			return User.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			// 此时是User对象
			User user = (User) target;
			//判断这个字段是否为空或者是否为"";
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"age","age requried");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","name requried");
//			String name = user.getName();


		}
	}
}
