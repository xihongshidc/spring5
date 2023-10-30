package com.dc.springTest.metadatascan;

import com.dc.springTest.annotation.EnableHellworld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/29 13:37
 */
@EnableHellworld
public class EnableDemo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(EnableDemo.class);
		annotationConfigApplicationContext.refresh();
		String bean = annotationConfigApplicationContext.getBean(String.class);
		System.out.println(bean);
	}
}
