Using Condition to read property file based on active profile and in test , enabling it for test profile
using @Activeprofile

     	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//context.getBean("")!=null
		//To condition on presence of a bean
	return	context.getBeanFactory().containsBeanDefinition("");
	}
	
	//To find if property set in env
	return	"test".equalsIgnoreCase(context.getEnvironment().getProperty("db.name"));
	
Spring 4.0 @Conditional annotation is at more higher level when compared to @Profile annotation. @Profile annotation should be used for loading application configuration based on conditional logic.
@Profile annotation is restricted to write conditional checking based on predefined properties. @Conditional annotation does not have this restriction.

Both Spring @Profiles and @Conditional annotations are used to develop an “If-Then-Else” conditional checking. However, Spring 4 @Conditional  is more generalized version of @Profile annotation.

Spring 3.1 @Profiles is used to write conditional checking based on Environment variables only. Profiles can be used for loading application configuration based on environments.
Spring 4 @Conditional annotation allows Developers to define user-defined strategies for conditional checking. @Conditional can be used for conditional bean registrations.

We can use Spring @Conditional annotation for the following scenarios:

	Condition whether a property is available or not using Environment variables, irrespective of its value.
	Like Profiles, Condition whether a property value is available or not using Environment variables.

	Conditions based on a Bean definition are present in Spring Application context.

	Conditions based on a Bean object are present in Spring Application context.

	Conditions based on some or all Bean properties values.

	Conditions based on some Resources are present in current Spring Application Context or not.

	Conditions based on Bean’s Annotations

	Conditions Bean’s Method’s Annotations.

	Conditions based on Bean’s Annotation’s parameter values

	Conditions based on  Bean’s Method’s Annotation’s parameter values.
	
