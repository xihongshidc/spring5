package com.dc.springTest.aop.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: duancong
 * Date: 2024/1/4 17:02
 */
@EnableAspectJAutoProxy
@Aspect
@Component
public class AspectDemo {

	@Pointcut("execution(public * *(..))")
	public void demo(){

	}

	@Before("demo()")
	public void befor(){
		System.out.println("befor");
	}


	@After("demo()")
	public void After(){
		System.out.println("After");
	}




}
