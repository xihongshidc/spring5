package com.dc.springTest.conversion;

import com.dc.springTest.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditorSupport;

/**
 * Description: 通过PropertyEditor 进行扩展自定义类型转换
 * Author: duancong
 * Date: 2023/10/24 15:50
 *
 * @see PropertyEditorSupport
 * @see PropertyEditorRegistrar
 * @see CustomEditorConfigurer  //注册中心
 * {@link org.springframework.beans.factory.config.CustomEditorConfigurer#propertyEditorRegistrars} 该属性会在 postProcessBeanFactory 中执行注册方法.
 */
public class PropertyEditorRegistryDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/property-editors.xml");
		CustomCompanyReigtrar bean = context.getBean(CustomCompanyReigtrar.class);
		User bean1 = context.getBean(User.class);
		System.out.println(bean1);
		System.out.println(bean);

	}
}
