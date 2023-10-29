package com.dc.springTest.metadatascan;

import com.dc.springTest.annotation.MyComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ConfigurationClass;
//import org.springframework.context.annotation.ConfigurationClassParser;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/28 21:12
 * @see MyComponent
 * {@link ConfigurationClassParser#doProcessConfigurationClass(ConfigurationClass, org.springframework.context.annotation.ConfigurationClassParser.SourceClass, java.util.function.Predicate)}
 *
 */
@ComponentScan(basePackages = "com.dc.springTest.metadatascan")
public class ComponentScanDemo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(ComponentScanDemo.class);
		annotationConfigApplicationContext.refresh();

		MyComponentDemo bean = annotationConfigApplicationContext.getBean(MyComponentDemo.class);
		System.out.println(bean);

		annotationConfigApplicationContext.close();
	}
}
