package com.dc.springTest.aop.demo;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.target.SingletonTargetSource;

/**
 * Description: TargetSource 组件本身与 SpringIoC 容器无关，换句话说，target 的生命周期不一定是受 spring 容器管理的，
 * 我们以往的 XML 中的 AOP 配置，只是对受容器管理的 bean 而言的，我们当然可以手动创建一个 target，同时使用 Spring 的 AOP 框架（而不使用 IoC 容器）
 * {@link TargetSource 脱离了spring的限制, 仍然能使用aop}
 * Author: duancong
 * Date: 2023/11/15 18:46
 */
public class AOPDemo {
	public static void main(String[] args) {
//		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\spring-study\\spring");

		TargetBean targetBean = new TargetBean();
		// 只是一个代理目标对象的类.
		SingletonTargetSource singletonTargetSource = new SingletonTargetSource(targetBean);
		//使用springaop 代理工厂手动创建代理对象 ，。。  CGLIB  动态代理
		TargetBean proxy = (TargetBean)ProxyFactory.getProxy(singletonTargetSource);

		proxy.show();


	}
}

class TargetBean implements B{
	public void show() {
		System.out.println("show");
	}
}
