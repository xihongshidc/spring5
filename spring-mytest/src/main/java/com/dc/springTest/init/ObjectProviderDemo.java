package com.dc.springTest.init;

import com.dc.springTest.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/8 16:00
 */
public class ObjectProviderDemo {//实现延迟加载
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		annotationConfigApplicationContext.register(ObjectProviderDemo.class);

		annotationConfigApplicationContext.refresh();
		ObjectProvider<String> beanProvider = annotationConfigApplicationContext.getBeanProvider(String.class);

		//在需要的时候创建对应的bean 对象.
		String ifAvailable = beanProvider.getIfAvailable(() -> "test"); //lamda 表达式

		beanProvider.stream().forEach(System.out::println); //结合stream  可以获取多个bean实例.
		System.out.println(ifAvailable);
		//启动应用上下文

		ObjectProvider<User> bean = annotationConfigApplicationContext.getBeanProvider(User.class);
		User ifAvailable1 = bean.getIfAvailable(() -> User.create());
		System.out.println(ifAvailable1);

	}

	@Bean
	@Primary
	public String helloword(){
		return "Hello,Word";
	}

	@Bean
	public String message(){
		return "message";
	}

}
