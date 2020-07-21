package com.walmart.gai.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.walmart.gai.globalfilter.GlobalFilter;
import com.walmart.gai.util.Constants;

@Configuration  
@EnableTransactionManagement
@EnableJpaRepositories(
		 	entityManagerFactoryRef = "gaiLocalEntityManager",
		    transactionManagerRef = "gaiLocalTransactionManager",
		    basePackages = {"com.walmart.gai.dao.repositorylocal"})
public class LocalDbConfig {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.driverClassName}")
	private String dbDriver;
	
	@Value("${spring.hibernate.dialect}")
	private String hibernateDialect;
	
	@Value("${spring.hibernate.show_sql}")
	private String showSql;
	
	@Autowired
	private GlobalFilter globalFilter;
	
	@Primary
	@Bean(name = "gaiLocalEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException{
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"com.walmart.gai.dao"});
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalJpaProperties());
		em.setPersistenceUnitName("gaiLocal");
		return em;
	}
	
		Properties additionalJpaProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", hibernateDialect);
		properties.setProperty("hibernate.show_sql", showSql);
		return properties;
	}
	
	@Primary
	@Bean(name = "primaryDataSource")
	public DataSource dataSource() throws IOException{
		return DataSourceBuilder.create()
				.url(dbUrl) //- ECGBLASC 
				.driverClassName(dbDriver)
				.username(globalFilter.getPropValues(Constants.USERNAME))
				.password(globalFilter.getPropValues(Constants.USERCRED))
				.build();
	}	
	
	@Primary
	@Bean(name = "gaiLocalTransactionManager")
	public JpaTransactionManager transactionManager(@Qualifier("gaiLocalEntityManager")  EntityManagerFactory gaiLocalEntityManager) throws IOException{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(gaiLocalEntityManager);		
		transactionManager.setDataSource(dataSource());
		transactionManager.setPersistenceUnitName("gaiLocal");
		return transactionManager;
	}
	
	@Bean(name="tm1")
    @Primary
    DataSourceTransactionManager tm1(@Qualifier("primaryDataSource") DataSource datasource) throws IOException {
		return new DataSourceTransactionManager(dataSource());
    }

}