package com.dc.springTest;

import org.springframework.util.StringUtils;

import java.util.HashSet;

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
	public static void main(String[] args) {
		HashSet<String> es = new HashSet<>();
		es.add("ddd");
		es.add("adc");
		es.add("moed");
		es.add("emode");
		System.out.println(StringUtils.collectionToDelimitedString(es, ", "));
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
