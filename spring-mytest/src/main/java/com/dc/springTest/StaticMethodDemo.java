package com.dc.springTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * Description:
 *  静态方法的生成的bean对象不会出现自我引用..
 * {@link DefaultListableBeanFactory#isSelfReference(java.lang.String, java.lang.String)}
 * Author: duancong
 * Date: 2023/11/4 22:10
 */
public class StaticMethodDemo {

	@Autowired
	private User user;

	//实例化的方式, 构造器, 静态工厂(静态方法) , 普通工厂方法实例化 ,Factorybean 实例化
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(StaticMethodDemo.class);
		annotationConfigApplicationContext.register(UserFactory.class);
		annotationConfigApplicationContext.refresh();
//		User user1 = (User) annotationConfigApplicationContext.getBean("user");
		User user2 = (User) annotationConfigApplicationContext.getBean("userFactory");
		System.out.println(user2);
		StaticMethodDemo bean = annotationConfigApplicationContext.getBean(StaticMethodDemo.class);

		System.out.println("实例对象 : "+bean.user);
	}

	private static String name;

	@PostConstruct
	public static void Autowire(){
		System.out.println("22222");
		name="dc";
	}

	@Bean //标记为static 方法 执行的优先级会提升.
//	@Primary
//	@Lazy
	public static User user(){
		User user = new User();
		user.setAge("23");
		return user;
	}
}
