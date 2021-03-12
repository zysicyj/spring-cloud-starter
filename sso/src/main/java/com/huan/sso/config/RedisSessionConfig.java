package com.huan.sso.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 配置使用Redis存储用户session信息
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/12
 * @since 1.0.0
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    // @Value("${spring.redis.password}")
    // private String redisPassword;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPort(redisPort);
        // if (!StringUtils.equals(redisPassword, "-")) {
        //     redisStandaloneConfiguration.setPassword(redisPassword);
        // }

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}
