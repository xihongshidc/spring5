package com.dc.springTest.aop.service;

import com.dc.springTest.User;

public interface UserService {
	User createUser(String firstName, String lastName, int age);

	User queryUser();
}
