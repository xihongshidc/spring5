package com.dc.springTest.dependencysource.reource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/21 13:45
 */
public class InjectResourceDemo {

	@Value("classpath:/META-INF/user.yaml")
	private Resource defaultPropertiesResource;

	@Value("classpath*:/META-INF/*.properties")
	private Resource[] resources;

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册当前类. 为配置类
		annotationConfigApplicationContext.register(InjectResourceDemo.class);
		annotationConfigApplicationContext.refresh();
		InjectResourceDemo bean = annotationConfigApplicationContext.getBean(InjectResourceDemo.class);
//		System.out.println(ResourceUtils.toString(bean.defaultPropertiesResource));
		final Properties properties = new Properties();

		for (Resource resource : bean.resources) {
			EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
			InputStreamReader reader = (InputStreamReader) encodedResource.getReader();
			properties.load(reader);
//			final String s = IOUtils.toString(reader);
//			System.out.println(s);
			System.out.println(properties);

		}
	}
}
