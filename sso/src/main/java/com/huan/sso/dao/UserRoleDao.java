package com.huan.sso.dao;

import com.huan.common.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户权限sql管理
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/10
 */
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    /**
     * 根据用户id集合获取权限
     *
     * @param userIdList 用户id集合
     *
     * @return java.util.List<com.huan.sso.model.UserRole>
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/10
     * @since 1.0.0
     */
    List<UserRole> findAllByIdIn(List<Long> userIdList);
}
