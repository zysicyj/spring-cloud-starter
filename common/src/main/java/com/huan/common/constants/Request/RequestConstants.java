package com.huan.common.constants.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数配置
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2020/1/9
 */
public interface RequestConstants {
    /**
     * post请求类型，只接受该类型的登录请求
     */
    String CONTENT_TYPE_JSON = "application/json";

    /**
     * 接口url
     */
    Map<String, String> URL_MAPPING_MAP = new HashMap<>();

    /**
     * 请求头 - token
     */
    String REQUEST_HEADER = "X-Token";


    /**
     * 未登录者角色
     */
    String ROLE_LOGIN = "role_login";

    /**
     * 内容类型
     */
    String CONTENT_TYPE = "Content-Type";
}
