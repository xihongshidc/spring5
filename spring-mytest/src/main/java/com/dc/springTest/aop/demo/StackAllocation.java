package com.dc.springTest.aop.demo;

/**
 * Description: 开发中能使用局部变量，就不要使用在方法外定义。
 * -Xmx1000M -Xms1000M  -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 *
 * jvm对Eden 进一步划分, 为每一个线程划分一个线程私有缓冲区TLAB.
 * 堆空间都是线程共享的嘛？
 * 你可以回答堆空间总体来说是线程共享的，但是为了优化线程安全问题，
 * 堆空间的Eden区可以开辟TLAB区（默认也是开启的），TLAB是线程私有的，其优点也就是降低线程不安全的问题。(默认是开启的 分配在Eden ,占 Eden 1% 空间)
 *
 *
 * jvm 默认会将对象先分配再TLAB 上,只有TLAB 不够了再分配到Eden区 ,
 * Author: duancong
 * Date: 2024/2/28 15:49
 */
public class StackAllocation {
	public static void main(String[] args) throws InterruptedException {
		User2 user;
		long l = System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
			user = user();
//			user.hashCode(); //此时对象逃逸出方法，也就在堆上分配，
		}
		long l1 = System.currentTimeMillis();

		System.out.println(" 耗时 : " + (l1 - l) + "ms");

		Thread.sleep(100000);

	}

	public static User2 user() {
		//没逃逸出方法的对象， 会被分配在栈上 ,栈上分配的形式就是标量替换,本质上还是将成员变量放在了栈上.
		User2 user = new User2();

		return user;
	}

	static class User2 {

	}
}
