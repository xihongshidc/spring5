package com.dc.springTest.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
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
public class ApplicationContextListenerDemo  implements ApplicationEventPublisherAware {//实现Aware 接口可以扩展 spring 事件发布器

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(ApplicationContextListenerDemo.class);
		annotationConfigApplicationContext.register(MyListener.class);
		//基于api 添加 监听器 .
		annotationConfigApplicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("事件" + event);
			}
		});
		annotationConfigApplicationContext.refresh(); // ContextRefreshedEvent
		annotationConfigApplicationContext.stop();    // ContextStopedEvent
		annotationConfigApplicationContext.start();   // ContextStartedEvent
		annotationConfigApplicationContext.close();   // ContextClosedEvent
	}

	@EventListener
	@Async
	public void onApplicationEvent(ContextRefreshedEvent event) {
		getPrintln("@EventListener 事件 ContextRefreshedEvent " + event.getTimestamp());
	}
	@EventListener
	public void onApplicationEvent(ContextStoppedEvent event) {
		getPrintln("@EventListener 事件 ContextStoppedEvent " + event.getTimestamp());
	}
	@EventListener
	public void onApplicationEvent(ContextStartedEvent event) {
		getPrintln("@EventListener 事件 ContextStartedEvent " + event.getTimestamp());
	}
	@EventListener
	public void onApplicationEvent(ContextClosedEvent event) {
		getPrintln("@EventListener 事件 ContextClosedEvent " + event.getTimestamp());
	}

	private static void getPrintln(String print) {
		System.out.println( print +"  ---- "+ Thread.currentThread().getName());
	}


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
	applicationEventPublisher.publishEvent(new ApplicationEvent(" hello world! ") {
	});
	// PayloadApplicationEvent 事件
	applicationEventPublisher.publishEvent(" ddddd ");
	}
	//基于spring bean 配置ApplicationListener
	static class MyListener implements ApplicationListener<ApplicationEvent>{
		@Override
		public void onApplicationEvent(ApplicationEvent event) {
			getPrintln(" MyListener 事件 " + event);
		}
	}

//	class MyApplicationPublish implements ApplicationEventPublisher{
//
//		@Override
//		public void publishEvent(Object event) {
//			publishEvent(new ApplicationEvent("hello world"){
//
//			});
//		}
//	}

}
