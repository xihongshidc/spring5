package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.SuperUser;
import com.dc.springTest.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.Map;

/**
 * Description:  属性注入后，会执行 aware 接口的回调方法， 通过接口实现注入特定的属性，BeanFactoryAware ，BeanClassLoaderAware， BeanNameAware，（基础的BeanFactory 提供的方法。）
 * {@link AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)}
 * 高级容器  ApplicationContext 扩展了一个 ApplicationContextAwareProcessor 内置的类， 在初始化前实现aware 接口注入，
 * {@link BeanPostProcessor#postProcessBeforeInitialization(Object, String)}
 * Author: duancong
 * Date: 2023/10/16 14:39
 */
public class BeanInstancePostAwareDemo {

	public static void main(String[] args) {
		//既是一个容器也是一个bean定义注册 实现类
		//创建默认的bean容器
//		executerdefaultBeanFactory();
		System.out.println("**********************");
		executerApplicationContext();
	}

	private static void executerdefaultBeanFactory() {
		DefaultListableBeanFactory defaultListableBeanFactory= new DefaultListableBeanFactory();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BeanInstancePostAwareDemo.class);
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		defaultListableBeanFactory.registerBeanDefinition("beanInstanceDemo",beanDefinition);
		defaultListableBeanFactory.addBeanPostProcessor(new MyBeanInstancePostprocessor());
		//可以通过
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		Map<String, User> beansOfType = defaultListableBeanFactory.getBeansOfType(User.class);
		for (String s : beansOfType.keySet()) {
			System.out.println("beanName  : " + s + " ===" +  beansOfType.get(s));
		}
		User bean = defaultListableBeanFactory.getBean("user",User.class);
//		User bean1 = defaultListableBeanFactory.getBean("user1",SuperUser.class);
//		System.out.println(bean1);
		System.out.println(bean);
	}

	private static void executerApplicationContext() {

		ClassPathXmlApplicationContext classPathXmlApplicationContext= new ClassPathXmlApplicationContext();
//		classPathXmlApplicationContext.addBeanFactoryPostProcessor(beanFactory -> {
//			beanFactory.addBeanPostProcessor(new MyBeanInstancePostprocessor());
//		});
		classPathXmlApplicationContext.setConfigLocation("META-INF/annnotation-dependence-injection.xml");
		classPathXmlApplicationContext.refresh();

		Map<String, User> beansOfType = classPathXmlApplicationContext.getBeansOfType(User.class);
		for (String s : beansOfType.keySet()) {
			System.out.println("beanName  : " + s + " ===" +  beansOfType.get(s));
		}
		User bean = classPathXmlApplicationContext.getBean("user",User.class);
		System.out.println(bean);
		classPathXmlApplicationContext.close();
	}

}
