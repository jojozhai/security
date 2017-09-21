/**
 * 
 */
package com.imooc.security.rbac.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 管理员(用户)
 * 
 * @author zhailiang
 *
 */
@Entity
public class Admin implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3521673552808391992L;
	/**
	 * 数据库主键
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdTime;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户的所有角色
	 */
	@OneToMany(mappedBy = "admin", cascade = CascadeType.REMOVE)
	private Set<RoleAdmin> roles = new HashSet<>();
	/**
	 * 用户有权访问的所有url，不持久化到数据库
	 */
	@Transient
	private Set<String> urls = new HashSet<>();
	/**
	 * 用户有权的所有资源id，不持久化到数据库
	 */
	@Transient
	private Set<Long> resourceIds = new HashSet<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	/**
	 * @return
	 */
	public Set<Long> getAllResourceIds() {
		init(resourceIds);
		forEachResource(resource -> resourceIds.add(resource.getId()));
		return resourceIds;
	}
	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the roles
	 */
	public Set<RoleAdmin> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<RoleAdmin> roles) {
		this.roles = roles;
	}

	/**
	 * @return the urls
	 */
	public Set<String> getUrls() {
		init(urls);
		forEachResource(resource -> urls.addAll(resource.getUrls()));
		return urls;
	}
	
	/**
	 * @param data
	 * @param consumer
	 */
	private void init(Set<?> data){
		if (CollectionUtils.isEmpty(data)) {
			if (data == null) {
				data = new HashSet<>();
			}
		}
	}
	/**
	 * @param consumer
	 */
	private void forEachResource(Consumer<Resource> consumer) {
		for (RoleAdmin role : roles) {
			for (RoleResource resource : role.getRole().getResources()) {
				consumer.accept(resource.getResource());
			}
		}
	}
	

	/**
	 * @param urls
	 *            the urls to set
	 */
	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

}
