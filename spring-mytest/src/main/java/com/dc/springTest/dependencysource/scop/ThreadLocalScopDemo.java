package com.dc.springTest.dependencysource.scop;

import com.dc.springTest.injection.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 自定义线程级别的作用域对象创建.
 * Author: duancong
 * Date: 2023/10/14 22:20
 */
public class ThreadLocalScopDemo {

	@Autowired//会根据类型进行匹配
	private UserHolder userHolder;

	@Bean
	@Scope(ThreadLocalScop.NAME_Scop)
	private UserHolder userHolder() {
		UserHolder userHolder = new UserHolder();
		userHolder.setId(System.nanoTime());
		return userHolder;
	}
	public static void main(String[] args) throws InterruptedException {
//		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(ThreadLocalScopDemo.class);
		annotationConfigApplicationContext.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.registerScope(ThreadLocalScop.NAME_Scop,new ThreadLocalScop());
		});
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
//		ThreadLocalScopDemo bean = annotationConfigApplicationContext.getBean(ThreadLocalScopDemo.class);
		ThreadLocalScopDemo bean = annotationConfigApplicationContext.getBean("threadLocalScopDemo",ThreadLocalScopDemo.class);
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 13; i++) {
			executorService.execute(() -> {
				System.out.printf(" bean : %s   , thread : %d" ,annotationConfigApplicationContext.getBean(UserHolder.class).toString(),Thread.currentThread().getId());
			});
//			Thread thread = new Thread(() -> {
//				System.out.printf(" bean : %s   , thread : %d" ,annotationConfigApplicationContext.getBean(UserHolder.class).toString(),Thread.currentThread().getId());
//			});
//			System.out.println();
//			thread.start();
//			thread.join();
		}
		Thread.sleep(10000l);
		annotationConfigApplicationContext.close();
		Set<Map.Entry<String, Runnable>> entries = ThreadLocalScop.requestDestructionCallbacks.entrySet();
		for (Map.Entry<String, Runnable> entry : entries) {
			System.out.println(entry.getKey()+"   =====");
			entry.getValue().run();
		}

	}

}
