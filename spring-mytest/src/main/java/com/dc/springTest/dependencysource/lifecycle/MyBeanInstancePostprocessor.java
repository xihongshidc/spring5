package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.SuperUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/17 15:06
 */
class MyBeanInstancePostprocessor implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals(beanName,"user1")){
			//非正常流程
//				return new SuperUser();
		}
		//执行正常的 bean 实例化, 初始化,流程.
		return null;
	}

	//可以完成 自定义属性注入,而不是自动配置配置的Beandefinition
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals(beanName,"user1")){
			SuperUser sp= (SuperUser) bean;
			sp.setAge("2");
			sp.setName("dcc");

			return true;
		}
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		if (!ObjectUtils.nullSafeEquals(beanName, "user1")) {
			return null;
		}
		if (pvs instanceof MutablePropertyValues) {
			MutablePropertyValues pvs1 = (MutablePropertyValues) pvs;
			if (pvs1.contains("age")) {
				PropertyValue age = pvs1.getPropertyValue("age");
				age.setConvertedValue("30");
			}
			if (pvs1.contains("name")) {
				PropertyValue age = pvs1.getPropertyValue("name");
				age.setConvertedValue("dac");
			}
		}
		return pvs;
	}
}
