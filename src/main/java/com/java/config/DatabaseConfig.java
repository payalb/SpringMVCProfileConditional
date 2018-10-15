package com.java.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass=true)
/*@PropertySource(value="classpath:/database-${spring.profiles.active}.properties")*/
public class DatabaseConfig {

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.jpa.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.show-sql}")
	private String showSql;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hbm2ddl;
	
	@Value("${entitymanager.packagesToScan}")
	private String pkg;

	@Bean(name="flyway", initMethod="migrate")
	public Flyway getFlyway() {
		Flyway flyway= new Flyway() ;
		flyway.setDataSource(getDatasource());
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:/migration");
		return flyway;
	}

	@Bean
	public DataSource getDatasource() {
		DriverManagerDataSource ds= new DriverManagerDataSource();
		ds.setUrl(url);
		System.out.println("url is"+ url);
		ds.setDriverClassName(driverClassName);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean("transactionManager")
	public HibernateTransactionManager getTransactionManager() throws IOException {
		HibernateTransactionManager tx= new HibernateTransactionManager();
		tx.setDataSource(getDatasource());
		tx.setSessionFactory(sf());
		return tx;
	}
	
	@Bean("sessionFactory")
	@DependsOn("flyway")
	public SessionFactory sf() throws IOException {
		LocalSessionFactoryBean bean= new LocalSessionFactoryBean();
		bean.setDataSource(getDatasource());
		bean.setPackagesToScan(pkg);
		bean.setHibernateProperties(properties());
		bean.afterPropertiesSet();
		System.out.println("sf is"+ bean);
		return bean.getObject();
	}

	private Properties properties() {
		Properties p= new Properties();
		//p.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
		p.setProperty("hibernate.show_sql", showSql);
		p.setProperty("hibernate.dialect", dialect);
		return p;
	}
	
	
	
}
