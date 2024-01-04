package com.dc.springTest.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Description:
 * Author: duancong
 * Date: 2023/12/22 10:40
 */
@ComponentScan(basePackages = "com.dc.springTest.demo")
public class DemoExtends {
	@Autowired
	private List<A> a;


	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(DemoExtends.class);
		annotationConfigApplicationContext.refresh();
		DemoExtends bean = annotationConfigApplicationContext.getBean(DemoExtends.class);
		String[] split = StringUtils.split("a,v,x", ",");

		System.out.println(split.length);
		System.out.println(split);
		System.out.println(bean.a);


	}

}

@Component
class A{

}
@Component
class B extends A{

}
@Component
class C extends A{

}