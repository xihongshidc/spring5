package com.dc.springTest.demo;

import org.springframework.util.ClassUtils;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/24 16:50
 */
public class Demo1124 {
	public static void main(String[] args) {
		System.out.println(Demo1124.class.getSimpleName());
		System.out.println(ClassUtils.getShortName(Demo1124.class));

		System.out.println(Child.class.getSimpleName());
		System.out.println(ClassUtils.getShortNameAsProperty(Child.class));
		System.out.println(StaticClassTest.getInstance());
		System.out.println(StaticClassTest.getInstance());
		System.out.println(StaticClassTest.getInstance());
	}
}
