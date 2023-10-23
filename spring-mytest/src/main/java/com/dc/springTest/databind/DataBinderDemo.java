package com.dc.springTest.databind;

import com.dc.springTest.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/23 17:04
 * @see DataBinder
 * @see MutablePropertyValues
 *
 */
public class DataBinderDemo {
	public static void main(String[] args) {
		User user = new User();

		DataBinder dataBinder = new DataBinder(user,"user");
		//构造数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","张三");
		map.put("age","23");
		map.put("company",new User.Company());
		map.put("company.company","zzzzz");
		map.put("id","11");

		MutablePropertyValues propertyValues = new MutablePropertyValues(map);
		// 不会忽略不认识的字段
//		dataBinder.setIgnoreUnknownFields(false);
		// 自动按照嵌套路径生成对象属性
		dataBinder.setAutoGrowNestedPaths(false);   //false 忽略嵌套信息
		// 校验必须的字段,
		dataBinder.setRequiredFields("name","age","id","city");
		dataBinder.setDisallowedFields("age");
		//是否忽略非法字段, 默认是不忽略,
//		dataBinder.setIgnoreInvalidFields(true);
		dataBinder.bind(propertyValues);

		BindingResult bindingResult = dataBinder.getBindingResult();
		//获取 绑定结果, (结果都是文案 信息 错误code, 记录 {校验的必须的字段, 黑名单的字段})
		System.out.println(bindingResult);
		System.out.println(user);

	}
}
