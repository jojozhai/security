/**
 * 
 */
package com.imooc.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imooc.security.rbac.dto.AdminCondition;
import com.imooc.security.rbac.dto.AdminInfo;

/**
 * 管理员服务
 * 
 * @author zhailiang
 *
 */
public interface AdminService {

	/**
	 * 创建管理员
	 * @param adminInfo
	 * @return
	 */
	AdminInfo create(AdminInfo adminInfo);
	/**
	 * 修改管理员
	 * @param adminInfo
	 * @return
	 */
	AdminInfo update(AdminInfo adminInfo);
	/**
	 * 删除管理员
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 获取管理员详细信息
	 * @param id
	 * @return
	 */
	AdminInfo getInfo(Long id);
	/**
	 * 分页查询管理员
	 * @param condition
	 * @return
	 */
	Page<AdminInfo> 	query(AdminCondition condition, Pageable pageable);

}
