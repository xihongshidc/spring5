package com.dc.springTest.injection;

import com.dc.springTest.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Description:通过api进行注入
 * Author: duancong
 * Date: 2023/10/9 14:44
 */
public class ApiDependenceInject {
	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(ApiDependenceInject.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		//api方式需要发现 beandefinition
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
		//set方法设置参数, 顺序是不确定, 构造器的参数是确定的.
//		beanDefinitionBuilder.addPropertyReference("user","user1");
		beanDefinitionBuilder.addConstructorArgReference("user1");// 可以实现构造器 Constructor 注入
		//注册 Beandefinition
		annotationConfigApplicationContext.registerBeanDefinition("userHolder",beanDefinitionBuilder.getBeanDefinition());
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		UserHolder bean = annotationConfigApplicationContext.getBean(UserHolder.class);
		System.out.println(bean);

	}

//	@Bean
//	public User user(){
//		User user = new User();
//		user.setAge("1");
//		user.setName("dddc");
//		return user;
//	}
}
