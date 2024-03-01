package com.dc.springTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: @Configuration 标注的类会被代理增强 ，CGlib 增强。设置了CallBack 拦截器， 当执行@Bean 标注的目标方法时，会进入拦截器BeanMethodInterceptor
 * 在@Bean 方法中执行@Bean 方法, 会进入拦截器,避免重复创建单例bean对象导致破坏单例规则.
 * Author: duancong
 * Date: 2023/12/12 11:48
 */
public class ConfigurationProxyDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(Conifguration.class);
		annotationConfigApplicationContext.refresh();

		Conifguration bean = annotationConfigApplicationContext.getBean(Conifguration.class);
		User bean1 = annotationConfigApplicationContext.getBean("duancong-user",User.class);
		User bean2 = annotationConfigApplicationContext.getBean("user",User.class);
		Student bean3 = annotationConfigApplicationContext.getBean("student",Student.class);
		System.out.println(bean);
		System.out.println(bean1);
		System.out.println(bean2);
		System.out.println(bean3);
	}

}
