/**
 * 
 */
package com.imooc.security.rbac.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;

/**
 * @author zhailiang
 */
@Component
@Order(Integer.MAX_VALUE)
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {

	/* (non-Javadoc)
	 * @see com.imooc.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config
			.antMatchers(HttpMethod.GET, "/fonts/**").permitAll()
			.antMatchers(HttpMethod.GET, 
					"/**/*.html",
					"/admin/me",
					"/resource").authenticated()
			.anyRequest()
				.access("@rbacService.hasPermission(request, authentication)");
		return true;
	}

}
