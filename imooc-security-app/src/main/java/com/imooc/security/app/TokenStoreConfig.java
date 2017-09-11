/**
 * 
 */
package com.imooc.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author zhailiang
 *
 */
@Configuration
public class TokenStoreConfig {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

}
