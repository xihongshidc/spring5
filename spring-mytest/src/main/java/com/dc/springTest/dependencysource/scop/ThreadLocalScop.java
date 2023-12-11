package com.dc.springTest.dependencysource.scop;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/14 22:20
 */
public class ThreadLocalScop implements Scope {

	final static String NAME_Scop = "Thread-Local";

	//重写了 NamedThreadLocal
	private final NamedThreadLocal<java.util.Map<String, Object>> namedThreadLocal = new NamedThreadLocal<java.util.Map<String, Object>>("threadLocal-scop") {
		//初始化保证对象不会空指针异常
		public HashMap initialValue() {
			return new HashMap<>();
		}
	};


	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		//获取当前线程对应的ThreadLocalMap.Entry
		Map<String, Object> stringObjectMap = namedThreadLocal.get();
		Object o = stringObjectMap.get(name);
		if (o == null) {
			o = objectFactory.getObject();
			stringObjectMap.put(name, o);
		}
		return o;
	}

	public static final Map<String, Runnable> requestDestructionCallbacks = new LinkedHashMap(8);

	@Override
	public Object remove(String name) {
		Map<String, Object> stringObjectMap = namedThreadLocal.get();
		Object remove = stringObjectMap.remove(name);
		return remove;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {

		System.out.println("注册销毁bean "+name+Thread.currentThread().getId());
		this.requestDestructionCallbacks.put(name+Thread.currentThread().getId(),callback);
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		long id = Thread.currentThread().getId();

		return String.valueOf(id);
	}
}
