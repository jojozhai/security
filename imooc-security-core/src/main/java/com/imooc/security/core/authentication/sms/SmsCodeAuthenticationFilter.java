/**
 * 
 */
package com.imooc.security.core.authentication.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.ServletRequestUtils;

/**
 * @author zhailiang
 *
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		if(!StringUtils.equalsIgnoreCase("post", request.getMethod())){
			throw new AuthenticationServiceException("登录请求只支持POST方法");
		}
		
		String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
		
		if(StringUtils.isBlank(mobile)){
			throw new AuthenticationServiceException("手机号不能为空");
		}
		
		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
		
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		
		return getAuthenticationManager().authenticate(authRequest);
	}

}
