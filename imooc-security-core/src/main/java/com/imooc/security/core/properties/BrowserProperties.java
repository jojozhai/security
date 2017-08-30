/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * @author zhailiang
 *
 */
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String signUpUrl = "/imooc-signUp.html";
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private LoginResponseType loginType = LoginResponseType.JSON;
	
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}
	
}
