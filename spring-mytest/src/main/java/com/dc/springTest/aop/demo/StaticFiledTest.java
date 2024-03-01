package com.dc.springTest.aop.demo;

/**
 * Description:
 *
 * 纯解释器： -Xint
 * 完全采用即时编译器 ，如果即时编译出现问题，那么解释器会介入： -Xcomp
 * 混合模式： 解释+编译器 -Xmixed
 *
 * jdk 8:
 * -Xms300m -Xmx300m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 *
 * -Xmixed：默认为混合模式，开始解释执行，启动速度较快，对热点代码实行检测和编译
 * -Xint：使用纯解释模式，启动很快，执行稍慢
 * -Xcomp：使用纯编译模式，执行很快，启动很慢
 * -XX:CompileThreadhold = 10000 ：检测热点代码阀值
 *
 *
 * 静态变量在元空间,但是其引用的对象占用的内存在堆空间
 * eden ：from ：to = 8：1：1  Parallel Scavenge 是一个自适应调节策略 ，-XX：MaxGCPauseMillis 设置垃圾收集最大停顿时间，单位是毫秒
 * 新生代采用的是标记复制，老年代采用标记整理
 * 并行垃圾回收器是吞吐量优先。
 *
 * 类装载和卸载
 * -XX:+TraceClassLoading -XX:+TraceClassUnloading
 *
 * Author: duancong
 * Date: 2024/2/20 16:20
 */
public class StaticFiledTest {

	private final static byte[] arr = new byte[1024 * 1024 * 100]; //100MB

	private final static String ar = "!2";

	public static void main(String[] args) throws InterruptedException {

		long x = System.currentTimeMillis();
//		System.out.println(x);
		for (int i = 0; i < 1000; i++) {

			String ar= "!2"+i;
//			byte[] arr = new byte[1024 * 1024 * 100];

		}
		System.out.println(StaticFiledTest.arr);
		long x2 = System.currentTimeMillis();
		System.out.println(x2);
		System.out.println(x2 - x);
//		System.out.println(StaticFiledTest.ar);

		//Thread.sleep(10000000);
	}
}
