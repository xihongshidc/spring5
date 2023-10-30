package com.dc.springTest.metadatascan;

import com.dc.springTest.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Description: spring 占位符处理
 * Author: duancong
 * Date: 2023/10/30 17:58
 *
 * @see PropertyPlaceholderConfigurer
 * @see PropertySourcesPlaceholderConfigurer
 */
public class PropertySourcesPlaceholderDemo {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/property-placeholder.xml");
		User bean = classPathXmlApplicationContext.getBean(User.class);
		System.out.println(bean);


	}
}
