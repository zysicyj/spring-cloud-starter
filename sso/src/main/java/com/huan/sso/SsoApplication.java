package com.huan.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SSO模块，主要负责登录相关业务
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.huan.common", "com.huan.sso"})
@EntityScan(basePackages = "com.huan.common.model")
public class SsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

    @Bean
    ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}
