package com.dc.springTest.databind;

import com.dc.springTest.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/24 10:52
 */
public class JavaBeansDemo {
	public static void main(String[] args) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
			//属性描述 这个属性只和set get 方法 有关系, 和字段的名字没有关系... 也就是提供了get set方法,那么就会有着字段name
			// 每个对象都继承自Object 包含一个getClass 属性, 可以通过stopClass 排除这个属性
			Stream.of(beanInfo.getPropertyDescriptors()).forEach(n->{
				Method writeMethod = n.getWriteMethod();
				Method readMethod = n.getReadMethod();
				System.out.println(n);
			});

			Stream.of(beanInfo.getMethodDescriptors()).forEach(methodDescriptor -> {
				System.out.println(methodDescriptor);
			});


		} catch (IntrospectionException e) {
			e.printStackTrace();
		}


	}
}
