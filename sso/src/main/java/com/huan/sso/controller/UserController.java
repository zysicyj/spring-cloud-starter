package com.huan.sso.controller;

import com.huan.common.util.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 用户服务接口
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/25
 */
@Slf4j
@RestController()
public class UserController {
    @Resource
    private TokenStore tokenStore;

    @GetMapping("/logout")
    public R<String> logout(String token) {
        if (StringUtils.isBlank(token)) {
            return R.fail("退出登录请传入token");
        }
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        if (oAuth2AccessToken == null) {
            return R.fail("该账号已退出，请勿重复提交！");
        }
        tokenStore.removeAccessToken(oAuth2AccessToken);
        log.debug("退出登录成功: {}", oAuth2AccessToken);
        return R.success("退出登录成功！");
    }
}
