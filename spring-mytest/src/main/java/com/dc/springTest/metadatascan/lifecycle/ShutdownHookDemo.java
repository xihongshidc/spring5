package com.dc.springTest.metadatascan.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/3 11:51
 */
public class ShutdownHookDemo {
	public static void main(String[] args) throws IOException {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(MyLifeCycleProcessor.class);
		annotationConfigApplicationContext.refresh();
		// 注册钩子 关闭方法
		annotationConfigApplicationContext.registerShutdownHook();
		System.out.println(" ========== ");
		System.in.read();
//		annotationConfigApplicationContext.start();
//		annotationConfigApplicationContext.stop();
//		annotationConfigApplicationContext.close();
	}
}
