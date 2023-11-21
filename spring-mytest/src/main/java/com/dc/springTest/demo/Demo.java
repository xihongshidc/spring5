package com.dc.springTest.demo;

import com.dc.springTest.SuperUser;
import com.dc.springTest.Test;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/20 10:02
 */
public class Demo {
	public static void main(String[] args) throws ClassNotFoundException {

//		User user = new SuperUser(new GenericXmlApplicationContext());
		//创建子类对象的时候默认会加载父类对象的空参构造方法.
		SuperUser superUser = new SuperUser();

//		System.out.println(user);
		String a = new String("1");
		String intern = a.intern();
		String a1 = "1";
		System.out.println(intern == a1);
		System.out.println(a == intern);

		Class<?> aClass = Class.forName("com.dc.springTest.Test");
		Class<?> aClass1 = Class.forName("com.dc.springTest.Test");
		Test test = new Test();
		Class<? extends Test> aClass2 = test.getClass();
		System.out.println(aClass == aClass2);
		System.out.println(aClass == aClass1);

	}
}
