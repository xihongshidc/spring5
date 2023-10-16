package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.SuperUser;
import com.dc.springTest.User;
import com.dc.springTest.injection.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * Description: 实例化前置阶段,{@link InstantiationAwareBeanPostProcessor}
 * 通过postProcessBeforeInstantiation  方法拦截beanClass,beanName 可以提前实例化对象, 生成代理对象, 提前返回.
 * Author: duancong
 * Date: 2023/10/16 14:39
 */
public class BeanInstanceLifeCycleDemo {

	public static void main(String[] args) {
		//既是一个容器也是一个bean定义注册 实现类
		//创建默认的bean容器
		DefaultListableBeanFactory defaultListableBeanFactory= new DefaultListableBeanFactory();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BeanInstanceLifeCycleDemo.class);
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		defaultListableBeanFactory.registerBeanDefinition("beanInstanceDemo",beanDefinition);
		//可以通过
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml","META-INF/autowire-constructor-dependence-injection.xml");
		Map<String, User> beansOfType = defaultListableBeanFactory.getBeansOfType(User.class);
//		User bean = defaultListableBeanFactory.getBean("user",User.class);//无参注入
//		User bean1 = defaultListableBeanFactory.getBean("user1",SuperUser.class);
		UserHolder bean2 = defaultListableBeanFactory.getBean("userHolder",UserHolder.class);//构造器注入

		System.out.println(bean2);
//		System.out.println(bean1);
	}
}
