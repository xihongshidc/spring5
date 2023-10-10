package com.dc.springTest.injection;

import com.dc.springTest.User;
import com.dc.springTest.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/10 10:19
 */
public class GroupAnnotationDemo {
	@Autowired
	private UserHolder userHolder;

	@Autowired
	private List<UserHolder> ls; //userHolder1 , userHolder2 , userHolder3 ,userHolder4 ,userHolder5


	@Autowired
	@Qualifier
	private List<UserHolder> QualifierLs; //userHolder3 /4 /5

	@Autowired
	@UserGroup
	private List<UserHolder> GroupLs; //userHolder5

	public static void main(String[] args) {
		// 基于ApplicationContext实现的ioc
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(GroupAnnotationDemo.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		GroupAnnotationDemo bean = annotationConfigApplicationContext.getBean(GroupAnnotationDemo.class);
		System.out.println("userHolder  "+bean.userHolder);
		System.out.println("Autowired "+bean.ls);
		System.out.println("QualifierLs  "+bean.QualifierLs);
		System.out.println("GroupLs "+bean.GroupLs);
	}

	@Bean
	public UserHolder userHolder(User user){
		return createUserHolder(1l);
	}
	@Bean
	public UserHolder userHolder1(User user){
		return createUserHolder(2l);
	}
	@Bean
	@Qualifier // 可以实现按名称限定, 实现分组
	public UserHolder userHolder3(User user){
		return createUserHolder(3l);
	}
	@Bean
	@Qualifier
	public UserHolder userHolder4(User user){
		return createUserHolder(4l);
	}

	@Bean
	@UserGroup
	public UserHolder userHolder5(User user){
		return createUserHolder(5l);
	}


	public static UserHolder createUserHolder(Long id){
		UserHolder userHolder = new UserHolder(id);
		return userHolder;
	}



}
