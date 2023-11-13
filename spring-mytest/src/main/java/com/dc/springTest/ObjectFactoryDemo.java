package com.dc.springTest;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/4 22:10
 */
public class ObjectFactoryDemo {

	@Autowired
	private ObjectProvider<List<User>> objectProvider;
	@Autowired
	private ObjectProvider<User> objectProvider1;

//	@Autowired
//	private List<User> list;
	// 间接查找 User
	@Autowired
	private ObjectFactory<List<User>> objectFactory;

	//实例化的方式, 构造器, 静态工厂(静态方法) , 普通工厂方法实例化 ,Factorybean 实例化
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(ObjectFactoryDemo.class);
		annotationConfigApplicationContext.register(UserFactory.class);
		annotationConfigApplicationContext.refresh();
		//依赖的来源不仅仅是自定义的Bean SuperUser |  User
//		User superUser = (User) annotationConfigApplicationContext.getBean("user-create");
		User user = (User) annotationConfigApplicationContext.getBean("userFactory");
//		User user1 = (User) annotationConfigApplicationContext.getBean("user");

//		System.out.println(user1);
		ObjectFactoryDemo bean = annotationConfigApplicationContext.getBean(ObjectFactoryDemo.class);
		System.out.println(bean.objectProvider);
		System.out.println("---" + bean.objectProvider1.getObject());
		System.out.println(bean.objectFactory);
		//org.springframework.beans.factory.support.DefaultListableBeanFactory$DependencyObjectProvider  类型是一样的 ,
		System.out.println(bean.objectFactory.getClass() == bean.objectProvider.getClass());

//		System.out.println(bean.list.size()+"----");
		//间接查找.
		System.out.println(bean.objectFactory.getObject() == bean.objectProvider.getObject());
		System.out.println("延迟加载的User  "+bean.objectProvider.getObject());
		System.out.println("实例对象 : "+bean);
		//还有容器内建依赖(非Bean)    classPathXmlApplicationContext
//		System.out.println(user);

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
	public User user(){
		User user = new User();
		user.setAge("23");
		return user;
	}

	@Override
	public String toString() {
		return "ObjectFactoryDemo{" +
				"objectProvider=" + objectProvider +
				'}';
	}
}
