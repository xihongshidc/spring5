package com.dc.springTest.metadatascan;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/29 13:58
 */
public class EnableHelloworldBeandefinitionResgistar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotatedGenericBeanDefinition annotatedGenericBeanDefinition = new AnnotatedGenericBeanDefinition(EnableHellworldConfiguration.class);
		BeanDefinitionReaderUtils.registerWithGeneratedName(annotatedGenericBeanDefinition,registry);
	}
}
