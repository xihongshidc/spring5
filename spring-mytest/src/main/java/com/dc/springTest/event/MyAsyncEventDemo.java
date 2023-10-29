package com.dc.springTest.event;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 基于注解 实现异步发送消息.
 * Author: duancong
 * Date: 2023/10/27 17:45
 */
@EnableAsync
public class MyAsyncEventDemo {
	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\class");  //--该设置用于输出cglib动态代理产生的类
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");  // --该设置用于输出jdk动态代理产生的类


		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(MyAsyncEventDemo.class);
		//基于api实现.
		annotationConfigApplicationContext.addApplicationListener(new ApplicationListener<MyEventObject>() {
			@Override
			public void onApplicationEvent(MyEventObject event) {
				System.out.println("Thread : " + Thread.currentThread().getName());
				System.out.println("事件" + event);
			}
		});

		annotationConfigApplicationContext.refresh(); //
		annotationConfigApplicationContext.publishEvent(new MyEventObject("hello world"));
//		annotationConfigApplicationContext.publishEvent(new ApplicationEvent("today is friday") {
//		});

		annotationConfigApplicationContext.close(); //
	}


	@EventListener
	@Async //创建 代理类。
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println(" MyListener 事件 " + event + Thread.currentThread().getName());
	}

	@Bean
	public ExecutorService taskExecutor(){
		return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("dc -1-1"));
	}
}
