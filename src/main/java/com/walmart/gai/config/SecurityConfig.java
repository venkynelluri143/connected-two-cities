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
import com.walmart.gai.security.SecurityFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	LdapAuthenticationProvider ldapAuthenticationProvider;

	@Autowired
	SecurityFilter securityFilter;
	
	/*@Override
    public void configure(HttpSecurity http) throws Exception {
		   http
		   .antMatcher("/**")
		   //.requestMatcher(new RequestHeaderRequestMatcher("Authorization")).oauth2Login().tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient)
           .authorizeRequests().anyRequest().fullyAuthenticated();
		
		
        http.antMatcher("/**").authorizeRequests()
            .antMatchers("/", "/login**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login();
    }*/
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests().antMatchers("/swagger-ui.html").authenticated();
    	http.httpBasic();
    	http.csrf().disable();
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	 
    	/* http.authorizeRequests().anyRequest().fullyAuthenticated();
     	 http.httpBasic().authenticationEntryPoint(new SecurityAuthenticationEntryPoint());
    	 http.exceptionHandling()
    	 .authenticationEntryPoint(new SecurityAuthenticationEntryPoint());
    	 http.csrf().disable();*/
    }
    
    @Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(ldapAuthenticationProvider);
    } 
	
	/*private AuthorizationRequestRepository<OAuth2AuthorizationRequest> cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}*/
}
