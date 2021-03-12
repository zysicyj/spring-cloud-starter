package com.huan.sso.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;


/**
 * 配置token存放在Redis中
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/26
 */
@Slf4j
@Configuration
public class RedisTokenStoreConfig {
    @Resource
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore jwtTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
}
