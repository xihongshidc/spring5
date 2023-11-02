package com.dc.springTest.metadatascan.environment;

import com.dc.springTest.injection.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/31 11:42
 */
public class AutowiredDemo {
	@Autowired//会根据类型进行匹配
//	@Qualifier("singleton2")
	private UserHolder singleton1;

	private static Boolean aBoolean;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		annotationConfigApplicationContext.register(AutowiredDemo.class);
		annotationConfigApplicationContext.refresh();
		AutowiredDemo singleton = annotationConfigApplicationContext.getBean(AutowiredDemo.class);
		System.out.println(singleton.singleton1);

		System.out.println(singleton.aBoolean);

	}

	@Bean("singleton2")
	public UserHolder singleton(){
		UserHolder userHolder = new UserHolder();
		userHolder.setId(1l);
		return userHolder;
	}

	@Bean
	@Primary
	public UserHolder singleton2(){
		return new UserHolder();
	}
}
