package com.dc.springTest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Description: 应用场景， mybatis - > SqlSessionFactoryBean   |  dubbo -> ReferenceBean
 * FactoryBean 通常是用来创建比较复杂的bean，一般的bean 直接用xml配置即可，但如果一个bean的创建过程中涉及到很多其他的bean 和复杂的逻辑，
 * 用xml配置比较困难，这时可以考虑用FactoryBean。
 *
 * {@link org.mybatis.spring.mapper.MapperFactoryBean } 实现了FactoryBean  主要是将mapper 接口 交给spring 管理,通过代理生成了Mapper 实例,
 * 在调用getMapper 方法的时候会生成代理实例, 可以执行自定义sql ,支持事务.
 * Author: duancong
 * Date: 2023/9/19 11:59
 */
@Component
public class UserFactory implements FactoryBean<User> {
	@Override
	public User getObject() throws Exception {
		User user = new User();
		user.setAge("1");
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
