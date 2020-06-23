package com.walmart.gai.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.AuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class LdapAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	AuthenticationProvider activeDirectoryLdapAuthenticationProvider;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LdapAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication auth = null; 
		try {
			auth = activeDirectoryLdapAuthenticationProvider.authenticate(authentication);
		} catch (BadCredentialsException e) {
			LOGGER.info("Authentication BadCredentials Exception:" + e.getMessage());
		} catch (Exception e) {
			LOGGER.info("Authenticaton Exception:" + e.getMessage());
		}
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
