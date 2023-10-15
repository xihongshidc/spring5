package com.dc.springTest.dependencysource.lifecycle;

import com.dc.springTest.injection.AutowiredAnnotationDependencyInjectDemo;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: {@link AbstractBeanFactory#getMergedLocalBeanDefinition(java.lang.String)} 从这个方法开始合并bean信息，
 * 里面会迭代获取ParentName 然后获取父的beanDefination ， 如果父bean 没有，那么迭代去加载父beandefination 然后进行合并判断，然后升级为RootBeandefinition
 * （合并就是用子类的属性去覆盖父类的属性，最终变成了RootBeandefinition）
 * Author: duancong
 * Date: 2023/10/15 19:50
 */
public class MergeBeanDefinitionDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//注册bean
		annotationConfigApplicationContext.register(MergeBeanDefinitionDemo.class);

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
		//加载配置文件， 解析并生成beanDefinitions
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/annnotation-dependence-injection.xml");
		//启动应用上下文
		annotationConfigApplicationContext.refresh();
		//依赖查找。
		MergeBeanDefinitionDemo bean = annotationConfigApplicationContext.getBean(MergeBeanDefinitionDemo.class);
		System.out.println("==========================");
		System.out.println(bean);
		annotationConfigApplicationContext.close();


	}
}
