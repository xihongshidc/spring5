package com.dc.springTest.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description:
 * Author: duancong
 * Date: 2024/1/16 16:25
 */
public class LoadClassTest {
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

		while (true){

			MyClassLoader myClassLoader = new MyClassLoader("D:\\spring-study\\spring\\spring-mytest\\src\\main\\java\\com\\dc\\springTest\\demo");
			ClassLoader parent = myClassLoader.getParent();
			System.out.println(parent);

			Class<?> aClass = myClassLoader.loadClass("TestHotClass");

			System.out.println(aClass.getClassLoader());
			System.out.println(aClass.getClassLoader().getParent().getClass().getName());
			Object o = aClass.newInstance();
			System.out.println(o);
			Method test = aClass.getMethod("test");
			test.invoke(o);
			Thread.sleep(5000);
		}


	}
}
