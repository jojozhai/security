/**
 * 
 */
package com.imooc.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.imooc.security.app.jwt.ImoocJwtTokenEnhancer;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * @author zhailiang
 *
 */
@Configuration
public class TokenStoreConfig {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	@ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "tokenStore", havingValue = "redis")
	public TokenStore redisTokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	@Configuration
	@ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
	public static class JwtConfig {
		
		@Autowired
		private SecurityProperties securityProperties;
		
		@Bean
		public TokenStore jwtTokenStore() {
			return new JwtTokenStore(jwtAccessTokenConverter());
		}
		
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter(){
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
	        return converter;
		}
		
		@Bean
		@ConditionalOnBean(TokenEnhancer.class)
		public TokenEnhancer jwtTokenEnhancer(){
			return new ImoocJwtTokenEnhancer();
		}
		
	}
	
	

}
