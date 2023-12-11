package com.dc.springTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/24 22:10
 */
public class TestStaticMethod {

	//实例化的方式, 构造器, 静态工厂(静态方法) , 普通工厂方法实例化 ,Factorybean 实例化
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/bean-creation.xml");
		//依赖的来源不仅仅是自定义的Bean SuperUser |  User
		User superUser = (User) classPathXmlApplicationContext.getBean("user-create");
		User user = (User) classPathXmlApplicationContext.getBean("UserFactory");
		//还有容器内建依赖(非Bean)    classPathXmlApplicationContext
		System.out.println(user);
		System.out.println(superUser==user);
		String s = "dc";

		TestStaticMethod testStaticMethod = new TestStaticMethod();
		testStaticMethod.username=s;
		s= "mmmd";
		System.out.println(testStaticMethod.username);

	}

	private String username;

}
