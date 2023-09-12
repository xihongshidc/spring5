package com.dc.springTest;

/**
 * Description:
 * Author: duancong
 * Date: 2023/9/12 16:36
 */
public class User {
	private String name;
	private String age;

	public User(String name, String age) {
		this.name = name;
		this.age = age;
	}

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age='" + age + '\'' +
				'}';
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
