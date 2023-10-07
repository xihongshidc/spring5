package com.dc.springTest;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;//静态导入.

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/7 14:34
 */
//通过Configuration(java注解), xml配置, api 方式 ,注册一个 BeanDefinition
@Import(Conifguration.class)
public class AnnotationConfigTest {
	public static void main(String[] args) {

		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.scan("com.dc.springTest");
//		annotationConfigApplicationContext.register(AnnotationConfigTest.class);
		//启动应用上下文
		annotationConfigApplicationContext.refresh();

		User bean = annotationConfigApplicationContext.getBean(User.class);
		User bean2 = annotationConfigApplicationContext.getBean("user",User.class);
		User bean1 = annotationConfigApplicationContext.getBean("duancong-user",User.class);
		System.out.println(bean);

		System.out.println("user 是否等于 duancong-user " + (bean1==bean2) );
		registerUserBeanDefinition(annotationConfigApplicationContext,"user-merge");//调用api生成bean
		registerUserBeanDefinition(annotationConfigApplicationContext,null);
		System.out.println(" Configuration 类型的 所有Beans "+annotationConfigApplicationContext.getBeansOfType(Conifguration.class));
		System.out.println(" Configuration 类型的 所有User  "+annotationConfigApplicationContext.getBeansOfType(User.class));
		annotationConfigApplicationContext.close();
		annotationConfigApplicationContext.stop();



	}
	public static void registerUserBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry,String beanName){

		BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
		beanDefinitionBuilder.addPropertyValue("name","dcc").addPropertyValue("age","20");
		if (StringUtils.hasText(beanName)){
			beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
		}else{
			BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),beanDefinitionRegistry);
		}
	}




}
