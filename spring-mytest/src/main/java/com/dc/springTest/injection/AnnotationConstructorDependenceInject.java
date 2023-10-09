package com.dc.springTest.injection;

import com.dc.springTest.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Description: 构造器 通过注解注入手动注入
 * Author: duancong
 * Date: 2023/10/9 14:44
 */
public class AnnotationConstructorDependenceInject {
	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(AnnotationConstructorDependenceInject.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		UserHolder bean = annotationConfigApplicationContext.getBean(UserHolder.class);
		System.out.println(bean);
	}

	@Bean
	public UserHolder userHolder(User user){
		UserHolder userHolder = new UserHolder(user);
		return userHolder;
	}


}
