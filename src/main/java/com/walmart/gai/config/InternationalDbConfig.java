package com.walmart.gai.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration  
@EnableJpaRepositories(
		 	entityManagerFactoryRef = "gaiInternationalEntityManager",
		    transactionManagerRef = "gaiInternationalTwoTransactionManager",
		    basePackages = {"com.walmart.gai.dao.repositoryInternational"})
public class InternationalDbConfig {
	
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
		/*properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("spring.h2.console.enabled", "true");*/
		
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		
		return properties;
	}
	
	/*@Bean
	public DataSource secondaryDataSource(){
		return DataSourceBuilder.create()
				.url("jdbc:h2:mem:testdb")
				.driverClassName("org.h2.Driver")
				.username("sa")
				.password("password")
				.build();
	}	*/
	
	@Bean(name = "secondaryDataSource")
	public DataSource secondaryDataSource(){
		return DataSourceBuilder.create()
				.url("jdbc:db2://DSNVDRDA.wal-mart.com:49422/DSNV") //- ECGBUASC 
				.driverClassName("com.ibm.db2.jcc.DB2Driver")
				.username("remotec")
				.password("tempsox1")
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
    //@Autowired
    DataSourceTransactionManager tm2(@Qualifier("secondaryDataSource") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(secondaryDataSource());
        return txm;
    }
	
	/*@Bean(name = "gaiInternationalTwoTransactionManager")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("gaiInternationalEntityManager") EntityManagerFactory gaiInternationalEntityManager) {
		return new JpaTransactionManager(gaiInternationalEntityManager);
	  }*/
	
}