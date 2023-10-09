package com.dc.springTest.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Description: 构造器 自动注入
 * Author: duancong
 * Date: 2023/10/9 14:44
 */
public class AutowireConstructorDependenceInject {
	public static void main(String[] args) {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/autowire-constructor-dependence-injection.xml");
		//依赖查找。
		UserHolder bean = defaultListableBeanFactory.getBean(UserHolder.class);
		System.out.println(bean);

	}
}
