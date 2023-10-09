package com.dc.springTest.init;

import com.dc.springTest.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/8 10:23
 */
@Configuration
public class initDemo {
	public static void main(String[] args) throws InterruptedException {
		// 基于ApplicationContext实现的ioc

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		//注册bean
		annotationConfigApplicationContext.register(initDemo.class);
		//启动应用上下文
		annotationConfigApplicationContext.refresh();

		System.out.println("spring 应用上下文已启动  ");
		User bean = annotationConfigApplicationContext.getBean(User.class);//懒加载的bean 是在getBean 时初始化的
		User bean1 = (User) annotationConfigApplicationContext.getBean("init");//懒加载的bean 是在getBean 时初始化的
		annotationConfigApplicationContext.close();
		System.out.println(bean1.equals(bean));
		Thread.sleep(5000l);
		System.gc();

		Thread.sleep(5000l);
	}

	@Bean(initMethod = "initdemo" ,destroyMethod = "predes")
	@Lazy
	public User init(){     //如果没有指定bean的名字，那么默认就是 方法名字，
		return new User();
	}



}
