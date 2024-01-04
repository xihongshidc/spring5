package com.dc.springTest.dependencysource.configuration;

import com.dc.springTest.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * Description:  todo yaml
 * Author: duancong
 * Date: 2023/10/19 17:22
 */
//@ImportResource("META-INF/yaml-property-source.xml")
@PropertySource(name = "dddd",value = "META-INF/user.yaml", factory = YamlPropertySourceFactory.class)//扩展yaml 格式读取为Properties文件
public class YamlPropertySourceDemo {
	@Value("${user.age2:d}")
	private String superName;
//
//	@Value("${resource}")
//	private Resource resource; //类型转换, 字符串转为Resource

	//@Bean 触发bean的注册, 定义
	@Bean(value = "user2")
	public User user(@Value("${user.name}")String name, @Value("${user.age}")String age){
		User user = new User();
		user.setAge(age);
		user.setName(name);
		return user;

	}
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册当前类. 为配置类
		annotationConfigApplicationContext.register(YamlPropertySourceDemo.class);
		//自定义添加PropertySource
		annotationConfigApplicationContext.refresh();

//		YamlMapFactoryBean beansOfType1 = annotationConfigApplicationContext.getBean(YamlMapFactoryBean.class);

		System.out.println(annotationConfigApplicationContext.getEnvironment().getPropertySources());
//		System.out.println(beansOfType1.getObject());
//		Map yamlMap = annotationConfigApplicationContext.getBean("yamlMap", Map.class);
//		System.out.println(yamlMap);
//		System.out.println(annotationConfigApplicationContext.getEnvironment().getPropertySources() + "******");

		YamlPropertySourceDemo bean = annotationConfigApplicationContext.getBean(YamlPropertySourceDemo.class);
		User user2 = (User) annotationConfigApplicationContext.getBean("user2");
		System.out.println(user2);
		System.out.println(bean.superName);
		annotationConfigApplicationContext.close();
	}
}
