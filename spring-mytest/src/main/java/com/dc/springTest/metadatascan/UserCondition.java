package com.dc.springTest.metadatascan;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Profiles;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/29 15:51
 */
public class UserCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().acceptsProfiles(Profiles.of("two"));
	}
}
