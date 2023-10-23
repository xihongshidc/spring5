package com.dc.springTest.dependencysource.validation;

import com.dc.springTest.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/23 14:50
 */
public class BeanValidatedDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/bean-validated.xml");

		Validator bean = classPathXmlApplicationContext.getBean(Validator.class);
		Config configuration = classPathXmlApplicationContext.getBean(Config.class);

		Message message1 = new Message();
		message1.setId(10l);
		message1.setMsg(null);
		Message message = configuration.message(message1);

		System.out.println(bean);

		classPathXmlApplicationContext.close();
	}


	@Component
	@Validated		//Cglib 动态代理 拦截这个类.
	static class Config {

		public Message message(@Valid Message message){
			return message;
		}
	}

	static class Message {

		//@NotEmpty 不能用于非字符串,

		@Min(value = 10)
		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		@NotNull
		private String msg;

	}
}
