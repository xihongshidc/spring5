package com.dc.springTest.injection;

import com.dc.springTest.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * Description: 延迟注入可以实现注入一些非必要的依赖.
 * Author: duancong
 * Date: 2023/10/10 10:45
 */
public class LazyAnnotationDependencyInjectDemo {

	@Autowired
	private UserHolder userHolder;

	@Autowired
	private ObjectProvider<UserHolder> userHolderObjectProvider;    //支持单一类型

	@Autowired
	private ObjectProvider<List<UserHolder>> userHolderObjectProviderList; //支持和集合类型


	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(LazyAnnotationDependencyInjectDemo.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		LazyAnnotationDependencyInjectDemo bean = annotationConfigApplicationContext.getBean(LazyAnnotationDependencyInjectDemo.class);
		System.out.println("==========================");
//		bean.userHolderObjectProvider.stream().forEach(System.out::println);
		System.out.println(" 延迟加载 " +bean.userHolderObjectProvider.getIfAvailable());
		System.out.println("延迟加载 : "+bean.userHolder);
		System.out.println("==========================");
		bean.userHolderObjectProviderList.getObject().stream().forEach(System.out::println);
		System.out.println(bean);
	}


	@Bean
	public UserHolder userHolder(User user){
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
