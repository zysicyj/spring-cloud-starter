package com.huan.sso.util.login;

import com.huan.common.model.SecurityUser;
import com.huan.sso.constants.serviceEnum.UserEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * 用户登录校验工具
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/9
 */
@Slf4j
public class UserLoginValidateUtil {
    /**
     * 校验用户信息是否有效
     *
     * @return boolean
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/9
     * @since 1.0.0
     */
    public static boolean checkUserIsValid(String password, SecurityUser securityUser) {
        return securityUser != null &&
                PasswordUtils.isValidPassword(password, securityUser.getPassword(), securityUser.getSalt())
                && securityUser.isAccountNonLocked()
                && securityUser.isAccountNonExpired()
                && securityUser.isCredentialsNonExpired()
                && securityUser.isEnabled()
                && StringUtils.equals(UserEnum.Status.USUAL.getValue(), securityUser.getStatus());
    }
}
