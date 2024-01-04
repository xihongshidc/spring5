package com.dc.springTest.aop.service.impl;

import com.dc.springTest.aop.Order;
import com.dc.springTest.aop.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/10 10:13
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Override
	public Order createOrder(String username, String product) {
		Order order = new Order();
		order.setUserName(username);
		order.setProduct(product);
		return order;
	}

	@Override
	public Order queryOrder(String username) {
		System.out.println("on");
		Order order = new Order();
		order.setUserName("test");
		order.setProduct("test");
		return order;
	}

}
