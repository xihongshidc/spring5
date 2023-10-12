package com.dc.springTest.injection;

import com.dc.springTest.User;
import com.dc.springTest.annotation.InjectUser;
import com.dc.springTest.annotation.MyAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * Description: 延迟注入可以实现注入一些非必要的依赖.
 * Author: duancong
 * Date: 2023/10/10 10:45
 */
public class AutowiredAnnotationDefined {

//	@Autowired//会根据类型进行匹配
//	@Lazy//加了Lazy 注解实际注入的是一个代理对象.
//	private UserHolder userHolder3;

//	@MyAutowired
//	private Optional<UserHolder> users4;
	//如果存在多个该类型的Bean ，并且没有声明Primary ,和 Priority优先级 ，那么就通过userHolder2 进行注入
	@InjectUser
	private Optional<UserHolder> userHolder2;

//	@Inject // Inject 和 Autowired 进行注入.
//	private Optional<UserHolder> users3;

//	//扩展 创建了一个新的AutowiredAnnotationBeanPostProcessor .
//	@Bean
//	public static AutowiredAnnotationBeanPostProcessor getBeanPostprocessor2(){
//		AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//		autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
//		autowiredAnnotationBeanPostProcessor.setOrder(Ordered.LOWEST_PRECEDENCE - 3);
//		return autowiredAnnotationBeanPostProcessor;
//	}

	//扩展 , 因为bean 复用了系统内建的名字,所以在注入的时候会 ,只会注入下面这个bean.
	//static  可以让bean 提前进行初始化, 脱离了类 的限制.
	@Bean(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
	public static AutowiredAnnotationBeanPostProcessor getBeanPostprocessor(){
		AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//		InjectUser.class,Autowired.class,Inject.class 注解
		//可能存在  Inject.class 不存在。
		Set<Class<?extends Annotation>> set =new LinkedHashSet<>(Arrays.asList(InjectUser.class,Autowired.class,Inject.class));
		autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(set);
		autowiredAnnotationBeanPostProcessor.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
		return autowiredAnnotationBeanPostProcessor;
	}

	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(AutowiredAnnotationDefined.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		AutowiredAnnotationDefined bean = annotationConfigApplicationContext.getBean(AutowiredAnnotationDefined.class);
		System.out.println("==========================");
//		System.out.println("自定义 : "+bean.users4);
		System.out.println("自定义 : "+bean.userHolder2);
		System.out.println("==========================");


	}

	//定义的顺序就是注入的顺序,
	@Bean
	public UserHolder userHolder2(User user){
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);//set注入
		userHolder.setId(1l);
		return userHolder;
	}

	@Bean
//	@Primary
	public UserHolder userHolder1(User user){
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);//set注入
		userHolder.setId(2l);
		return userHolder;
	}
}
