package com.dc.springTest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.util.HashSet;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:36
 */
public class User implements BeanFactoryAware , ApplicationContextAware {
	private String name;
	private String age;
	private String beanName;

	private BeanFactory beanFactory;

	private ClassLoader classLoader;

	private ApplicationContext applicationContext;

	public User(String name, String age) {
		this.name = name;
		this.age = age;
	}

	public User() {
	}
	public static void main(String[] args) {
		HashSet<String> es = new HashSet<>();
		es.add("ddd");
		es.add("adc");
		es.add("moed");
		es.add("emode");
		System.out.println(StringUtils.collectionToDelimitedString(es, ", "));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age='" + age + '\'' +
				'}';
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用 beanFactory :" + beanFactory);
		this.beanFactory=beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("调用 applicationContext :" + applicationContext);
		this.applicationContext=applicationContext;

	}
}
