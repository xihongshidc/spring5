package com.dc.springTest.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Description: 泛型 擦写 示例
 * 泛型是为了骗过编译器,让其在编译的时候不需要创建一个新的类型. 其实运行时在底层存储的时候全是Object
 * Author: duancong
 * Date: 2023/10/26 10:30
 */
public class GenericDemo {
	public static void main(String[] args) {
		Collection<String> list= new ArrayList<>();
		list.add("add");
		list.add("add");
		//编译错误
//		list.add(22)
		System.out.println(list.toString());

		//泛型擦写, 任何一个类型都可以被 集合类型替换
		Collection tmp =list;
		tmp.add(222);

		System.out.println(tmp);

	}
}
