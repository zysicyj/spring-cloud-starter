package com.huan.sso.dao;

import com.huan.common.model.UserRolePermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户权限角色关联管理
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/10
 */
public interface UserRolePermissionRelationDao extends JpaRepository<UserRolePermissionRelation, Long> {
    /**
     * 根据权限id获取关联信息
     *
     * @param id 权限id
     *
     * @return java.util.List<com.huan.sso.model.UserRolePermissionRelation>
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/10
     * @since 1.0.0
     */
    List<UserRolePermissionRelation> findAllByPermissionId(Long id);

    /**
     * 根据角色id获取权限列表
     *
     * @param idList 角色id列表
     *
     * @return java.util.List<com.huan.sso.model.UserRolePermissionRelation>
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/11
     * @since 1.0.0
     */
    List<UserRolePermissionRelation> findAllByRoleIdIn(List<Long> idList);
}
