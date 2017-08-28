/**
 * 
 */
package com.imooc.security.core.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("imooc_");
		return repository;
	}
	
	@Bean
	public SpringSocialConfigurer imoocSocialSecurityConfig() {
		return new SpringSocialConfigurer();
	}
	
}
