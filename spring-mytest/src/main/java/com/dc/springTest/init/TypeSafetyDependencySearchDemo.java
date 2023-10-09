package com.dc.springTest.init;

import com.dc.springTest.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/8 17:49
 */
public class TypeSafetyDependencySearchDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		annotationConfigApplicationContext.register(TypeSafetyDependencySearchDemo.class);
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		ObjectProvider<User> beanProvider = annotationConfigApplicationContext.getBeanProvider(User.class);
		printException("getIfAvailable()安全方法",()-> {
			User bean = beanProvider.getIfAvailable(); //可以自定义依赖查找，如果查找不到的话，可以返回一个自定义的对象。
		});

		printException("getObject()不安全方法",()-> {
			User bean1 = beanProvider.getObject();
		});

		printException("getBean()不安全方法",()-> {
			User bean = annotationConfigApplicationContext.getBean(User.class);
		});

		printException("getBeansOfType()安全方法",()-> {
			Map<String, User> beansOfType = annotationConfigApplicationContext.getBeansOfType(User.class);
		});
		printException("getBeanProvider()安全方法",()-> {
			ObjectProvider<User> beansOfType = annotationConfigApplicationContext.getBeanProvider(User.class);
			beansOfType.stream().forEach(System.out::println);
		});
		//结论getBean() ，以及包括ObjectFactory 的getObject() 方法 是不安全的。 User bean1 = beanProvider.getIfAvailable();是安全的，

	}


	public static void printException(String message, Runnable runnable){
		System.err.println("消息来源 ： "+message);
		try {
			runnable.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
