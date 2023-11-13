package com.dc.springTest.metadatascan.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/3 11:51
 */
public class LifeCycleDemo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(MyLifeCycleProcessor.class);
		annotationConfigApplicationContext.refresh();

//		annotationConfigApplicationContext.start();
//		annotationConfigApplicationContext.stop();
		annotationConfigApplicationContext.close();
	}
}
