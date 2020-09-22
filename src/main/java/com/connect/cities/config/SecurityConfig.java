package com.walmart.gai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.walmart.gai.security.LdapAuthenticationProvider;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LdapAuthenticationProvider ldapAuthenticationProvider;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
    	http.authorizeRequests().antMatchers("/swagger-ui.html").authenticated();
    	http.httpBasic();
    	http.csrf().disable();
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    }
    
    @Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(ldapAuthenticationProvider);
    } 

}