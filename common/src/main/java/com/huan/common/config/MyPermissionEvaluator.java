package com.huan.common.config;

import cn.hutool.core.collection.CollUtil;
import com.huan.common.model.SecurityUser;
import com.huan.common.model.UserPermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;


/**
 * 自定义权限处理
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/2/26
 */
@Slf4j
@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        List<UserPermission> permissionList = principal.getPermissionList();
        if (CollUtil.isNotEmpty(permissionList)) {
            return permissionList.stream().anyMatch(x -> StringUtils.equals(x.getUrl(), (CharSequence) targetDomainObject) &&
                    StringUtils.equals(x.getCode(), (CharSequence) permission));
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
