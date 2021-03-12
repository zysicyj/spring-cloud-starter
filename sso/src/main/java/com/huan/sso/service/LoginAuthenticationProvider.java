package com.huan.sso.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * 自定义密码校验
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/13
 */
@Slf4j
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {
    public LoginAuthenticationProvider(UserDetailsService userDetailsService) {
        super();
        // 这个地方一定要对userDetailsService赋值，不然userDetailsService是null (这个坑有点深)
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // Object salt = null;
        //
        //
        // if (authentication.getCredentials() == null) {
        //     logger.debug("Authentication failed: no credentials provided");
        //
        //     throw new BadCredentialsException(
        //             messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        // }
        //
        // String presentedPassword = authentication.getCredentials().toString();
        // //我就改了这个地方，增加一个验证码登录标识(具体怎么做就看自己了)
        // // 【原本的是登录密码和数据密码不等就抛出异常，我用验证码登录时压根都不知道密码(ー`´ー)】
        // //so 我给短信登录时赋值一个默认密码（验证码登录标识）来判断，不让这儿报异常
        //
        // if (!presentedPassword.equals("YZMLG_KSssdS1D145Sd4S")) {
        // if (!getPasswordEncoder()) {
        //     logger.debug("Authentication failed: password does not match stored value");
        //
        //     throw new BadCredentialsException(messages
        //             .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        // }
    }
}
