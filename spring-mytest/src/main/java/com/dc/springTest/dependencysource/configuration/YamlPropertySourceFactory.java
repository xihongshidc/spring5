package com.dc.springTest.dependencysource.configuration;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/19 18:29
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		//YamlPropertiesFactoryBean 的结构和 YamlMapFactoryBean 结构有区别,
		YamlPropertiesFactoryBean yamlMapFactoryBean = new YamlPropertiesFactoryBean();
		yamlMapFactoryBean.setResources(resource.getResource());
		Properties object = yamlMapFactoryBean.getObject();

		PropertySource propertySource= new PropertiesPropertySource(name,object);
		return propertySource;
	}
}
