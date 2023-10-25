package com.dc.springTest.conversion;

import com.dc.springTest.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * (AbstractApplicationContext) finishBeanFactoryInitialization  (初始化BeanFactory 中 conversionService)  ->
 * beandefinition  -> 实例化后 会生成BeanWrapperImpl 包装对象  (这里会创建一个默认的 new TypeConverterDelegate()  )  ->
 * 调取initBeanWrapper(BeanWrapper) ,将beanFactory 中的 conversionService 传递到BeanwrapperImpl -> 属性转换
 * BeanWrapper.convertForProperty()  (将原始数据转换成目标数据) ->  会委派给TypeConverterDelegate 进行 类型转换。 ->
 * 获取自定义的PropertyEditor ，或者获取 beanWrapper 中保存的ConversionService 然后，进行判断是否有对应的Converter 。。
 * 然后调用convert 方法，返回对应的结果。
 * setPropertyValues(PropertyValues) 反射设置值。。
 *
 *
 * Author: duancong
 * Date: 2023/10/25 9:58
 */
public class CustomConvertDemo {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/custom-convert.xml");

		User bean1 = context.getBean(User.class);
		System.out.println(bean1);

	}
}
