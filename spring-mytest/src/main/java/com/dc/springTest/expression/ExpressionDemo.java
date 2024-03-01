package com.dc.springTest.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Description: spel
 * Author: duancong
 * Date: 2023/11/29 11:00
 */
public class ExpressionDemo {
	public static void main(String[] args) {

//		// 文本
//		SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
//		Expression expression1 = spelExpressionParser.parseExpression("'Hello World'");
//		System.out.println(expression1.getValue());

		// concat 函数
		// 创建解析器对象
		SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
		Expression expression2 = spelExpressionParser.parseExpression("('Hello World').concat(#end)");
		StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
//		//添加 上下文,
		standardEvaluationContext.setVariable("end","!!");
		System.out.println(expression2.getValue(standardEvaluationContext));

		//表达式模板  前缀+表达式+后缀
		ParserContext parserContext = new ParserContext(){
			@Override
			public boolean isTemplate() {
				return true;
			}

			@Override
			public String getExpressionPrefix() {
				return "#{";
			}

			@Override
			public String getExpressionSuffix() {
				return "}";
			}
		};
		Expression expression1 = spelExpressionParser.parseExpression("#{('Hello World').concat('!')} #{'World!'}",parserContext);

		System.out.println(expression1.getValue(String.class));

		standardEvaluationContext.setVariable("name","dc");
		System.out.println(spelExpressionParser.parseExpression("{#name}+sda", new ParserContext() {
			@Override
			public boolean isTemplate() {
				return true;
			}

			@Override
			public String getExpressionPrefix() {
				return "{";
			}

			@Override
			public String getExpressionSuffix() {
				return "}";
			}
		}).getValue(standardEvaluationContext, String.class));


	}
}
