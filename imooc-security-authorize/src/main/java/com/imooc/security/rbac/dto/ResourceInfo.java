/**
 * 
 */
package com.imooc.security.rbac.dto;

import java.util.ArrayList;
import java.util.List;

import com.imooc.security.rbac.domain.ResourceType;

/**
 * @author zhailiang
 *
 */
public class ResourceInfo {
	
	/**
	 * 资源ID
	 *
	 * @since 1.0.0
	 */
	private Long id;
	/**
	 * 
	 */
	private Long parentId;
	/**
	 * 资源名
	 *
	 * @since 1.0.0
	 */
	private String name;
	/**
	 * 资源链接
	 *
	 * @since 1.0.0
	 */
	private String link;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 资源类型
	 */
	private ResourceType type;
	/**
	 * 子节点
	 */
	private List<ResourceInfo> children = new ArrayList<>();

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
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the children
	 */
	public List<ResourceInfo> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ResourceInfo> children) {
		this.children = children;
	}

	/**
	 * @return the type
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}
	
}
