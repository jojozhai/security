/**
 * 
 */
package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.imooc.security.browser.session.ImoocExpiredSessionStrategy;
import com.imooc.security.browser.session.ImoocInvalidSessionStrategy;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * @author zhailiang
 *
 */
@Configuration
public class BrowserSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new ImoocInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}
	
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new ImoocExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}
	
}
