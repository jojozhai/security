/**
 * 
 */
package com.imooc.security.core.social.qq.config;

import com.imooc.security.core.properties.QQProperties;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;

/**
 * @author zhailiang
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;


	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		connectionFactoryConfigurer.addConnectionFactory(new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret()));
	}

	@Override
	public UserIdSource getUserIdSource() {
		return () -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null) {
				throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
			} else {
				return authentication.getName();
			}
		};
	}
}
