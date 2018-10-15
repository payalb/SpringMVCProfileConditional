package com.java.config;

import java.util.Arrays;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DevProdEnvironmentConfig implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
String[] profilesActive=context.getEnvironment().getActiveProfiles();
		
		return Arrays.stream(profilesActive).allMatch(x-> !x.equalsIgnoreCase("test"));
	}

}
