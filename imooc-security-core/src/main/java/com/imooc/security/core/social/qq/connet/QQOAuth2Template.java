/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhailiang
 *
 */
public class QQOAuth2Template extends OAuth2Template {

	public QQOAuth2Template(String clientId, String clientSecret, String authenticateUrl,String accessTokenUrl) {
		super(clientId, clientSecret, authenticateUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}
	
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		
		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		
		System.out.println(responseStr);
		
		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
		
		String accessToken = StringUtils.substringAfter(items[0], "=");
		Long expiresIn = new Long(StringUtils.substringAfter(items[1], "="));
		String refreshToken = StringUtils.substringAfter(items[2], "=");
		
		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}
	
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
