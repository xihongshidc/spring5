package com.dc.springTest.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Description: 注入ApplicationEventPublisher
 * Author: duancong
 * Date: 2023/10/27 12:15
 */
public class InjectApplicationContextPublishDemo implements ApplicationEventPublisherAware, ApplicationContextAware, BeanPostProcessor {

	@Autowired
	public ApplicationEventPublisher publisher;

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(InjectApplicationContextPublishDemo.class);
		annotationConfigApplicationContext.register(ApplicationContextListenerDemo.MyListener.class);

		annotationConfigApplicationContext.refresh();
		InjectApplicationContextPublishDemo bean = annotationConfigApplicationContext.getBean(InjectApplicationContextPublishDemo.class);

//		不提供依赖查找。 但是支持依赖注入
//		ApplicationEventPublisher bean1 = annotationConfigApplicationContext.getBean(ApplicationEventPublisher.class);

		// 但是广播器确支持 依赖查找， 是为了方便 非spring bean 管理环境的扩展。
		Object applicationEventMulticaster = annotationConfigApplicationContext.getBean("applicationEventMulticaster");
		System.out.println("  "+applicationEventMulticaster);

	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		System.out.println("1............");
		applicationEventPublisher.publishEvent(new ApplicationEvent("ApplicationContextAware  注入ApplicationEventPublisher ") {
		});
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("2............");
		applicationContext.publishEvent(new ApplicationEvent("ApplicationEventPublisherAware  注入ApplicationEventPublisher ") {
		});
	}

	@PostConstruct
	public void init(){
		System.out.println("3............");

		publisher.publishEvent(new ApplicationEvent(" ApplicationEventPublisher 发布 注入ApplicationEventPublisher ") {
		});

		applicationContext.publishEvent(new ApplicationEvent(" ApplicationContext 发布 注入ApplicationEventPublisher ") {
		});
	}


}
