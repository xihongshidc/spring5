package com.dc.springTest.injection;

import com.dc.springTest.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/9 15:19
 */
public class UserHolder implements BeanNameAware{
	private User user;
	private Long id;
	private String beanName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserHolder() {
	}

	public UserHolder(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				", id=" + id +
				'}';
	}

	public UserHolder(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@PostConstruct
	public void testPostConstruct(){
		System.out.println("testlife :  "+beanName + id);
	}

	@PreDestroy
	public void testPreDestroy(){
		System.out.println("testPreDestroy: " + beanName + id);
	}

//	@Override
//	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("初始化后置 。。。处理" + beanName);
//		return bean;
//	}

	//Aware 接口回调时在属性注入之后 （包括Autowired 等注解处理），初始化前
	@Override
	public void setBeanName(String name) {
		this.beanName=name;
	}
}
