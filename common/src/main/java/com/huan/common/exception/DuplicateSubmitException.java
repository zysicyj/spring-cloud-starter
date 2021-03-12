package com.huan.common.exception;

import java.io.Serializable;

/**
 * 防重复提交异常类
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class DuplicateSubmitException extends ServiceException {
    private static final long serialVersionUID = -6764106461416287985L;

    public DuplicateSubmitException() {
        super();
    }

    public DuplicateSubmitException(String s) {
        super(s);
    }

    public DuplicateSubmitException(Throwable e) {
        super(e);
    }

    public DuplicateSubmitException(String message, Throwable cause) {
        super(message, cause);
    }
}
