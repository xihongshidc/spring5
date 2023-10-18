package com.dc.springTest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/24 22:08
 */
public class SuperUser extends User {
	//自动注入的话是注入DefaultListableBeanFactory
	private BeanFactory beanFactory;

	private ObjectFactory<ApplicationContext> contextObjectFactory;

	public ObjectFactory<ApplicationContext> getContextObjectFactory() {
		return contextObjectFactory;
	}

	public void setContextObjectFactory(ObjectFactory<ApplicationContext> contextObjectFactory) {
		this.contextObjectFactory = contextObjectFactory;
	}

	@Override
	public String toString() {
		return "SuperUser{" +
				"beanFactory=" + beanFactory +
				", contextObjectFactory=" + contextObjectFactory +
				", user="+super.toString()+
				'}';
	}

	@Override
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	@PostConstruct
	public void init2(){
		System.out.println("@PostConstruct SuperUser init2 :::  初始化");
	}
	@PreDestroy
	public void preDestory1(){
		System.out.println("preDestory1 :::  销毁中"+(getBeanName()));
	}

}
