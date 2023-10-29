package com.dc.springTest.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: 层次性上下文. 事件传播.
 * Author: duancong
 * Date: 2023/10/27 10:31
 */
public class HierarchicalApplicationContextEventDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
//		annotationConfigApplicationContext.register(ApplicationContextListenerDemo.class);
//		annotationConfigApplicationContext.register(ApplicationContextListenerDemo.MyListener.class);
		parentContext.setId("parent-context");
		parentContext.register(MyListener.class);
		AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
		currentContext.setId("current-context");
		currentContext.setParent(parentContext);
		currentContext.register(MyListener.class);

		parentContext.refresh(); //先refhresh父 , 然后再子
		currentContext.refresh();  //如果子还有一个父那么父容器会调用该对象的publish 方法. 然后去发布消息.

		currentContext.close(); //所以如果先关闭子, 此时子容器会触发两个事件.
		parentContext.close(); //父容器会触发一个事件


	}

	//基于spring bean 配置ApplicationListener
	static class MyListener implements ApplicationListener<ApplicationContextEvent> {
		//去重一致性判断. 加静态字段是为了保证这个属性在类级别 都是唯一的.
		private static Set<ApplicationContextEvent> applicationContextEventSet =new HashSet<>();
		@Override
		public void onApplicationEvent(ApplicationContextEvent event) {
			if (applicationContextEventSet.add(event)){
				System.out.printf("MyListener 事件id: {%s} \n" ,event.getApplicationContext().getId());
			}

		}
	}


}
