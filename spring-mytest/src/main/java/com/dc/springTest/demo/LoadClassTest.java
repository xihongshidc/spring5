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

		System.out.println(args[0]);
		while (true){

			//系统类加载器加载的 MyClassLoader
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
		/**
		 * 吞吐量: 一段时间内 请求和响应的数量.
		 * 并发数: 当前时刻,请求和响应的数量.
		 * 响应时间: 并发数增加的时候响应时间也会降低. ()
		 *
		 * jstat -option  <tid> interval count
		 */


	}
}
