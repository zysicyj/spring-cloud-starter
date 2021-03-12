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
@Table(name = "user_role_relation")
public class UserRoleRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "customIdGenerator")
    @GenericGenerator(name = "customIdGenerator", strategy = "com.huan.common.util.CustomIdGenerator")
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;
    /**
     * 记录更新时间
     */
    private LocalDateTime modifyTime;
    /**
     * 创建人
     */
    private Long createUid;
}

