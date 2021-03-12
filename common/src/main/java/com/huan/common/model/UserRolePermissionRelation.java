package com.huan.common.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_role_permission_relation")
public class UserRolePermissionRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(generator = "customIdGenerator")
    @GenericGenerator(name = "customIdGenerator", strategy = "com.huan.common.util.CustomIdGenerator")
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;
    /**
     * 创建人
     */
    private Long createUid;
    /**
     *
     */
    private LocalDateTime createTime;
    /**
     *
     */
    private LocalDateTime modifyTime;
}

