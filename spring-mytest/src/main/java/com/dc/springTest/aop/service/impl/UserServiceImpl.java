package com.dc.springTest.aop.service.impl;

import com.dc.springTest.User;
import com.dc.springTest.aop.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/10 10:19
 */
@Service
public class UserServiceImpl implements UserService {
	@Override
	public User createUser(String firstName, String lastName, int age) {
		User user = new User();
		user.setName(firstName+lastName);
		user.setAge(age+"");

		UserServiceImpl o1 = (UserServiceImpl) AopContext.currentProxy();
		System.out.println(o1 +"-/*");
		return user;
	}

	@Override
	public User queryUser() {
		User user = new User();
		user.setName("test");
		user.setAge(16+"");
		return user;
	}
}
