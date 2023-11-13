package com.dc.springTest;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Description: 循环引用...
 *  静态方法的生成的bean对象不会出现自我引用..
 * {@link DefaultListableBeanFactory#isSelfReference(String, String)}
 * Author: duancong
 * Date: 2023/11/4 22:10
 */
public class CircularReferencesDemo {

	//实例化的方式, 构造器, 静态工厂(静态方法) , 普通工厂方法实例化 ,Factorybean 实例化
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(CircularReferencesDemo.class);
		annotationConfigApplicationContext.register(UserFactory.class);
		annotationConfigApplicationContext.refresh();
		Student student = annotationConfigApplicationContext.getBean(Student.class);
		ClassRoom classRoom = annotationConfigApplicationContext.getBean(ClassRoom.class);
		System.out.println(student);
		System.out.println(classRoom);
		CircularReferencesDemo bean = annotationConfigApplicationContext.getBean(CircularReferencesDemo.class);
	}

	/**
	 * description:
	 *  循环依赖 ,首先 实例化student, 此时实例化完, 会添加三级单例工厂缓存, 然后依赖classRoom,
	 *  此时开始实例化ClassRoom 对象, 实例化class 对象的时候也会添加三级工厂缓存, 实例化完后 会依赖注入上面的student 对象, 属性注入, 然后初始化, 完事之后添加到一级缓存.
	 *  之后重新开始 Student对象 属性注入, 和初始化, 然后添加一级缓存 .
	 */

	@Bean
	public static Student student(){
		Student student = new Student();
		student.setName("dc");
		student.setId(22l);
		return student;
	}

	@Bean
	public static ClassRoom classRoom(){
		ClassRoom classRoom = new ClassRoom();
		classRoom.setId(1l);
		return classRoom;
	}
}
