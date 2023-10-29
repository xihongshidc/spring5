package com.dc.springTest.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/27 17:45
 */
public class MyCustomEventDemo {
	static ExecutorService executorService = null;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(MyCustomEventDemo.class);
		annotationConfigApplicationContext.register(MyListener.class);
		//基于api实现.
		annotationConfigApplicationContext.addApplicationListener(new ApplicationListener<MyEventObject>() {
			@Override
			public void onApplicationEvent(MyEventObject event) {
				System.out.println("Thread : " + Thread.currentThread().getName());
				System.out.println("事件" + event);
				throw new RuntimeException("故意抛异常了");
			}
		});
		annotationConfigApplicationContext.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(ContextClosedEvent event) {
				System.out.println("Thread Name "+Thread.currentThread().getName());
				if (!executorService.isShutdown()) {
					executorService.shutdown();
				}

			}
		});

		annotationConfigApplicationContext.refresh(); //

		//基于 api 设置 线程池会让所有的事件都通过线程池进行调用... 设置完线程池之后会开启异步处理/
		Object caster = annotationConfigApplicationContext.getBean("applicationEventMulticaster");
		if (caster instanceof SimpleApplicationEventMulticaster) {
			SimpleApplicationEventMulticaster applicationEventMulticaster = (SimpleApplicationEventMulticaster) caster;
			executorService = Executors.newFixedThreadPool(3,new CustomizableThreadFactory("dddd"));
			applicationEventMulticaster.setTaskExecutor(executorService);
			applicationEventMulticaster.setErrorHandler(err->{
				System.err.println("异常原因 : " + err.getMessage());
			});
		}

		annotationConfigApplicationContext.publishEvent(new MyEventObject("hello world"));
		annotationConfigApplicationContext.publishEvent(new ApplicationEvent("today is friday") {
		});

		annotationConfigApplicationContext.close(); //
	}

	static class MyListener implements ApplicationListener<ApplicationEvent>{
		@Override
		public void onApplicationEvent(ApplicationEvent event) {
			System.out.println(" MyListener 事件 " + event +Thread.currentThread().getName());
		}
	}

}
