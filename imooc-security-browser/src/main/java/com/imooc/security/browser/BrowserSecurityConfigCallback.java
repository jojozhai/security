/**
 * 
 */
package com.imooc.security.browser;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author zhailiang
 *
 */
public interface BrowserSecurityConfigCallback {
	
	void config(HttpSecurity http);

}
