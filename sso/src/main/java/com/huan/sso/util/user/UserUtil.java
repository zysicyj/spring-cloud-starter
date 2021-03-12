package com.huan.sso.util.user;

import com.huan.common.model.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * 用户工具类
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/11
 */
@Slf4j
public class UserUtil {
    /**
     * 获取当前登录用户身份信息
     *
     * @return com.huan.sso.model.SecurityUser
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/11
     * @since 1.0.0
     */
    public static SecurityUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof SecurityUser) {
            return (SecurityUser) principal;
        } else {
            return null;
        }
    }
}
