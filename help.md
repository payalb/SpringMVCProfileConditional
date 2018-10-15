Using Condition to read property file based on active profile and in test , enabling it for test profile
using @Activeprofile

Most projects will have different environments like DEV, QA, PREPROD and PRODUCTION. Most of the projects have different databases for each Environment. First developer starts developing projects on DEV environment which uses DEV database. Once development is done, they will move code to QA environment which uses different database. Once QA is done successfully, they will move to PREPRODUCTION environment, which uses PREPRO database to do End-to-End and performance testing. Once everything is done and happy to go live, it will be deployed on LIVE or PRODUCTION Environment which uses PROD database.Then creating DataSource object for each environment requires different database details.

If we change Database details, then we need to rebuild and deploy application. We cannot use same application WAR or EAR file for all environments.To solve this kind of environment related setup dependencies, Spring 3.1 has introduced a new annotation. That is @Profile annotation. It can be used to develop an “If-Then-Else” conditional checking to configure. We cannot implement this scenario by using SpEL Ternary Operator.

To work with Profiles, Spring 3.1 Framework has provided the following two properties

spring.profiles.default
spring.profiles.active
spring.profiles.active represents active profile.

spring.profiles.default represents default profile.

If we don’t specify active profile, then Spring IOC Container will look for default profile. We need to provide values to one of these properties as JVM Parameters. In Eclipse or Spring STS IDEs, we need to pass these values as shown below,

-Dspring.profiles.active=dev

Then Spring IOC Container uses this profile value and creates only those beans to run the application. To activate the profiles in JUnits, Spring Framework has provided another annotation: @ActiveProfiles. Instead of activating a profile using JVM Parameters,

Profiles:
1) @Profile: to specify profile for specific bean/class
2) @ActiveProfiles : over test to specify profile
3) Run project with -Dspring.profiles.active=test
4) as a context param in web.xml: 
<context-param>
<param-name>spring.profiles.active</param-name>
<param-value>dev</param-value>
</context-param>
5) In webapplicationInitializer: onStartUp(ctx): ctx.setInitParameter("spring.profiles.active","dev");



    @Profile("dev") 
    
Only present in dev environment, not also on prod

	@Profile("test")
Only for test env

	@Profile("!test")
For env other than test

	<beans profile="test">
	</beans>

	Priority Order
1) Web.xml context-param (highest preference)
2) WebApplicationInitializer
3) JVM system property
4) Environment variable
5) Maven profile


	

