package com.huan.sso.filter;

import com.alibaba.fastjson.JSONObject;
import com.huan.common.constants.Request.RequestConstants;
import com.huan.common.model.User;
import com.huan.common.util.http.MultiReadHttpServletRequest;
import com.huan.sso.handler.AdminAuthenticationFailureHandler;
import com.huan.sso.handler.AdminAuthenticationSuccessHandler;
import com.huan.sso.service.CusAuthenticationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截尝试认证
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Slf4j
@Component
public class AdminAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * @param authenticationManager:             认证管理器
     * @param adminAuthenticationSuccessHandler: 认证成功处理
     * @param adminAuthenticationFailureHandler: 认证失败处理
     */
    public AdminAuthenticationProcessingFilter(CusAuthenticationManager authenticationManager, AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler, AdminAuthenticationFailureHandler adminAuthenticationFailureHandler) {
        super(new AntPathRequestMatcher("/login", "POST"));
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(adminAuthenticationSuccessHandler);
        setAuthenticationFailureHandler(adminAuthenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (request.getContentType() == null || !request.getContentType().contains(RequestConstants.CONTENT_TYPE_JSON)) {
            throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
        }

        UsernamePasswordAuthenticationToken authRequest;
        try {
            MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
            User user = JSONObject.parseObject(wrappedRequest.getBodyJsonStrByJson(wrappedRequest), User.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getLoginName(), user.getPassword(), null);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return getAuthenticationManager().authenticate(authRequest);
    }
}