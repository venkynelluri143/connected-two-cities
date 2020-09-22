package com.walmart.gai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.walmart.gai.security.ADUserDetailsContextMapper;
import com.walmart.gai.security.SecurityFilter;
import com.walmart.gai.validator.AssociateIdentifierValidator;
import com.walmart.gbs.corehr.CryptoUtil;

@Configuration
@EnableTransactionManagement
public class WebConfig {
	
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
    public SecurityFilter securityFilter(){
    	return new SecurityFilter();
    }
    
    @Bean CryptoUtil cryptoUtil() {
    	return new CryptoUtil();
    }
	
}