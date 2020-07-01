/**
 * 
 */
package com.walmart.gai.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.Person.Essence;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import com.walmart.gai.util.Constants;


/**
 * @author s0m05lw
 *
 */
@Component
public class ADUserDetailsContextMapper implements UserDetailsContextMapper, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ADUserDetailsContextMapper.class);

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		List<GrantedAuthority> mappedAuthorities = new ArrayList<>();
		Essence essence= new Essence(ctx);
		essence.setUsername(username);
		String[] members = ctx.getStringAttributes(Constants.MEMBEROF);
		mappedAuthorities = buildUserAuthority(members);
		essence.setAuthorities(mappedAuthorities);
		UserDetails userDetails=essence.createUserDetails();
		LOGGER.info("Get Authorities:" +userDetails.getAuthorities());
		return userDetails;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		// Do nothing - default implementation can ignored 
		
	}
	
	private List<GrantedAuthority> buildUserAuthority(String[] members) {
	    Set<GrantedAuthority> setAuths = new HashSet<>();
	    // Build user's authorities
	    if(members != null){
	    	Arrays.stream(members) 
	        .forEach(e->setAuths.add(new SimpleGrantedAuthority(e))); 
	    }
	    return new ArrayList<>(setAuths);
	}

}
