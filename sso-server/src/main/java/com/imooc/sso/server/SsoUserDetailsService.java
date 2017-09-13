/**
 * 
 */
package com.imooc.sso.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class SsoUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(username, passwordEncoder.encode("123456"), 
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
	}

}
