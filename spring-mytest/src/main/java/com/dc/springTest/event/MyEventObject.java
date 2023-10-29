package com.dc.springTest.event;

import org.springframework.context.ApplicationEvent;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/27 17:44
 */
public class MyEventObject extends ApplicationEvent {
	/**
	 * Constructs a prototypical Event.
	 *
	 * @param source The object on which the Event initially occurred.
	 * @throws IllegalArgumentException if source is null.
	 */
	public MyEventObject(Object source) {
		super(String.format("%s , thread : {}",source));
	}
}
