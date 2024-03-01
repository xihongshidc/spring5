package com.dc.springTest.dependencysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * Description: 非spring 容器 依赖来源 , 实现依赖注入
 * Author: duancong
 * Date: 2023/10/12 15:45
 */
@Configuration
@PropertySource(value = "META-INF/defaultProperty.properties",encoding = "utf-8")
public class ExtPropertyDependencySourceDemo {
	@Value("${usr.name}")
	private String value;

	@Value("${user.id:12}")
	private Long id;

	@Value("${user.url:defaultProperty.properties}")
	private String url;


	@Autowired
	private Environment environment;
	@PostConstruct
	public void init(){
		System.out.println(environment);
		System.out.println(value);
		System.out.println(url);
		System.out.println(id);
	}

	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(ExtPropertyDependencySourceDemo.class);

//		annotationConfigApplicationContext.addBeanFactoryPostProcessor(beanFactory -> {
//			beanFactory.registerResolvableDependency(String.class,"hello World !!! ");
//		});
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		String s = annotationConfigApplicationContext.getEnvironment().resolvePlaceholders("${user:23}");
		System.out.println(s);

	}
}
