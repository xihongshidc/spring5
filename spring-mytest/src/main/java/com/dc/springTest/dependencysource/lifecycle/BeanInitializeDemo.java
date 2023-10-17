package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Description: 初始化前置阶段 ->初始化阶段-> 初始化后置阶段
 * {@link AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)}
 * 在前置阶段 会执行@PostConstrut  （需要添加一个扩展的CommonAnnotationBeanPostProcessor类）
 * 初始化阶段 执行初始化方法，执行 invokeInitMethods -> {@link InitializingBean#afterPropertiesSet()}
 * 之后会调用自定义的初始化方法，{@link AbstractAutowireCapableBeanFactory#invokeCustomInitMethod(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)}
 *
 * Author: duancong
 * Date: 2023/10/16 14:39
 */
public class BeanInitializeDemo {

	public static void main(String[] args) {
		//既是一个容器也是一个bean定义注册 实现类
		//创建默认的bean容器
		executerdefaultBeanFactory();
	}

	private static void executerdefaultBeanFactory() {
		DefaultListableBeanFactory defaultListableBeanFactory= new DefaultListableBeanFactory();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BeanInitializeDemo.class);
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		defaultListableBeanFactory.registerBeanDefinition("beanInstanceDemo",beanDefinition);
		defaultListableBeanFactory.addBeanPostProcessor(new MyBeanInstancePostprocessor());
		defaultListableBeanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		defaultListableBeanFactory.preInstantiateSingletons();  //  提前实例化->初始化
		//可以通过

		User bean = defaultListableBeanFactory.getBean("user1",User.class);

		System.out.println(bean);
	}


}
