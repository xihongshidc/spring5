package com.dc.springTest.dependencysource.configuration;

import com.dc.springTest.SuperUser;
import com.dc.springTest.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;

import javax.annotation.Resources;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/19 10:25
 */
@PropertySource("META-INF/superuser.properties")
@PropertySource("META-INF/user.properties")
//将外部化配置加载进Environment 可以通过Inject或者@Value注解进行属性注入.
public class AnnotationPropertySourceDemo {
	@Inject
	private Environment environment;

	@Value("${user.name}")
	private String superName;

	@Value("${resource}")
	private Resource resource; //类型转换, 字符串转为Resource

	@Bean
	public User user(){
		User user = new User();
		user.setAge("1");
		user.setName(superName);
		return user;

	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册当前类. 为配置类
		annotationConfigApplicationContext.register(AnnotationPropertySourceDemo.class);
		MutablePropertySources propertySources = annotationConfigApplicationContext.getEnvironment().getPropertySources();

		//自定义添加PropertySource
		java.util.Map map =new HashMap();
		map.put("user.name","ddddd");
		org.springframework.core.env.PropertySource propertySource=new MapPropertySource("dc",map);
		propertySources.addFirst(propertySource);

		annotationConfigApplicationContext.refresh(); // refresh 完之后那么类的属性都已经注入完了,所以再添加PropertySource 其实作用并不大
		Map<String, User> beansOfType = annotationConfigApplicationContext.getBeansOfType(User.class);
		for (Map.Entry<String, User> stringUserEntry : beansOfType.entrySet()) {
			System.out.printf(" beanName : %s ,  bean : %s \n",stringUserEntry.getKey(),stringUserEntry.getValue());
		}
		AnnotationPropertySourceDemo bean = annotationConfigApplicationContext.getBean(AnnotationPropertySourceDemo.class);
		System.out.println(annotationConfigApplicationContext.getEnvironment().getPropertySources()+"******");
		System.out.println(bean.environment + "******");
		String property = bean.environment.getProperty("user.name");
		System.out.println(property);
		System.out.println(bean.superName+"******");
		System.out.println(bean.resource+"*****");
		annotationConfigApplicationContext.close();
	}
}
