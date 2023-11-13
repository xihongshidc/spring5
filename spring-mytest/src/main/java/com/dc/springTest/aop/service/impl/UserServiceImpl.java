package com.dc.springTest.aop.service.impl;

import com.dc.springTest.User;
import com.dc.springTest.aop.service.UserService;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/10 10:19
 */
public class UserServiceImpl implements UserService {
	@Override
	public User createUser(String firstName, String lastName, int age) {
		User user = new User();
		user.setName(firstName+lastName);
		user.setAge(age+"");

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
