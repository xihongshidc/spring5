package com.dc.springTest.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/2 17:58
 */
public class EventDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(EventDemo.class);
//		annotationConfigApplicationContext.register(MyCustomEventDemo.MyListener.class);
		//基于api实现.
		annotationConfigApplicationContext.addApplicationListener(new ApplicationListener<MyEventObject>() {
			@Override
			public void onApplicationEvent(MyEventObject event) {
				System.out.println("Thread : " + Thread.currentThread().getName());
				System.out.println("事件" + event);
//				throw new RuntimeException("故意抛异常了");
			}
		});

		annotationConfigApplicationContext.refresh();
		annotationConfigApplicationContext.publishEvent(new ContextStartedEvent(annotationConfigApplicationContext));

		annotationConfigApplicationContext.publishEvent(new MyEventObject("hello world"));
		annotationConfigApplicationContext.publishEvent(new ApplicationEvent("today is friday") {
		});

	}

	@EventListener
	public void onPublish(ApplicationEvent applicationEvent){
		System.out.println(applicationEvent + "*********");

	}

}
