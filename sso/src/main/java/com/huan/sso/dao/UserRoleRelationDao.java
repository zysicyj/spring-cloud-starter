package com.huan.sso.dao;

import com.huan.common.model.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户角色关联SQL管理
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/10
 */
public interface UserRoleRelationDao extends JpaRepository<UserRoleRelation, Long> {
    /**
     * 根据用户id获取用户权限
     *
     * @param userId 用户id
     *
     * @return java.util.List<com.huan.sso.model.UserRole>
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/10
     * @since 1.0.0
     */
    List<UserRoleRelation> findByUserId(Long userId);
}
