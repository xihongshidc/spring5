package com.dc.springTest.aop.demo;

/**
 * Description: 锁消除, -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -Xmx1000M -Xms1000M
 * //默认的热点代码 编译次数10000
 *
 * //方法被循环调用 的阈值默认是10700
 *
 * 达到阈值则触发即时编译。
 *
 * 热点代码触发即时编译阈值设置
 * -XX:CompileThreshold=10000
 * Author: duancong
 * Date: 2024/2/29 10:33
 */
public class SynchronizedDelete {
	public static void main(String[] args) throws InterruptedException {

		long l = System.currentTimeMillis();

		for (int i = 0; i < 200000; i++) {

			toSynchronized();
		}

		long l1 = System.currentTimeMillis();

		System.out.println("锁消耗时间 " +(l1-l) + "ms");


		// -XX:CompileThreshold
		Thread.sleep(11111111);
	}

	private static void toSynchronized() {
		StackAllocation.User2 o = new StackAllocation.User2();

		synchronized (o){
			int i = 1;
//			System.out.println("1222");
		}
		/*
		无法保证非逃逸分析的性能消耗一定高于逃逸分析的消耗， 虽然经过逃逸分析可以实现标量替换，栈上分配，锁消除，但是逃逸分析自身也是需要进行一系列复杂的分析，

		极端情况下如果对象没有一个是不逃逸的 哪这个逃逸分析就白白浪费掉了。

		理论上经过逃逸分析，没有逃逸出方法的对象被分配在栈上，但是这取决与虚拟机设计者， hotspot 虚拟机并没有实现对象栈上分配，转而用标量替换，代替了栈上分配对象，

		结论对象是在堆上分配的。
		 */
	}
}
