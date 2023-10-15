package com.dc.springTest.dependencysource;

import com.dc.springTest.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/12 11:49
 */
public class DependencyDemo {


	@Autowired
	private User user;

	@Autowired
	private ListableBeanFactory beanFactory;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ApplicationContext applicationContext;


	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(DependencyDemo.class);
		annotationConfigApplicationContext.getAutowireCapableBeanFactory();

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		DependencyDemo bean = annotationConfigApplicationContext.getBean(DependencyDemo.class);
		System.out.println(annotationConfigApplicationContext.getBeanFactory());
		System.out.println("beanFactory  : "+ bean.beanFactory + (bean.beanFactory ==annotationConfigApplicationContext.getBeanFactory()));
		System.out.println("resourceLoader " +(bean.resourceLoader == bean.beanFactory));
		System.out.println("applicationEventPublisher " +(bean.applicationEventPublisher == bean.beanFactory));
		System.out.println("applicationContext " +(bean.applicationContext == bean.applicationEventPublisher));


	}
}
