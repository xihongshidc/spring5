package com.dc.springTest.init;

import com.dc.springTest.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/8 11:53
 */
public class SingletonBeanRegistry {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		User user = new User();
		ConfigurableListableBeanFactory beanFactory = annotationConfigApplicationContext.getBeanFactory();
		//注册单例bean
		beanFactory.registerSingleton("user1",user);

		//启动应用上下文
		annotationConfigApplicationContext.refresh();

		User bean = annotationConfigApplicationContext.getBean(User.class);
		System.out.println(bean.equals(user));
	}


}
