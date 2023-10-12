package com.dc.springTest.injection;

import com.dc.springTest.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Description: 延迟注入可以实现注入一些非必要的依赖.
 * Author: duancong
 * Date: 2023/10/10 10:45
 */
public class AutowiredAnnotationDependencyInjectDemo {

//	@Autowired//会根据类型进行匹配
//	@Lazy//加了Lazy 注解实际注入的是一个代理对象.
//	private UserHolder userHolder3;

//	@Autowired//会根据类型进行匹配
//	private List<UserHolder> users;

//	@Autowired//会根据类型进行匹配
//	private java.util.Map<String,UserHolder> users;

	@Autowired//支持 Optional 容器
	private Optional<UserHolder> users;

//	@Inject // Inject 和 Autowired 进行注入.
//	private Optional<UserHolder> users;

	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(AutowiredAnnotationDependencyInjectDemo.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		AutowiredAnnotationDependencyInjectDemo bean = annotationConfigApplicationContext.getBean(AutowiredAnnotationDependencyInjectDemo.class);
		System.out.println("==========================");
//		bean.userHolderObjectProvider.stream().forEach(System.out::println);
//		System.out.println("延迟加载 userHolder3 : "+bean.userHolder3);
//		System.out.println("延迟加载 : "+bean.users);
		System.out.println("==========================");
		System.out.println(bean);
		annotationConfigApplicationContext.close();
	}

	//定义的顺序就是注入的顺序,
	@Bean
	public UserHolder userHolder2(User user){
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);//set注入
		userHolder.setId(1l);
		return userHolder;
	}

	@Bean
	@Primary
	public UserHolder userHolder1(User user){
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);//set注入
		userHolder.setId(2l);
		return userHolder;
	}
}
