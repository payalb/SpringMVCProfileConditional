package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan("com.java")
@Import(DatabaseConfig.class)
public class SpringConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Bean("pr")
	@Conditional(DevProdEnvironmentConfig.class)
	public PropertySourcesPlaceholderConfigurer getConfigurerDev() {
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("database.properties"));
		return cfg;
	}

	@Bean("pr")

	@Conditional(TestEnvironmentConfig.class)
	public PropertySourcesPlaceholderConfigurer getConfigurerTest() {
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("database-test.properties"));
		return cfg;
	}

	/*
	 * @Bean("pr")
	 * 
	 * @Profile("prod") public PropertySourcesPlaceholderConfigurer
	 * getConfigurerProd() { PropertySourcesPlaceholderConfigurer cfg= new
	 * PropertySourcesPlaceholderConfigurer(); cfg.setLocation(new
	 * ClassPathResource("database-prod.properties")); return cfg; }
	 */
}
