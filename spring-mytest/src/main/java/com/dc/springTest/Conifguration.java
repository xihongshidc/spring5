package com.dc.springTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:37
 */
@Configuration
public class Conifguration {

	@Bean({"duancong-user","user"})
	public User init(){
		User user = new User();
		user.setAge("22");
		user.setName("duancong");
		Student student = student();
		System.out.println(student.toString());
		return user;
	}

	@Bean
	public Student student(){
		Student user = new Student();
		user.setName("student");
		return user;
	}

	@Bean
	public ClassRoom classRoom(){
		ClassRoom classRoom = new ClassRoom();
		classRoom.setId(10l);
		return classRoom;
	}



}
