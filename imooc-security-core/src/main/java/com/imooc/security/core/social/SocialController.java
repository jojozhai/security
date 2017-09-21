/**
 * 
 */
package com.imooc.security.core.social;

import org.springframework.social.connect.Connection;

import com.imooc.security.core.social.support.SocialUserInfo;

/**
 * @author zhailiang
 *
 */
public abstract class SocialController {

	/**
	 * 根据Connection信息构建SocialUserInfo
	 * @param connection
	 * @return
	 */
	protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
		SocialUserInfo userInfo = new SocialUserInfo();
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
	}
	
}
