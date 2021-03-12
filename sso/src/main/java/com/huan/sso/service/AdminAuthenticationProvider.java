package com.huan.sso.service;

import com.huan.common.model.SecurityUser;
import com.huan.sso.util.login.UserLoginValidateUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

import static com.huan.common.constants.sso.LoginConstants.REDIS_KEY_LOGIN;

/**
 * 认证处理
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Component
@Slf4j
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private
    UserDetailsServiceImpl userDetailsService;

    @Resource
    private
    RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .setSigningKey("secret".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(password);
        } catch (ExpiredJwtException e) {
            throw new AccountExpiredException("jwt已过期");
        } catch (UnsupportedJwtException e) {
            throw new AccountExpiredException("不支持的jwt形式");
        } catch (MalformedJwtException e) {
            throw new AccountExpiredException("jwt格式不正确");
        } catch (SignatureException e) {
            throw new AccountExpiredException("jwt签名不正确");
        } catch (IllegalArgumentException e) {
            throw new AccountExpiredException("jwt异常");
        }
        if (jws == null) {
            throw new BadCredentialsException("未获取到密码，请确认jwt内容是否正确");
        }
        password = String.valueOf(jws.getBody().get("password"));


        SecurityUser userInfo = (SecurityUser) userDetailsService.loadUserByUsername(userName);

        // 验证密码
        boolean isValid = UserLoginValidateUtil.checkUserIsValid(password, userInfo);

        if (!isValid) {
            throw new BadCredentialsException("密码错误或已失效，请重新登录！");
        }

        //对外隐藏密码
        userInfo.setPassword("N/A");
        userInfo.setSalt("N/A");
        userInfo.setToken("N/A");

        redisTemplate.opsForSet().add(REDIS_KEY_LOGIN, userName);

        log.debug("userInfo:{}", userInfo);
        return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}