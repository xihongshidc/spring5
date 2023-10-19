package com.dc.springTest.dependencysource.configuration;

import com.dc.springTest.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Description: 基于properties 进行配置bean定义.
 * Author: duancong
 * Date: 2023/10/19
 */
public class BeanConfigurationPropertiesDemo {
	public static void main(String[] args) {

		//既是一个容器也是一个bean定义注册 实现类
		//创建默认的bean容器
		DefaultListableBeanFactory defaultListableBeanFactory= new DefaultListableBeanFactory();

		String localtion ="META-INF/user.properties";

		//可以通过 EncodedResource转换编码
		Resource resource = new ClassPathResource(localtion);
		EncodedResource encodedResource= new EncodedResource(resource);

		//Properties 配置bean
		PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(defaultListableBeanFactory);
		propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);

		User bean = defaultListableBeanFactory.getBean("user",User.class);
		System.out.println(bean);


	}
}
