package com.walmart.gai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.walmart.gai.dao.mapper.ResourceMapper;
import com.walmart.gai.validator.AssociateIdentifierValidator;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
@EnableTransactionManagement
public class WebConfig {
	
	/*@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.driverClassName}")
	private String dbDriver;
	
	@Value("${spring.datasource.username}")
	private String userName;
	
	@Value("${spring.datasource.password}")
	private String password;	
	
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddl;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.show_sql}")
	private String show_sql;
   
	
	@Bean
	public DataSource dataSource() {		
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);		
		return dataSource;
	}
*/	
	@Bean
    MapperFacade mapperFacade() {
	MapperFactory factory = new DefaultMapperFactory.Builder().build();
	return factory.getMapperFacade();
    }

    @Bean
    public ResourceMapper resourceMapper() {
	return new ResourceMapper();
    } 
    
    @Bean
    AssociateIdentifierValidator getAssociateIdentifierValidator(){
	return new AssociateIdentifierValidator();
    }
	
}