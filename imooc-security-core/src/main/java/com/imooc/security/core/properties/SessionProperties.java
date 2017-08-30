/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * @author zhailiang
 *
 */
public class SessionProperties {
	
	private int maximumSessions = 1;
	
	private boolean maxSessionsPreventsLogin;
	
	private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

	public int getMaximumSessions() {
		return maximumSessions;
	}

	public void setMaximumSessions(int maximumSessions) {
		this.maximumSessions = maximumSessions;
	}

	public boolean isMaxSessionsPreventsLogin() {
		return maxSessionsPreventsLogin;
	}

	public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
		this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
	}

	public String getSessionInvalidUrl() {
		return sessionInvalidUrl;
	}

	public void setSessionInvalidUrl(String sessionInvalidUrl) {
		this.sessionInvalidUrl = sessionInvalidUrl;
	}
	
}
