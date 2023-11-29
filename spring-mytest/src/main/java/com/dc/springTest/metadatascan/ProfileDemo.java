package com.dc.springTest.metadatascan;

import com.dc.springTest.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/29 15:25
 */
public class ProfileDemo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(ProfileDemo.class);
		annotationConfigApplicationContext.getEnvironment().setDefaultProfiles("one");
		// 通过外部化配置可以实现 环境切换。  -Dspring.profiles.active=two
//		annotationConfigApplicationContext.getEnvironment().setActiveProfiles("two");

		annotationConfigApplicationContext.refresh();
		User bean = annotationConfigApplicationContext.getBean("user",User.class);
		System.out.println(bean);
	}

	@Bean("user")
	@Profile("one")
	public User user(){
		return new User();
	}

	@Bean("user")
//	@Profile("two")
	@Conditional(UserCondition.class)
	public User user2(){
		User user = new User();
		user.setAge("222");
		return user;
	}

}

