package com.dc.springTest.demo;

import java.util.HashMap;

/**
 * Description: 静态代码快的加载时机
 * Author: duancong
 * Date: 2023/11/21 16:17
 */
public class StaticClassTest {
	{
		System.out.println("2");
	}

	static {
		System.out.println("1");
	}

	public static void tes(){
		System.out.println(3);
	}

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//		Class<?> aClass = Class.forName("com.dc.springTest.StaticClassTest");
//		StaticClassTest staticClassTest = new StaticClassTest();
//
//		staticClassTest.tes();
//		staticClassTest.tes();
//		staticClassTest.tes();
//		staticClassTest.tes();
//		staticClassTest.tes();
//		Object o = aClass.newInstance();
		HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
		objectObjectHashMap.put("1","2*");
		System.out.println(objectObjectHashMap.values());
		StaticClassTest.tes();


	}
}
