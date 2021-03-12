package com.huan.common.exception;

import java.io.Serializable;

/**
 * sql异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class DaoException extends RuntimeException {

    public DaoException() {
        super();
    }

    public DaoException(String s) {
        super(s);
    }

    public DaoException(Throwable e) {
        super(e);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}