package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Description: 基于properties 进行配置bean定义.
 * Author: duancong
 * Date: 2023/10/15 14:20
 */
public class BeanMetadataConfigurationDemo {
	public static void main(String[] args) {

		//既是一个容器也是一个bean定义注册 实现类
		//创建默认的bean容器
		DefaultListableBeanFactory defaultListableBeanFactory= new DefaultListableBeanFactory();

		String localtion ="META-INF/user.properties";

		//可以通过
		Resource resource = new ClassPathResource(localtion);
		EncodedResource encodedResource= new EncodedResource(resource,"utf-8");

		//Properties 配置bean
		PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(defaultListableBeanFactory);
		propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);

		User bean = defaultListableBeanFactory.getBean(User.class);
		System.out.println(bean);


	}
}
