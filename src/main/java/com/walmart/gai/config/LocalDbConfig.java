package com.walmart.gai.config;

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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration  
@EnableTransactionManagement
@EnableJpaRepositories(
		 	entityManagerFactoryRef = "gaiLocalEntityManager",
		    transactionManagerRef = "gaiLocalTransactionManager",
		    basePackages = {"com.walmart.gai.dao.repositoryLocal"})
public class LocalDbConfig {
	
	/*@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.driverClassName}")
	private String dbDriver;
	
	@Value("${spring.datasource.username}")
	private String userName;
	
	@Value("${spring.datasource.password}")
	private String password;*/
	
	//@Primary
	@Bean(name = "gaiLocalEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
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
		//properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("", "");
		
		return properties;
	}
	
	//@Primary
	@Bean(name = "primaryDataSource")
	public DataSource dataSource(){
		return DataSourceBuilder.create()
				.url("jdbc:db2://DSNSDRDA.wal-mart.com:49543/DSNS") //- ECGBUASC 
				.driverClassName("com.ibm.db2.jcc.DB2Driver")
				.username("remotec")
				.password("tempsox1")
				.build();
	}	
	
	@Primary
	@Bean(name = "gaiLocalTransactionManager")
	public JpaTransactionManager transactionManager(@Qualifier("gaiLocalEntityManager")  EntityManagerFactory gaiLocalEntityManager){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(gaiLocalEntityManager);		
		transactionManager.setDataSource(dataSource());
		transactionManager.setPersistenceUnitName("gaiLocal");
		return transactionManager;
	}
	
	@Bean(name="tm1")
    @Autowired
    //@Primary
    DataSourceTransactionManager tm1(@Qualifier("primaryDataSource") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(dataSource());
        return txm;
    }
	
	//@Primary
	/*@Bean(name = "gaiLocalTransactionManager")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("gaiLocalEntityManager") EntityManagerFactory gaiLocalEntityManager) {
		return new JpaTransactionManager(gaiLocalEntityManager);
	  }*/
}