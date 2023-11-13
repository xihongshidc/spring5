package com.dc.springTest.generic;

import org.springframework.core.ResolvableType;

/**
 * Description: ResolvableType  泛型 解析
 *  替代 GenericCollectionTypeResolve  和  GenericTypeResolver
 *  工厂方法：for* 方法
 *  转换方法：as* 方法
 *  处理方法：resolve*方法
 *
 * Author: duancong
 * Date: 2023/10/26 15:45
 */
public class ResolvableTypeDemo {
	public static void main(String[] args) {

		//ListString->ArrayList -> AbstractList ->List > Collection
		ResolvableType resolvableType = ResolvableType.forClass(ListString.class);
		System.out.println(resolvableType.resolve());   // 获取原生类型

		System.out.println(resolvableType.getSuperType()); //java.util.ArrayList

		System.out.println(resolvableType.getSuperType().getSuperType()); //java.util.AbstractList

		System.out.println(resolvableType.asCollection().resolve()); // 获取原生类型  interface java.util.Collection

		System.out.println(resolvableType.asCollection().resolveGeneric(0)); //获取泛型参数类型 class java.lang.String

		System.out.println(resolvableType.resolveGeneric());


	}
}
