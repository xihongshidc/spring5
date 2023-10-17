package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.SuperUser;
import com.dc.springTest.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Description: 实例化前置阶段,{@link org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor}
 * 通过postProcessBeforeInstantiation  方法拦截beanClass,beanName 可以提前实例化对象, 生成代理对象, 提前返回.
 * Author: duancong
 * Date: 2023/10/16 14:39
 */
public class BeanInstanceDemo {

	public static void main(String[] args) {
		//既是一个容器也是一个bean定义注册 实现类
		//创建默认的bean容器
		DefaultListableBeanFactory defaultListableBeanFactory= new DefaultListableBeanFactory();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BeanInstanceDemo.class);
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

		defaultListableBeanFactory.destroySingletons();
//		User bean1 = defaultListableBeanFactory.getBean("user1",SuperUser.class);
//		System.out.println(bean1);
//		System.out.println(bean);

	}
	static class MyBeanInstancePostprocessor implements InstantiationAwareBeanPostProcessor {
		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals(beanName,"user1")){
				//非正常流程
//				return new SuperUser();
			}
			//执行正常的 bean 实例化, 初始化,流程.
			return null;
		}

		//可以完成 自定义属性注入,而不是自动配置配置的Beandefinition
		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals(beanName,"user1")){
				SuperUser sp= (SuperUser) bean;
				sp.setAge("2");
				sp.setName("dcc");

				return true;
			}
			return true;
		}

		@Override
		public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
			if (!ObjectUtils.nullSafeEquals(beanName, "user1")) {
				return null;
			}
			if (pvs instanceof MutablePropertyValues) {
				MutablePropertyValues pvs1 = (MutablePropertyValues) pvs;
				if (pvs1.contains("age")) {
					PropertyValue age = pvs1.getPropertyValue("age");
					age.setConvertedValue("30");
				}
				if (pvs1.contains("name")) {
					PropertyValue age = pvs1.getPropertyValue("name");
					age.setConvertedValue("dac");
				}
			}
			return pvs;
		}
	}
}
