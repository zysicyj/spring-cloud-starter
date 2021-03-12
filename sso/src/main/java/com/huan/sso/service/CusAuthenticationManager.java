package com.huan.sso.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 这里指定自己的认证管理策略
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Component
public class CusAuthenticationManager implements AuthenticationManager {

    private final AdminAuthenticationProvider adminAuthenticationProvider;

    public CusAuthenticationManager(AdminAuthenticationProvider adminAuthenticationProvider) {
        this.adminAuthenticationProvider = adminAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication result = adminAuthenticationProvider.authenticate(authentication);
        if (Objects.nonNull(result)) {
            return result;
        }
        throw new ProviderNotFoundException("Authentication failed!");
    }
}