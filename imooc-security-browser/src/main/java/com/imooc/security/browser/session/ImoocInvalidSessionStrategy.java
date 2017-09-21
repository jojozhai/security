/**
 * 
 */
package com.imooc.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;

import com.imooc.security.core.properties.SecurityProperties;

/**
 * 默认的session失效处理策略
 * 
 * @author zhailiang
 *
 */
public class ImoocInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public ImoocInvalidSessionStrategy(SecurityProperties securityProperties) {
		super(securityProperties);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}

}
