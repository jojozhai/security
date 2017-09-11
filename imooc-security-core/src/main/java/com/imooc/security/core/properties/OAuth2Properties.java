/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * @author zhailiang
 *
 */
public class OAuth2Properties {
	
	private String jwtSigningKey = "imooc";
	
	private OAuth2ClientProperties[] clients = {};

	public OAuth2ClientProperties[] getClients() {
		return clients;
	}

	public void setClients(OAuth2ClientProperties[] clients) {
		this.clients = clients;
	}

	public String getJwtSigningKey() {
		return jwtSigningKey;
	}

	public void setJwtSigningKey(String jwtSigningKey) {
		this.jwtSigningKey = jwtSigningKey;
	}
	
}
