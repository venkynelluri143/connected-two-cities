package com.walmart.gai.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration  
@EnableTransactionManagement
@EnableJpaRepositories(
		 	entityManagerFactoryRef = "gaiInternationalEntityManager",
		    transactionManagerRef = "gaiInternationalTwoTransactionManager",
		    basePackages = {"com.walmart.gai.dao.repositoryinternational"})
public class InternationalDbConfig {
	
	@Value("${spring.globaldatasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.driverClassName}")
	private String dbDriver;
	
	@Value("${spring.datasource.username}")
	private String userName;
	
	@Value("${spring.datasource.cred}")
	private String password;
	
	@Value("${spring.hibernate.dialect}")
	private String hibernateDialect;
	
	@Value("${spring.hibernate.show_sql}")
	private String showSql;
	
	@Bean(name = "gaiInternationalEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(secondaryDataSource());
		em.setPackagesToScan(new String[] {"com.walmart.gai.dao"});
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalJpaProperties());
		em.setPersistenceUnitName("gaiInternational");
		return em;
		}
	
		Properties additionalJpaProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", hibernateDialect);
		properties.setProperty("hibernate.show_sql", showSql);
		
		return properties;
	}
	
	@Bean(name = "secondaryDataSource")
	public DataSource secondaryDataSource(){
		return DataSourceBuilder.create()
				.url(dbUrl) //- ECGBUASC 
				.driverClassName(dbDriver)
				.username(userName)
				.password(password)
				.build();
	}	
	
	@Bean(name = "gaiInternationalTwoTransactionManager")
	public JpaTransactionManager transactionManager(@Qualifier("gaiInternationalEntityManager") EntityManagerFactory gaiInternationalEntityManager){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(gaiInternationalEntityManager);	
		transactionManager.setDataSource(secondaryDataSource());
		transactionManager.setPersistenceUnitName("gaiInternational");
		return transactionManager;
	}
	
	@Bean(name="tm2")
    DataSourceTransactionManager tm2(@Qualifier("secondaryDataSource") DataSource datasource) {
        return new DataSourceTransactionManager(secondaryDataSource());
    }
	
}