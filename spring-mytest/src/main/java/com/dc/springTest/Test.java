package com.dc.springTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:36
 */
public class Test {
	public static void main(String[] args) throws ClassNotFoundException {

//		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Conifguration.class);
		AnnotationConfigApplicationContext apx = new AnnotationConfigApplicationContext(UserFactory.class);
//		User user= annotationConfigApplicationContext.getBean(User.class);
		Object bean = apx.getBean(User.class, User.class);
		System.out.println(bean instanceof String);
		System.out.println(HashMap.class.isAssignableFrom(AbstractMap.class));
		System.out.println(HashMap.class.isAssignableFrom(java.util.Map.class));
		System.out.println(java.util.Map.class.isAssignableFrom(HashMap.class));
		java.util.Map<Object, Object> objectObjectHashMap = new LinkedHashMap<>();
		HashMap<Object, Object> objectObjectHashMap1 = new HashMap<>();
		LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
		LinkedHashMap objectObjectHashMap2 = (LinkedHashMap) objectObjectHashMap;
		System.out.println(objectObjectHashMap2);
		System.out.println((objectObjectHashMap.getClass() == objectObjectHashMap1.getClass())+"**");
		System.out.println(objectObjectHashMap.getClass().isAssignableFrom(objectObjectLinkedHashMap.getClass())+"----");
//		Map objectObjectHashMap1 = objectObjectHashMap;
//		HashMap objectObjectHashMap11 = (HashMap) objectObjectHashMap1;
//		System.out.println(objectObjectHashMap11);
//		System.out.println(objectObjectHashMap1);
		System.out.println(objectObjectLinkedHashMap instanceof Cloneable);

//		System.out.println(ClassLoader.getSystemClassLoader().toString());
////		Class<?> user1 = ClassLoader.getSystemClassLoader().loadClass("com.dc.springTest.User");
//		System.out.println("com.dc.springTest.User");
//		Class<?> user1 = Class.forName("com.dc.springTest.User");
//		Method[] methods = user1.getMethods();
//		for (Method method : methods) {
//			System.out.println("1111"+ method);
//
//		}
//		Field[] declaredFields = user1.getDeclaredFields();
//		for (Field declaredField : declaredFields) {
//			System.out.println(declaredField.getType().getName());
//		}
//		System.out.println(user);
	}
}
