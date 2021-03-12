package com.huan.common.exception;

import java.io.Serializable;

/**
 * 文件已经存在异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class FileExistsException extends ServiceException {

    public FileExistsException() {
        super();
    }

    public FileExistsException(String s) {
        super(s);
    }

    public FileExistsException(Throwable e) {
        super(e);
    }

    public FileExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}