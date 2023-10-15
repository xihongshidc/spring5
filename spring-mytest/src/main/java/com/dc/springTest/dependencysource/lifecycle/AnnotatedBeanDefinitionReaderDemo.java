package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/15 15:08
 */
public class AnnotatedBeanDefinitionReaderDemo {
	public static void main(String[] args) {
		//既是一个容器也是一个bean定义注册 实现类
		//创建默认的bean容器
		DefaultListableBeanFactory defaultListableBeanFactory= new DefaultListableBeanFactory();

		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(defaultListableBeanFactory);
		annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionReaderDemo.class);

		int beanDefinitionCount = defaultListableBeanFactory.getBeanDefinitionCount();
		System.out.println(beanDefinitionCount);

		AnnotatedBeanDefinitionReaderDemo bean = defaultListableBeanFactory.getBean("annotatedBeanDefinitionReaderDemo",AnnotatedBeanDefinitionReaderDemo.class);
		System.out.println(bean);

	}

}
