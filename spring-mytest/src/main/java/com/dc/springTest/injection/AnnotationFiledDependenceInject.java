package com.dc.springTest.injection;

import com.dc.springTest.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Description: 基于注解进行注入Bean注解,
 * Author: duancong
 * Date: 2023/10/9 14:44
 */
public class AnnotationFiledDependenceInject {
	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(AnnotationFiledDependenceInject.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		AnnotationFiledDependenceInject bean = annotationConfigApplicationContext.getBean(AnnotationFiledDependenceInject.class);
		System.out.println(bean.userHolder);
		System.out.println(bean.userHolder1);
		System.out.println(bean);

	}

	@Autowired
	UserHolder userHolder;

	@Resource
	UserHolder userHolder1;

	@Bean
	public UserHolder userHolder(User user){
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);//set注入
		this.userHolder =userHolder;
		return userHolder;
	}
}
