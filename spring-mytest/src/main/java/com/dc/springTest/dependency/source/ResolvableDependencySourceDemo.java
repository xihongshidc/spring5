package com.dc.springTest.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

/**
 * Description: 非spring 容器 依赖来源 , 实现依赖注入
 * Author: duancong
 * Date: 2023/10/12 15:45
 */
public class ResolvableDependencySourceDemo {
	@Autowired
	private String value;

	@PostConstruct
	public void init(){
		System.out.println(value);
	}

	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(ResolvableDependencySourceDemo.class);

		annotationConfigApplicationContext.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.registerResolvableDependency(String.class,"hello World !!! ");
		});

		//启动应用上下文
		annotationConfigApplicationContext.refresh();

	}
}
