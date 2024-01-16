package com.dc.springTest.demo;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import javax.crypto.spec.DESedeKeySpec;
import java.net.URL;

/**
 * Description:
 * Author: duancong
 * Date: 2024/1/11 17:04
 */
public class ClassloadDemo {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		//Bootstrap启动类加载器加载的路径.
		for (URL url : Launcher.getBootstrapClassPath().getURLs()) {

			System.out.println(url);

		}
		//启动类加载器 严格意义上是有 c 语言 c++ 进行加载的, 对于java来说就是null.
		System.out.println(DESedeKeySpec.class.getClassLoader());
		String property = System.getProperty("java.ext.dirs");
		String[] split = property.split(";");
		for (String s : split) {
			System.out.println(" ext_url: " + s);
		}
		//扩展类加载器:
		System.out.println(CurveDB.class.getClassLoader());

		//系统类加载器,
		System.out.println(ClassLoader.getSystemClassLoader());

		//也可以自定义类加载器，默认也是继承(包含)应用类加载器。
		Thread thread = new Thread();


		StringTest[] a = {};
		//类加载器加载数组的类型。  运行时动态创建数组的结构.
		System.out.println(a.getClass().getClassLoader());   //系统类加载器
		String[] ar ={};
		System.out.println(ar.getClass().getClassLoader());  // 引导类加载器。
		int[] ints ={1,2};
		System.out.println(ints.getClass().getClassLoader()); //基本数据类型没有加载器, 虚拟机预先定义了. 不需要类的加载.

		Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass("com.dc.springTest.annotation.Test2"); //加载类不初始化.指导使用的时候才初始化
		System.out.println(aClass);
		Object o = aClass.newInstance();
		System.out.println(o);
//		Class<?> aClass1 = Class.forName("com.dc.springTest.annotation.Test2"); // 加载类并初始化
//		System.out.println(aClass1 == aClass);



	}
}
