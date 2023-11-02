package com.dc.springTest.metadatascan.environment;

import com.dc.springTest.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/31 15:36
 */
@PropertySource(name = "zhangsan",value = "META-INF/superuser.properties")
public class EnvironmentPropertySourceDemo {
	@Value("${superUser.name}")
	private String superName; //Apollo 支持动态的配置.

	@Value("${resource}")
	private Resource resource; //类型转换, 字符串转为Resource

	@Bean
	public User user(@Value("${user.age}")String age, @Value("${user.name}")String superName){
		User user = new User();
		user.setAge(age);
		user.setName(superName);
		return user;

	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册当前类. 为配置类
		annotationConfigApplicationContext.register(EnvironmentPropertySourceDemo.class);
		MutablePropertySources propertySources = annotationConfigApplicationContext.getEnvironment().getPropertySources();
		//自定义添加PropertySource
		java.util.Map map =new HashMap();
		map.put("user.name","ddddd");
		map.put("user.age","9654");
		org.springframework.core.env.PropertySource propertySource=new MapPropertySource("dc",map);
		propertySources.addFirst(propertySource);

		annotationConfigApplicationContext.refresh(); // refresh 完之后那么类的属性都已经注入完了,所以再添加PropertySource 其实作用并不大
		Map<String, User> beansOfType = annotationConfigApplicationContext.getBeansOfType(User.class);
		for (Map.Entry<String, User> stringUserEntry : beansOfType.entrySet()) {
			System.out.printf(" beanName : %s ,  bean : %s \n",stringUserEntry.getKey(),stringUserEntry.getValue());
		}
		map.put("user.name","110dd"); //这里是在初始化之后重新修改输入的属性.
		EnvironmentPropertySourceDemo bean = annotationConfigApplicationContext.getBean(EnvironmentPropertySourceDemo.class);
		MutablePropertySources propertySources1 = annotationConfigApplicationContext.getEnvironment().getPropertySources();
		for (org.springframework.core.env.PropertySource<?> source : propertySources1) {
			//PropertySource 在environment中是有顺序的.
			System.out.printf(" name %s  ****** user.name %s \n", source.getName(),source.getProperty("user.name"));
		}

		System.out.println(bean.superName+"******");
		System.out.println(bean.resource+"*****");
		System.out.println("****"+ propertySources);
		annotationConfigApplicationContext.close();
	}
}
