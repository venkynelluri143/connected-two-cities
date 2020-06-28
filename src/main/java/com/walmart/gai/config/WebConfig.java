package com.walmart.gai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.gai.security.ADUserDetailsContextMapper;
import com.walmart.gai.security.SecurityFilter;
import com.walmart.gai.validator.AssociateIdentifierValidator;

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
	
	@Value("${ad.provider.url}")
	private String adProviderURL;
	
	@Value("${ad.search.base}")
	private String searchBase;
	
	@Value("${ad.domain}")
	private String adDomain;
    
    @Bean
    AssociateIdentifierValidator getAssociateIdentifierValidator(){
	return new AssociateIdentifierValidator();
    }
    
    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(adDomain, adProviderURL, searchBase);
        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);
        provider.setUserDetailsContextMapper(new ADUserDetailsContextMapper());
        return provider;
    }
    
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    ObjectMapper mapper = new ObjectMapper();
	    //MyObjectMapper mapper = new MyObjectMapper();
	    //mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    MappingJackson2HttpMessageConverter converter = 
	        new MappingJackson2HttpMessageConverter(mapper);
	    //converter.setObjectMapper(mapper);
	    return converter;
	}
    
    @Bean 
    public SecurityFilter securityFilter(){
    	return new SecurityFilter();
    }
	
}