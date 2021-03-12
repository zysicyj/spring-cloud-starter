package com.huan.common.exception;

/**
 * 版本拦截异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class VersionBlockedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4900829256208973176L;

    public VersionBlockedException(String message) { super(message);}

    public VersionBlockedException(String message, Throwable cause) { super(message, cause);}
}
