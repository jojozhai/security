/**
 *
 */
package com.imooc.security.rbac.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色用户关系表
 *
 * @author zhailiang
 *
 */
@Entity
public class RoleAdmin {

    /**
     * 数据库表主键
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
     * 角色
     */
    @ManyToOne
    private Role role;
    /**
     * 管理员
     */
    @ManyToOne
    private Admin admin;

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
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the admin
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
