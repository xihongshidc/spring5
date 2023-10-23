package com.dc.springTest.dependencysource.reource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/21 13:45
 */
public class InjectResourceLoaderDemo implements ResourceLoaderAware {

	@Autowired
	private ResourceLoader autowireresourceLoader;


	private ResourceLoader resourceLoader;

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册当前类. 为配置类
		annotationConfigApplicationContext.register(InjectResourceLoaderDemo.class);
		annotationConfigApplicationContext.refresh();
		InjectResourceLoaderDemo bean = annotationConfigApplicationContext.getBean(InjectResourceLoaderDemo.class);
//		System.out.println(ResourceUtils.toString(bean.defaultPropertiesResource));
		System.out.println((bean.resourceLoader == bean.autowireresourceLoader));


	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader=resourceLoader;
	}
}
