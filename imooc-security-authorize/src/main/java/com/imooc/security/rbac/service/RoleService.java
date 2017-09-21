/**
 * 
 */
package com.imooc.security.rbac.service;

import java.util.List;

import com.imooc.security.rbac.dto.RoleInfo;

/**
 * 角色服务
 * 
 * @author zhailiang
 *
 */
public interface RoleService {
	
	/**
	 * 创建角色
	 * @param roleInfo
	 * @return
	 */
	RoleInfo create(RoleInfo roleInfo);
	/**
	 * 修改角色
	 * @param roleInfo
	 * @return
	 */
	RoleInfo update(RoleInfo roleInfo);
	/**
	 * 删除角色
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 获取角色详细信息
	 * @param id
	 * @return
	 */
	RoleInfo getInfo(Long id);
	/**
	 * 查询所有角色
	 * @param condition
	 * @return
	 */
	List<RoleInfo> findAll();
	/**
	 * @param id
	 * @return
	 */
	String[] getRoleResources(Long id);
	/**
	 * @param id
	 * @param ids
	 */
	void setRoleResources(Long id, String ids);

}
