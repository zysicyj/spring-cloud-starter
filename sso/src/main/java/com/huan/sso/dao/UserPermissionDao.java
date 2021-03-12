package com.huan.sso.dao;

import com.huan.common.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 用户权限SQL管理
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/10
 */
public interface UserPermissionDao extends JpaRepository<UserPermission, Long> {
    /**
     * 根据请求的url查找是否有权限
     *
     * @param requestUrl 请求的url
     *
     * @return java.util.List<com.huan.sso.model.UserPermission>
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/10
     * @since 1.0.0
     */
    UserPermission findByUrl(String requestUrl);
}
