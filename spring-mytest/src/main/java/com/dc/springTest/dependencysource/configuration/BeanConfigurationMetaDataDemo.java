package com.dc.springTest.dependencysource.configuration;

import com.dc.springTest.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;
import org.springframework.core.io.support.EncodedResource;

/**
 * Description: xml 是通过 XmlBeanDefinitionReader 读取配置信息注册到ioc 容器的.
 * {@link XmlBeanDefinitionReader#loadBeanDefinitions(EncodedResource)}
 * Author: duancong
 * Date: 2023/10/18 12:22
 */
public class BeanConfigurationMetaDataDemo {
	public static void main(String[] args) {

		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		beanDefinitionBuilder.addPropertyValue("name", "zhangsan");
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		//这个作用就是一个bean定义的上下文。
		beanDefinition.setAttribute("name", "dc");
		//当前bean定义的来源.
		beanDefinition.setSource("api");

		defaultListableBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (ObjectUtils.nullSafeEquals(beanName, "user")) {
					BeanDefinition beanDefinition1 = defaultListableBeanFactory.getBeanDefinition(beanName);
					if (ObjectUtils.nullSafeEquals(beanDefinition1.getSource(), "api")) {
						//通过 上下文控制对象的属性
						String name = (String) beanDefinition1.getAttribute("name");
						User user = (User) bean;
						user.setName(name);
					}
				}
				return bean;
			}
		});
		defaultListableBeanFactory.registerBeanDefinition("user", beanDefinition);
		defaultListableBeanFactory.preInstantiateSingletons();

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		User user = defaultListableBeanFactory.getBean("user", User.class);
		System.out.println(user);

	}

}
