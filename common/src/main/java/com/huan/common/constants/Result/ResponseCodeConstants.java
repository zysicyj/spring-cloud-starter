package com.huan.common.constants.Result;

/**
 * 统一响应码
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2020/1/9
 */
public interface ResponseCodeConstants {
    /**
     * 请求处理成功
     */
    int SUCCESS = 200;
    /**
     * 权限受限
     */
    int Unauthorized = 401;
    /**
     * 请求处理失败
     */
    int ERROR = 500;

}
