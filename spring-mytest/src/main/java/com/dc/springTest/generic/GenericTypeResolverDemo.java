package com.dc.springTest.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.core.GenericTypeResolver.resolveReturnType;
import static org.springframework.core.GenericTypeResolver.resolveReturnTypeArgument;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/26 11:32
 * @see GenericTypeResolver
 */
public class GenericTypeResolverDemo {
	public static void main(String[] args) throws NoSuchMethodException {

		displayMethodReturn("getString",GenericTypeResolverDemo.class,Comparable.class);
		displayMethodReturn("getList",GenericTypeResolverDemo.class,List.class);
		displayMethodReturn("getListString",GenericTypeResolverDemo.class,List.class);


		Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(ListString.class);
		System.out.println(typeVariableMap);
	}

	private static void displayMethodReturn(String methodName, Class<?> cls, Class generic) throws NoSuchMethodException {
		Method getString = GenericTypeResolverDemo.class.getMethod(methodName);
		Class<?> aClass = resolveReturnType(getString, cls);
		System.out.println("获取返回类型 " + " : "+aClass);
		Class<?> aClass1 = resolveReturnTypeArgument(getString, generic);
		System.out.println("获取泛型类型参数 : "+aClass1);
	}

	public static String getString(){
		return null;
	}

	//泛型参数  , 具体化 这时候 泛型里面的变量就是一个常量了.
	public static ArrayList<Long> getList(){
		return null;
	}
	public static ListString getListString(){
		return null;
	}

}
