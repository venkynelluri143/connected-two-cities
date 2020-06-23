/**
 * 
 */
package com.walmart.gai.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
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
	
	private static Logger LOGGER = LoggerFactory.getLogger(ADUserDetailsContextMapper.class);
	public  static List<String> memberGroups = new ArrayList<>();

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		List<GrantedAuthority> mappedAuthorities = new ArrayList<GrantedAuthority>();
		Essence essence= new Essence(ctx);
		essence.setUsername(username);
		String[] members = ctx.getStringAttributes(Constants.memberOf);	
		memberGroups = Arrays.asList(members);
		LOGGER.info("Process Id MemberGroups :"+memberGroups);
		UserDetails userDetails=essence.createUserDetails();
		return userDetails;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		// TODO Auto-generated method stub
		
	}
	
	public List<String> getMemberGroups(){
		return memberGroups;
	}

}
