package com.java.config;

import java.util.Arrays;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TestEnvironmentConfig implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//Thsi line will not work:
		//String env=context.getEnvironment().getProperty("spring.profiles.active");
		String[] profilesActive=context.getEnvironment().getActiveProfiles();
		
		return Arrays.stream(profilesActive).anyMatch(x-> x.equalsIgnoreCase("test"));
	}

}
