package com.huan.sso.handler;

import com.huan.common.model.SecurityUser;
import com.huan.common.model.User;
import com.huan.common.util.result.R;
import com.huan.common.util.result.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证成功拦截处理
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) {
        String path = httpServletRequest.getServletPath();
        String filterRequest = "login";
        /*
         * 加这个判断主要是，认证授权成功的时候也会走这个拦截，但肯定不能直接返回用户信息，所以需要放掉继续走调用链
         */
        if (StringUtils.contains(path, filterRequest)) {
            User user = new User();
            SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
            user.setToken(securityUser.getToken());
            ResponseUtils.renderJson(response, R.success().data("token", user).toJson());
        }
    }
}