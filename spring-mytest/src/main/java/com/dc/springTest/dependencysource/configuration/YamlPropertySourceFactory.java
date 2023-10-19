package com.dc.springTest.dependencysource.configuration;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/19 18:29
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
		yamlMapFactoryBean.setResources(resource.getResource());
		Map<String, Object> object = yamlMapFactoryBean.getObject();
		PropertySource propertySource= new MapPropertySource(name,object);
		return propertySource;
	}
}
