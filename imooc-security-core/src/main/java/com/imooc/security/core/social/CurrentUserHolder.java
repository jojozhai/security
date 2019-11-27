/**
 * 
 */
package com.imooc.security.core.social;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.UserIdSource;

/**
 * @author zhailiang
 *
 */
public class CurrentUserHolder implements UserIdSource {

	/* (non-Javadoc)
	 * @see org.springframework.social.UserIdSource#getUserId()
	 */
	@Override
	public String getUserId() {
		String userId = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if(context != null) {
			Object principal = context.getAuthentication().getPrincipal();
			if(principal instanceof UserDetails) {
				userId = ((UserDetails)principal).getUsername();
			}
		}
		if(StringUtils.isBlank(userId)) {
			userId = RandomStringUtils.randomNumeric(4);
		}
		return userId;
	}

}
