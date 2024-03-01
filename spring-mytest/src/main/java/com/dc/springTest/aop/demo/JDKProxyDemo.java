package com.dc.springTest.aop.demo;

import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 动态代理 也会生一个.
 * Description: jdk 动态代理, 基于接口进行实现类然后对接口定义的方法重新声明 ,
 * 由于默认继承Proxy 类,保存了一个InvocationHandler 对象里面存储的是目标对象及
 * Author: duancong
 * Date: 2023/11/16 15:46
 */
public class JDKProxyDemo {
	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");  // --该设置用于输出jdk动态代理产生的类

		TargetBean targetBean = new TargetBean();
		TargetProxy targetProxy = new TargetProxy(targetBean);

		//JDK 动态代理
		B b = (B) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), TargetBean.class.getInterfaces(), targetProxy);
		b.show();
	}
}

class TargetProxy implements InvocationHandler{

	public Object target;

	public TargetProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("before");
		Object invoke = method.invoke(target, args);

		System.out.println("after");
		return  invoke;
	}
}
