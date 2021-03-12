package com.huan.common.constants.sso;

import lombok.extern.slf4j.Slf4j;


/**
 * SWAGGER常用参数
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/26
 */
public interface SwaggerConstants {
    String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**"
    };
}
