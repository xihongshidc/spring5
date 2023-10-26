package com.dc.springTest.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Description: java 泛型api
 * Author: duancong
 * Date: 2023/10/26 10:53
 */
public class GenericApiDemo {
	public static void main(String[] args) {

		//原生类型 int long float
		Class<Integer> integerClass = int.class;

		//数组类型 array
		Class<Object[]> aClass = Object[].class;

		//原始类型 String
		Class<String> stringClass = String.class;

		// 泛型类型
		ParameterizedType arrayListClass = (ParameterizedType) ArrayList.class.getGenericSuperclass(); // java.util.AbstractList<E>
		Class<ArrayList> arrayListClass1 = ArrayList.class;  //java.util.AbstractList
		System.out.println(arrayListClass.getRawType());     //java.util.AbstractList
		System.out.println(arrayListClass);

		Type[] actualTypeArguments = arrayListClass.getActualTypeArguments();
//		TypeVariable[] cast = TypeVariable[].class.cast(actualTypeArguments);  数组 类型不可以强转, 成员类型可以转.

		//强转泛型中类型变量
		Stream.of(actualTypeArguments).map(TypeVariable.class::cast).forEach(System.out::println);

		Collection  lis = new ArrayList();
		Type genericSuperclass = lis.getClass().getGenericSuperclass();
	}
}
