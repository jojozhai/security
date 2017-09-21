/**
 * 
 */
package com.imooc.security.rbac.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/**
 * @author zhailiang
 *
 */
public interface RbacService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
