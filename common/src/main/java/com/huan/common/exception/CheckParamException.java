package com.huan.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import java.io.Serializable;


/**
 * 检查异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class CheckParamException extends RuntimeException {
    private static final long serialVersionUID = 4350828032462127816L;
    private String message;
    private String errorData;
    private JoinPoint joinPoint;
    private String path;
    private String queryString;
    private String serverName;
    private String remoteAddress;


    /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     *
     * @since 1.7
     */
    public CheckParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                               String errorData, JoinPoint joinPoint) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.errorData = errorData;
        this.joinPoint = joinPoint;
    }

    @Override
    public String toString() {
        return "\r\nCheckParamException{\r\n" +
                "错误类型='" + message +
                ",\r\n 错误数据='" + errorData +
                ",\r\n 切点=" + joinPoint +
                ",\r\n 请求接口='" + path +
                ",\r\n 请求参数='" + queryString +
                ",\r\n 服务器名称=" + serverName +
                ",\r\n 服务器地址='" + remoteAddress +
                "\r\n}";
    }
}
