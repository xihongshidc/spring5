package com.dc.springTest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/6 17:23
 */
public class Student {
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	@Autowired
	private ClassRoom classRoom;

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", classRoom.Id =" + classRoom.getId()+
				'}';
	}
}
