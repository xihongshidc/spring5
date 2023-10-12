package com.dc.springTest;

import com.dc.springTest.annotation.Super;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:36
 */
@Super
public class User implements BeanFactoryAware , ApplicationContextAware, InitializingBean , DisposableBean {// 基于Aware 接口实现回调注入
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

	//静态工厂方法
	public static User create(){

		User user = new User();
		user.setAge("12");
		user.setName("dacong");
		return user;
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

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("调用 applicationContext :" + applicationContext);
		this.applicationContext=applicationContext;

	}


	//执行顺序是 PostConstruct  ->  InitializingBean  ->  initmethod(自定义方法)
	@PostConstruct
	public void init(){
		System.out.println("@PostConstruct 注解 :::  初始化");
	}

	public void initdemo(){
		System.out.println("initmethod  :::  初始化");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("name + age :  "+this.name + this.age);
		System.out.println("InitializingBean ::: 初始化");
	}

	@PreDestroy
	public void preDestory(){
		System.out.println("@PreDestroy :::  销毁中");
	}

	public void predes(){
		System.out.println("自定义销毁方法:::  销毁中");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean  :::  销毁中");
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		System.out.println( " Gc " );
	}
}
