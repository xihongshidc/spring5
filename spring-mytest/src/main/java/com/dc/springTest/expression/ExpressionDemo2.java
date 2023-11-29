package com.dc.springTest.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Description: spel
 * Author: duancong
 * Date: 2023/11/29 11:00
 */
public class ExpressionDemo2 {
	public static void main(String[] args) {

		// 表达式解析
		SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
		Expression expression1 = spelExpressionParser.parseExpression("T(java.lang.Math).random()");
		Expression expression2 = spelExpressionParser.parseExpression("(T(java.lang.Math).random()) +'22'");
		System.out.println(expression2.getValue());
		System.out.println(expression1.getValue());


	}
}
