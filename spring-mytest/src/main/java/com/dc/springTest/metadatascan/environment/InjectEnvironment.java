package com.dc.springTest.metadatascan.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Description: 依赖注入 Environment ,依赖查找 Environment
 * Author: duancong
 * Date: 2023/10/30 18:36
 * @see Environment
 */
public class InjectEnvironment implements EnvironmentAware , ApplicationContextAware {

	@Autowired
	private Environment environment;

	private Environment environment2;

	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(InjectEnvironment.class);
		annotationConfigApplicationContext.refresh();

		InjectEnvironment bean = annotationConfigApplicationContext.getBean(InjectEnvironment.class);
		System.out.println(bean.environment2 == bean.environment);

		System.out.println(bean.environment2 == annotationConfigApplicationContext.getEnvironment());

		System.out.println((annotationConfigApplicationContext == bean.applicationContext));

		System.out.println(bean.applicationContext.getEnvironment() == bean.environment2);
		//依赖查找。
		Environment bean1 = annotationConfigApplicationContext.getBean(Environment.class);
		System.out.println(bean1 == bean.environment);
		annotationConfigApplicationContext.close();

	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment2=environment;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
}
