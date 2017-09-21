/**
 * 
 */
package com.imooc.security.rbac.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author zhailiang
 *
 */
public class AdminInfo {
	
	private Long id;
	/**
	 * 角色id 
	 */
	@NotBlank(message = "角色id不能为空")
	private Long roleId;
	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	private String username;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
}