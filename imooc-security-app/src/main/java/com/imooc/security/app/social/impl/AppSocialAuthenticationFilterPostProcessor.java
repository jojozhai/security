/**
 * 
 */
package com.imooc.security.app.social.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.imooc.security.core.social.SocialAuthenticationFilterPostProcessor;

/**
 * @author zhailiang
 *
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {
	
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

	/* (non-Javadoc)
	 * @see com.imooc.security.core.social.SocialAuthenticationFilterPostProcessor#process(org.springframework.social.security.SocialAuthenticationFilter)
	 */
	@Override
	public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
		socialAuthenticationFilter.setAuthenticationSuccessHandler(imoocAuthenticationSuccessHandler);
	}

}
