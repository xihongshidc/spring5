package com.dc.springTest.dependencysource.bean;

import com.dc.springTest.injection.UserHolder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;

/**
 * Description: 单例和多例
 * Author: duancong
 * Date: 2023/10/12 18:29
 */
public class BeanScopeDemo {
	/**
	 * 结论
	 */
	//一、
	//singleton Bean 无论依赖查找还是依赖注入,都是同一个对象
	//prototype Bean 无论依赖查找还是依赖注入,都是新生成一个对象

	//二、
	//对于集合类型的依赖注入， 会注入所有的单例bean ，以及多例bean ，只不过这个多例bean 会重新生成。

	//三、
	//单例bean 会具有所有的spring bean 生命周期， 但是多例bean 不是 ， 多例bean 只具备初始化生命周期，不具备销毁方法回调
	// 如果需要显示的销毁多例bean 那么就需要，在主单例bean 中显示调用回调方法。。

	//四、
	// request 作用域， 是web请求的作用域，每次请求都会创建一个对象，然后请求结束对象销毁。（具备生命周期。）

	//五、
	// session 作用域， 是web请求的作用域，在同一会话中，每次请求获取到的对象是一样的.比如说再同一个页面请求(他们具备的session id 是一样的.)。 没有销毁bean的方法.
	//六、
	// application 作用域， 一个应用启动的时候 过程中间会保存 该作用域的对象..

	//七、
	// 自定义 Thread-Local 级别作用域，每个线程启动的时候会创建一个该作用域的对象... (比如 SimpleDateFormat 是线程不安全的, 多线程下每次去创建一个对象有一些不合适,
	// 可以利用线程作用域对象来创建对象保证每次创建的对象可以控制. )


	@Autowired//会根据类型进行匹配
	private UserHolder singleton;

	@Autowired//会根据类型进行匹配
	@Qualifier("singleton")
	private UserHolder singleton1;

	@Autowired//会根据类型进行匹配
	private UserHolder prototype;

	@Autowired
	@Qualifier("prototype")//会根据类型进行匹配
	private UserHolder prototype1;

	@Autowired
	private ConfigurableListableBeanFactory beanFactory;

	@Autowired
	private java.util.Map <String,UserHolder> map;
	//根据名称注入对象, 多例对象也会注入, 但是他会新创建一个对象, 所以多例对象会创建多个...

	public static void main(String[] args) {
//		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(BeanScopeDemo.class);
		annotationConfigApplicationContext.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
				@Override
				public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
					System.out.format("bean 类型：  %s  ,名字 %s  %n",bean.getClass().getName(),beanName);
					return null;
				}
			});
		});
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		BeanScopeDemo bean = annotationConfigApplicationContext.getBean(BeanScopeDemo.class);
		System.out.println("==========================");
		System.out.println("依赖注入 单例 : "+bean.singleton);
		System.out.println("依赖注入 单例 : "+bean.singleton1);
		System.out.println("依赖注入 多例 : "+bean.prototype);
		System.out.println("依赖注入 多例 : "+bean.prototype1);
		System.out.println("依赖注入 多例 : "+bean.map);
		System.out.println("==========================");
		System.out.println(bean);
		annotationConfigApplicationContext.close();

	}

	//定义的顺序就是注入的顺序,
	@Bean
	public UserHolder singleton(){
		return getUserHolder();
	}

	@NotNull
	private UserHolder getUserHolder() {
		UserHolder userHolder = new UserHolder();
		userHolder.setId(System.nanoTime());
		return userHolder;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public UserHolder prototype(){
		return getUserHolder();
	}

	@PreDestroy
	public void preDestory(){
		System.out.println("开始销毁多例 bean == ");
		prototype.testPreDestroy();
		prototype1.testPreDestroy();
		for (String s : map.keySet()) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(s);
			if (beanDefinition.isPrototype()){
				map.get(s).testPreDestroy();
			}
		}
		System.out.println("销毁bean 已完成 ");
	}
}
