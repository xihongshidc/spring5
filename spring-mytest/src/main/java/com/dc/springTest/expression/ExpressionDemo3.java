package com.dc.springTest.expression;

import com.dc.springTest.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Description:  spel 语法
 * Author: duancong
 * Date: 2023/11/29 15:01
 */
public class ExpressionDemo3 {
	public static void main(String[] args) throws NoSuchMethodException {
		SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
		StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
		standardEvaluationContext.setVariable("a", null);

		Double value = spelExpressionParser.parseExpression("1.1").getValue(Double.class);
		System.out.println(" *** " + value);
		Integer value1 = spelExpressionParser.parseExpression("0xa").getValue(Integer.class);
		System.out.println("**** " + value1);

		Boolean true1 = spelExpressionParser.parseExpression("TRUE").getValue(Boolean.class);
		System.out.println(true1);
		//幂运算
		Integer result0 = spelExpressionParser.parseExpression("2 ^ 2").getValue(Integer.class);
		System.out.println(result0);
		//小于
		Boolean result = spelExpressionParser.parseExpression("1 < 2").getValue(Boolean.class);
		System.out.println(result);
		//大于
		Boolean result1 = spelExpressionParser.parseExpression("1 GT 2").getValue(Boolean.class);
		System.out.println(result1);
		//大于等于  关系表达式
		Boolean result2 = spelExpressionParser.parseExpression("1 Ge 2").getValue(Boolean.class);
		System.out.println(result2);
		//逻辑
		Boolean result3 = spelExpressionParser.parseExpression("1 GE 2 and (NOT false) || not false").getValue(Boolean.class);
		System.out.println(result3);
		Boolean result4 = spelExpressionParser.parseExpression("1 GE 2 OR (NOT false) && not false").getValue(Boolean.class);
		System.out.println(result4);
		//字符串截取
		String value2 = spelExpressionParser.parseExpression("'StringParse'[0]").getValue(String.class);
		System.out.println(value2);
		//Elivis 运算符  如果为null 返回 ?: 后面的数据, 否则返回 前面的数据.
		Object value3 = spelExpressionParser.parseExpression(" #a ?: true").getValue(standardEvaluationContext);

		System.out.println(value3);
		//正则表达式
		String result6 = spelExpressionParser.parseExpression("'123' matches '\\d{4}'").getValue(String.class);
		System.out.println(result6);

		// 类相关表达式  T(Type)  Type 类的全限定名 ,“java.lang”包除外
		Class result7 = spelExpressionParser.parseExpression("T(String)").getValue(Class.class);
		System.out.println(result7);
		Class result8 = spelExpressionParser.parseExpression("T(com.dc.springTest.expression.ExpressionDemo)").getValue(Class.class);
		System.out.println(result8);
		// 静态字段
		Integer result9 = spelExpressionParser.parseExpression("T(Integer).MIN_VALUE").getValue(Integer.class);
		System.out.println(result9);
		// 静态方法 调用
		Integer result10 = spelExpressionParser.parseExpression("T(Integer).parseInt(1)").getValue(Integer.class);
		System.out.println(result10);
		// 实例化 new
		User result11 = spelExpressionParser.parseExpression("new com.dc.springTest.User('dc',12)").getValue(User.class);
		System.out.println(result11);
		//instanceof
		Boolean result12 = spelExpressionParser.parseExpression(" 'dc caib' instanceof T(String)").getValue(Boolean.class);
		System.out.println(result12);

		//变量定义及引用  #variableName
		standardEvaluationContext.setVariable("name", "dc");
		String result13 = spelExpressionParser.parseExpression("#name").getValue(standardEvaluationContext, String.class);
		System.out.println(result13);
		standardEvaluationContext = new StandardEvaluationContext("root");
		//root 根对象   ,    this 当前上下文对象
		String result14 = spelExpressionParser.parseExpression("#root").getValue(standardEvaluationContext, String.class);
		String result15 = spelExpressionParser.parseExpression("#this").getValue(standardEvaluationContext, String.class);
		System.out.println(result14);
		System.out.println(result15);
		// 添加 自定义函数 (目前只支持静态方法注册为自定义函数,)
		Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
		standardEvaluationContext.setVariable("method1", parseInt);
		//standardEvaluationContext.registerFunction(); 等价于setVariable 方法
		Integer value4 = spelExpressionParser.parseExpression("#method1('65')").getValue(standardEvaluationContext, Integer.class);
		System.out.println(value4);

		//表达式赋值
		User rootObject = new User();
		standardEvaluationContext = new StandardEvaluationContext(rootObject);
		spelExpressionParser.parseExpression("#root.name").setValue(standardEvaluationContext, " dc ");
		User value5 = spelExpressionParser.parseExpression("#root").getValue(standardEvaluationContext, User.class);
		System.out.println(value5);
		System.out.println(value5 == rootObject);

		People people = new People();
		Car car = new Car("dazhong");
		standardEvaluationContext.setVariable("people",people);
		// ?用问号可以规避null 错误
		Car value6 = spelExpressionParser.parseExpression("#people.car?.name").getValue(standardEvaluationContext, Car.class);
		System.out.println(value6);
		people.setCar(car);
		String value7 = spelExpressionParser.parseExpression("#people.car?.name").getValue(standardEvaluationContext, String.class);
		System.out.println(value7);
		//对象方法调用
		String value8 = spelExpressionParser.parseExpression("#people.car?.method()").getValue(standardEvaluationContext, String.class);
		System.out.println(value8);
		// @ 符号来引用Bean
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		People user = new People();
		Car car1 = new Car("dc");
		car1.setName("保时捷");
		user.setCar(car1);
		factory.registerSingleton("user", user);
		standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(factory));
		People userBean = spelExpressionParser.parseExpression("@user").getValue(standardEvaluationContext, People.class);
		System.out.println(userBean);
		System.out.println(userBean == factory.getBean("user"));

		//集合修改    #variableName['字段名(索引)']
		HashMap<Object, Object> map = new HashMap<>();
		map.put("a",12);
		map.put("v",136);
		standardEvaluationContext.setVariable("map",map);
		String value9 = spelExpressionParser.parseExpression("#map['a']").getValue(standardEvaluationContext, String.class);
		System.out.println(value9);

		//集合过滤
		java.util.Map value10 = spelExpressionParser.parseExpression("#map.?[key!='a']").getValue(standardEvaluationContext, java.util.Map.class);
		System.out.println(value10);



	}

	static class Car {
		private String name;

		public Car(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String method(){

			System.out.println("111");
			return "11";
		}

		@Override
		public String toString() {
			return "Car{" +
					"name='" + name + '\'' +
					'}';
		}
	}

	public static class People{
		private Car car;

		public Car getCar() {
			return car;
		}

		public void setCar(Car car) {
			this.car = car;
		}
	}
}
