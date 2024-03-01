package com.dc.springTest.dependencysource;

import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Properties;

/**
 * Description:
 * Author: duancong
 * Date: 2024/1/23 11:45
 */
public class CustomProperties extends Properties {

	public CustomProperties() {
	}

	public CustomProperties(java.util.Map defaults) {
		this.putAll(defaults);
	}

	//重写这个getProperty 方法
	@Override
	public String getProperty(String key) {
		Object value = get(key);
		return value !=null ? value.toString() : null;
	}

	//spring 占位符替换工具
	private final static PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}",":",true);

	public static void main(String[] args) {
		method();
	}

	public static void  method(){
		Object o = new Object();

		synchronized (o) {
//		CustomProperties customProperties = new CustomProperties(new HashMap());
			Properties map = new Properties();

			map.put("invoiceN1o", 23111);
			map.put("test", "dc");
			//自定义Properties
			CustomProperties customProperties = new CustomProperties(map);

			Thread thread = new Thread();
			System.out.println(thread.getId());
			System.out.println(helper.replacePlaceholders("${invoiceN1o:32} 你好 ${test:36}asdasd", customProperties));
		}
	}
}
