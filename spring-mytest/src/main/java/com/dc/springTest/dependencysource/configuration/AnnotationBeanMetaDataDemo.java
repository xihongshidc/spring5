package com.dc.springTest.dependencysource.configuration;

import com.dc.springTest.SuperUser;
import com.dc.springTest.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/19 10:25
 */
@ImportResource("META-INF/annnotation-dependence-injection.xml")
@Import(User.class)
@PropertySources({@PropertySource("META-INF/superuser.properties"),@PropertySource("META-INF/user.properties")})
@PropertySource("")
//将外部化配置加载进Environment 可以通过Inject或者@Value注解进行属性注入.
public class AnnotationBeanMetaDataDemo {
	@Inject
	private Environment environment;

	@Value("${superUser.name}")
	private String superName;


	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册当前类. 为配置类
		annotationConfigApplicationContext.register(AnnotationBeanMetaDataDemo.class);
		annotationConfigApplicationContext.refresh();
		String property = annotationConfigApplicationContext.getEnvironment().getProperty("user.age");
		System.out.println(property);
		Map<String, User> beansOfType = annotationConfigApplicationContext.getBeansOfType(User.class);
		Map<String, SuperUser> beansOfType1 = annotationConfigApplicationContext.getBeansOfType(SuperUser.class);
		for (Map.Entry<String, User> stringUserEntry : beansOfType.entrySet()) {
			System.out.printf(" beanName : %s ,  bean : %s \n",stringUserEntry.getKey(),stringUserEntry.getValue());
//			System.out.println();
		}
		System.out.println(beansOfType1.size());
		AnnotationBeanMetaDataDemo bean = annotationConfigApplicationContext.getBean(AnnotationBeanMetaDataDemo.class);
		System.out.println(annotationConfigApplicationContext.getEnvironment().getPropertySources()+"******");
		System.out.println(bean.environment + "******");
		System.out.println(bean.superName+"******");
		annotationConfigApplicationContext.close();
	}
}
