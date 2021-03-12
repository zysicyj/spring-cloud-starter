package com.huan.sso.service;

import com.huan.common.constants.Request.RequestConstants;
import com.huan.sso.util.user.UserUtil;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * URL拦截校验
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/12
 * @since 1.0.0
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    /**
     * 返回该url所需要的用户权限信息
     *
     * @param object: 储存请求url信息
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 忽略url请放在此处进行过滤放行
        String loginFilter = "/info";
        String logoutFilter = "/logout";
        String oauthFilter = "/oauth";
        if (loginFilter.equals(requestUrl) || requestUrl.contains(logoutFilter) || requestUrl.contains(oauthFilter)) {
            return null;
        }
        if (UserUtil.getCurrentUser() == null) {
            return SecurityConfig.createList(RequestConstants.ROLE_LOGIN);
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}