package com.dc.springTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:36
 */
public class Test {
	public static void main(String[] args) throws ClassNotFoundException {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Conifguration.class);
		User user= annotationConfigApplicationContext.getBean(User.class);

		System.out.println(ClassLoader.getSystemClassLoader().toString());
//		Class<?> user1 = ClassLoader.getSystemClassLoader().loadClass("com.dc.springTest.User");
		System.out.println("com.dc.springTest.User");
		Class<?> user1 = Class.forName("com.dc.springTest.User");
		Method[] methods = user1.getMethods();
		for (Method method : methods) {

		}
		Field[] declaredFields = user1.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			System.out.println(declaredField.getType().getName());
		}
		System.out.println(user);
	}
}
