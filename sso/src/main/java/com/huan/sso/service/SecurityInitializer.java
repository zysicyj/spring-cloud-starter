package com.huan.sso.service;

import com.huan.sso.config.RedisSessionConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 指定Redis存储session
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/12
 * @since 1.0.0
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(RedisSessionConfig.class);
    }
}
