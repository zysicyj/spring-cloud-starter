package com.huan.common.exception;

/**
 * 表单重复提交异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class FormRepeatException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4900829256208973176L;

    public FormRepeatException(String message) { super(message);}

    public FormRepeatException(String message, Throwable cause) { super(message, cause);}
}
