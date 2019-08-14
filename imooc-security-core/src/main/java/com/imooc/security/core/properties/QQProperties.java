/**
 * 
 */
package com.imooc.security.core.properties;


/**
 * QQ登录配置项
 * 
 * 
 * @author zhailiang
 *
 */
public class QQProperties {
	
	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 qq。
	 */
	private String providerId = "qq";

	private String appId;
	private String appSecret;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
}
