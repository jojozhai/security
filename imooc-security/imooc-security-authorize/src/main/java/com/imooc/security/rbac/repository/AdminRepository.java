/**
 *
 */
package com.imooc.security.rbac.repository;

import com.imooc.security.rbac.domain.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author zhailiang
 *
 */
@Repository
public interface AdminRepository extends ImoocRepository<Admin> {

    Admin findByUsername(String username);

}
