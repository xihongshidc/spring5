package com.dc.springTest;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/20 10:02
 */
public class Demo {
	public static void main(String[] args) throws ClassNotFoundException {

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
