package com.dc.springTest;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/6 17:23
 */
public class ClassRoom {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Autowired
	private List<Student> students; //Autowired 执行时机是在populate 属性注入时候..此时已经存在实例对象.

	@Override
	public String toString() {
		return "ClassRoom{" +
				"id=" + id +
				", students=" + students +
				'}';
	}
}
