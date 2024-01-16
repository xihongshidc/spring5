package com.dc.springTest.demo;

import java.io.IOException;

/**
 * Description:
 * 主动使用会调用clinit:
 * 反射 new对象 ,或者序列化,反序列化第一次调用的时候都会执行static 静态初始化块..
 * 调用static 静态方法的时候会执行一次静态代码块的代码, 只在第一次调用static 方法的时候才会执行.
 * 调用静态 非final 修饰的字段.
 *
 * 初始化子类的时候会检查父类是否初始化，如果没有那么会先初始化父类。但是对于其父接口 却没有这个要求。。
 * 如果要初始化一个接口时，并不会先初始化他的父接口。
 * 如果接口提供了了default 方法，那么会直接实现或者间接实现该接口的类初始化，那么会先初始化这个接口、
 *
 * 被动使用: 不会直接调用clinit
 * 一般像引用常量， final static 修饰的基本类型的数据的时候。  就不会进行初始化类。因为在准备阶段就已经完成了。
 * 当通过子类引用父类的静态变量不会导致子类初始化。。
 *
 * ClassLoader 类的loadClass（） 方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 *
 * 显示类加载: Class.forName(""); ClassLoader.loadClass()
 * 隐式:new 对象 ,访问类的静态属性, 静态方法.
 *
 * 双亲委派:优点确保了类加载的唯一性,
 *
 * 破坏双亲委派机制.
 * 1.继承classloader 重写loadclass 方法
 * 2.线程上下文的应用类加载器 , 可以为启动类加载器加载用户自定义的类.
 * 3.模块热部署, 代码热替换
 *
 *
 * 代码热替换  : {
 *     创建一个自定义的Classloader 实例 -> 加载需要替换的类 -> (类发生变化,)  创建新的ClassLoader实例,然后加载
 * }
 *
 * Author: duancong
 *
 * Date: 2024/1/7 22:01
 */
public class ActiveUse {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ActiveUse[] ar =new ActiveUse[10];

		System.out.println(ClassLoader.getSystemClassLoader());
//		System.out.println(ClassLoader.getSystemClassLoader().toString());
//		ar[0]=new ActiveUse();
//		ar[1]=new ActiveUse();
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("sd.txt"));
//		objectOutputStream.writeObject(new ActiveUse());

//		ActiveUse.method();
		ActiveUse.method();
		ActiveUse.method();
//		ActiveUse.method();
//		ActiveUse.method();

//		System.out.println(ActiveUse.a);
//		ActiveUse activeUse = new ActiveUse();
	}
	{
		//普通的初始化只在使用的时候去执行.
		System.out.println("dsfdsf");
	}

	static int a= 12;
	//<clinit> 只会执行一次。
	static {

		System.out.println("123");
	}

	public static void method(){
		System.out.println("static");
	}


}
