package com.dc.springTest.aop.service;

import com.dc.springTest.aop.Order;

public interface OrderService {

	Order createOrder(String username, String product);

	Order queryOrder(String username);
}
