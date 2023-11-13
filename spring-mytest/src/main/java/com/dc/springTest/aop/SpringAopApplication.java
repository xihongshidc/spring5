package com.dc.springTest.aop;

import com.dc.springTest.aop.service.OrderService;
import com.dc.springTest.aop.service.UserService;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/10 10:41
 */
public class SpringAopApplication {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		// 启动 Spring 的 IOC 容器
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/aop-demo.xml");

		UserService userService = context.getBean(UserService.class);
//		System.out.println(AopUtils.getTargetClass(userService).getName());
		System.out.println(userService);
		Object o = AopUtils.getTargetClass(userService).newInstance();
		System.out.println(o);
		OrderService orderService = context.getBean(OrderService.class);

		System.out.println(orderService);
		// spring  的容器保存的是一个代理的对象.
		//此时调用代理对象 方法,会执行
		userService.createUser("Tom", "Cruise", 55);
		userService.queryUser();

		orderService.createOrder("Leo", "随便买点什么");
		orderService.queryOrder("Leo");
	}
}
