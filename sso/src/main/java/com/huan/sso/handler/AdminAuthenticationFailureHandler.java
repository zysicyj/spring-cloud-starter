package com.huan.sso.handler;

import com.huan.common.util.result.R;
import com.huan.common.util.result.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败后拦截处理
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Slf4j
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) {
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            ResponseUtils.renderJson(response, R.fail().data("未查找到用户或用户凭据失效", e.getMessage()).toJson());
        } else if (e instanceof LockedException) {
            ResponseUtils.renderJson(response, R.fail().data("账户被锁定，请联系管理员!", e.getMessage()).toJson());
        } else if (e instanceof CredentialsExpiredException) {
            ResponseUtils.renderJson(response, R.fail().data("证书过期，请联系管理员!", e.getMessage()).toJson());
        } else if (e instanceof AccountExpiredException) {
            ResponseUtils.renderJson(response, R.fail().data("账户过期，请联系管理员!", e.getMessage()).toJson());
        } else if (e instanceof DisabledException) {
            ResponseUtils.renderJson(response, R.fail().data("账户被禁用，请联系管理员!", e.getMessage()).toJson());
        } else {
            log.error("登录失败：", e);
            ResponseUtils.renderJson(response, R.fail().data("登录失败!", e.getMessage()).toJson());
        }
    }
}