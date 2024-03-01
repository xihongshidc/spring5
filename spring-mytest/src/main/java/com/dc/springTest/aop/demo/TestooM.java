package com.dc.springTest.aop.demo;

import org.springframework.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Description:
 *
 *
 *
 * -Xss512K  线程栈（虚拟机栈，线程私有,对于hotspot来说也是 本地方法栈 默认大小为1MB） 大小为 512K
 * 如果线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverflowError异常；
 * 如果Java虚拟机栈容量可以动态扩展，当栈扩展时无法申请到足够的内存会抛出OutOfMemoryError异常
 *
 * CGLIB 动态创建代理类，
 * Author: duancong
 * Date: 2024/2/26 16:09
 */
public class TestooM {
	public static void main(String[] args) {
		//test(); // GC overhead limit exceeded 98% 的GC, 回收了仅 2% 的内存,

		int i1 = 160 * 4;
		int i2 = 580555 * 4 / 1024 /1024;
		System.out.println(i2);
		System.out.println(i1);
		int i3 = 16384 / 1024;
		System.out.println(i3);
		int i = 635 / 4;
		System.out.println(i);
//		test2(); //Java heap space
	}

	private static void test() {
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			while (true) {
				arrayList.add(UUID.randomUUID().toString());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}

	}

	private static void test2() {
		String a = "";
		try {
			while (true) {
				Enhancer enhancer =new Enhancer();
				a += UUID.randomUUID().toString();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}

	}
}
