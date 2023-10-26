package com.dc.springTest.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Description: Spring 事件
 * Author: duancong
 * Date: 2023/10/26 18:15
 */
@EnableAsync
public class ApplicationContextListenerDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(ApplicationContextListenerDemo.class);
		//基于接口实现.
		annotationConfigApplicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("事件" + event);
			}
		});

		annotationConfigApplicationContext.refresh();
		annotationConfigApplicationContext.stop();
		annotationConfigApplicationContext.start();
		annotationConfigApplicationContext.close();
	}

	@EventListener
	@Async
	public void onApplicationEvent(ContextRefreshedEvent event) {
		getPrintln("@EventListener 事件 ContextRefreshedEvent" );
	}
	@EventListener
	public void onApplicationEvent(ContextStoppedEvent event) {
		getPrintln("@EventListener 事件 ContextStoppedEvent" );
	}
	@EventListener
	public void onApplicationEvent(ContextStartedEvent event) {
		getPrintln("@EventListener 事件 ContextStartedEvent");
	}
	@EventListener
	public void onApplicationEvent(ContextClosedEvent event) {
		getPrintln("@EventListener 事件 ContextClosedEvent");
	}

	private static void getPrintln(String print) {
		System.out.println( print +"  ---- "+ Thread.currentThread().getName());
	}

}
