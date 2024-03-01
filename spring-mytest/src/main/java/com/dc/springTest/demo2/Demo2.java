package com.dc.springTest.demo2;

import java.util.ArrayList;

/**
 * Description: jstat -gc -t <tid> interval count
 *  ou 列 老年代使用比例. 如果一段时间内这个值不断地在上升,那么 就说明存在内存泄漏.
 * Author: duancong
 * Date: 2024/1/17 17:06
 */
public class Demo2 {
	public static void main(String[] args) throws InterruptedException {
		String sysprops = System.getProperty("sysprop");
		System.out.println(sysprops);
		ArrayList<byte[]> bytes = new ArrayList<>();
		while (true){
			byte[] bytes1 = new byte[1024 * 200 * 512];
			bytes.add(bytes1);
			String s = new String();
			Thread.sleep(1000);
		}
	}
}
