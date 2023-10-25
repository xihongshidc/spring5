package com.dc.springTest;

import com.dc.springTest.annotation.Super;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Properties;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:36
 */
@Super
public class User implements BeanFactoryAware , ApplicationContextAware, InitializingBean ,
		DisposableBean, SmartInitializingSingleton, BeanNameAware {// 基于Aware 接口实现回调注入
	private String name;
	private String age;
	private String beanName;

	private Company company;

	public Company getCompany() {
		return company;
	}

	private Properties properties;

	private String astext;

	public String getAstext() {
		return astext;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age='" + age + '\'' +
				", beanName='" + beanName + '\'' +
				", company=" + company +
				", properties=" + properties +
				", astext='" + astext + '\'' +
				'}';
	}

	public void setAstext(String astext) {
		this.astext = astext;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用 beanFactory :" + beanFactory);
		this.beanFactory = beanFactory;
	}

	public String getBeanName() {
		return beanName;
	}

	@Override
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
		this.applicationContext = applicationContext;

	}


	//执行顺序是 PostConstruct  ->  InitializingBean  ->  initmethod(自定义方法)
	@PostConstruct
	public void init(){
		System.out.println("@PostConstruct 注解 :::  初始化");
	}

	public void initdemo(){
		System.out.println("initmethod  :::  初始化 =" + beanName);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("name + age :  " + this.beanName +" -"+ this.age);
		System.out.println("afterPropertiesSet ::: 初始化");
	}

	@PreDestroy
	public void preDestory(){
		System.out.println("@PreDestroy :::  销毁中"+ beanName);
	}

	public void predes(){
		System.out.println("自定义销毁方法:::  销毁中");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean  :::  销毁中"+ beanName);
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		System.out.println( " Gc " );
	}

	//这个方法需要显示的被调用，通常是ApplicationContext 会在容器初始化之后显示调用。。
	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("afterSingletonsInstantiated ==== " + beanName);
	}

	public static class Company{
		private String company;

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		@Override
		public String toString() {
			return "Company{" +
					"company='" + company + '\'' +
					'}';
		}
	}
}
