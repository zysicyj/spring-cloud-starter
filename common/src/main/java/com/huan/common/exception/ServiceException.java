package com.huan.common.exception;

/**
 * 业务异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 2221019601835886746L;
    /**
     * 异常情况的分类，由业务方定义
     */
    private int errorType;

    public ServiceException() {
        super();
    }

    public ServiceException(String s) {
        super(s);
    }

    public ServiceException(int errorType, String s) {
        super(s);
        this.errorType = errorType;
    }

    public ServiceException(Throwable e) {
        super(e);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
}