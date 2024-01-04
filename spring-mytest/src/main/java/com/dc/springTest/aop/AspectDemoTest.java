package com.dc.springTest.aop;

import com.dc.springTest.aop.annotation.AspectDemo;
import com.dc.springTest.aop.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 * Author: duancong
 * Date: 2024/1/4 17:10
 */
public class AspectDemoTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(AspectDemo.class);
		annotationConfigApplicationContext.scan("com.dc.springTest.aop");
		annotationConfigApplicationContext.refresh();
		OrderService bean = annotationConfigApplicationContext.getBean(OrderService.class);
		Order dc = bean.queryOrder("dc");
		System.out.println(dc);
	}
}
