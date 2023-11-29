package com.dc.springTest.injection;

import com.dc.springTest.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Description: 基于@Autowired  @Resource  @Bean 实现方法注入 ,
 * Author: duancong
 * Date: 2023/10/9 14:44
 */
public class AnnotationMethodDependenceInject {
	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(AnnotationMethodDependenceInject.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		AnnotationMethodDependenceInject bean = annotationConfigApplicationContext.getBean(AnnotationMethodDependenceInject.class);
		System.out.println(bean.userHolder);
		System.out.println(bean.userHolder1);
		System.out.println(bean);
		System.out.println(AnnotationMethodDependenceInject.name);
		annotationConfigApplicationContext.close();
		System.out.println(AnnotationMethodDependenceInject.name); //类级别的属性.

		System.out.println("**************************** " );
		AnnotationMethodDependenceInject annotationMethodDependenceInject = new AnnotationMethodDependenceInject();
		System.out.println(annotationMethodDependenceInject.userHolder);  // 类级别 静态属性
		System.out.println(annotationMethodDependenceInject.userHolder.getBeanName() +annotationMethodDependenceInject.userHolder.getClassLoader() );
		UserHolder userHolder = new UserHolder();
		System.out.println(userHolder.getBeanName() +" *** "+ userHolder.getClassLoader());
	}

	//静态属性
	static String name;
	//静态属性
	static UserHolder userHolder;

	UserHolder userHolder1;

	//注入属性
	@Value("${user.name}")
	public void value(String name) {
		System.out.println(name+"***");
		this.name= name;
	}

	@Autowired
	public void initUserHolder(UserHolder userHolder){
		this.userHolder =userHolder;
	}

	@Resource
	public void initUserHolder2(UserHolder userHolder){
		this.userHolder1 =userHolder;
	}

	@Bean
	public UserHolder userHolder(User user){
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);//set注入
		this.userHolder =userHolder;
		return userHolder;
	}
}
