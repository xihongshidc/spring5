package com.dc.springTest.annotation;

import com.dc.springTest.SuperUser;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/24 22:10
 */
public class Test2 {
	public static void main(String[] args) {


		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/injection.xml");
		//依赖的来源不仅仅是自定义的Bean SuperUser |  User
		SuperUser superUser = (SuperUser) classPathXmlApplicationContext.getBean("SuperUser");
		//还有容器内建依赖(非Bean)    classPathXmlApplicationContext
		System.out.println("ObjectFactory  :"+superUser.getContextObjectFactory().getObject());
		//ObjectFactory 一般用于延迟加载bean

		//还有容器内建的Bean 对象
		Environment environment = classPathXmlApplicationContext.getBean(Environment.class);
		System.out.println(environment);
		System.out.println(superUser.toString());
	}
}
