/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * @author zhailiang
 *
 */
public class LogoutProperties {
	
	private String signOutSuccessUrl;
	
	private String[] deleteCookies;

	public String getSignOutSuccessUrl() {
		return signOutSuccessUrl;
	}

	public void setSignOutSuccessUrl(String signOutUrl) {
		this.signOutSuccessUrl = signOutUrl;
	}

	public String[] getDeleteCookies() {
		return deleteCookies;
	}

	public void setDeleteCookies(String[] deleteCookies) {
		this.deleteCookies = deleteCookies;
	}
	
}
