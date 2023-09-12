package com.dc.springTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:36
 */
public class Test {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Conifguration.class);
		User user= annotationConfigApplicationContext.getBean(User.class);

		System.out.println(user);
	}
}
