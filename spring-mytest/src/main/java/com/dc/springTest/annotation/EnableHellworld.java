package com.dc.springTest.annotation;


import com.dc.springTest.metadatascan.EnableHelloworldSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
// 方法一就是通过 Import 导入配置类
//@Import(EnableHellworldConfiguration.class)
// 法二 :  导入一个实现ImportSelector 的类
@Import(EnableHelloworldSelector.class)
// 法三 ： 通过ImportBeanDefinitionRegistrar 实现类进行bean 定义的注册，
//@Import(EnableHelloworldBeandefinitionResgistar.class)
public @interface EnableHellworld {
}
