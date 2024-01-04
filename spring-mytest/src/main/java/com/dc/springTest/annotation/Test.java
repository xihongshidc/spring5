package com.dc.springTest.annotation;

import com.dc.springTest.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/24 19:03
 */
public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/depedence.xml");
		User objectFatcory = classPathXmlApplicationContext.getBean(User.class);
		ObjectFactory<User> factory = (ObjectFactory<User>) classPathXmlApplicationContext.getBean("objectFactory");
		System.out.println(factory.getObject());

		ListableBeanFactory classPathXmlApplicationContext1 = classPathXmlApplicationContext;
		Map<String, User> beansWithAnnotation = (Map) classPathXmlApplicationContext.getBeansWithAnnotation(Super.class);
		Map<String, User> beansOfType = classPathXmlApplicationContext.getBeansOfType(User.class);
		System.out.println(beansOfType.toString());
		User user = beansOfType.get("user");
		System.out.println(user.getApplicationContext());
		System.out.println(beansWithAnnotation.toString());
	}
}
