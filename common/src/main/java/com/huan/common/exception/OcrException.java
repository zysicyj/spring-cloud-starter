package com.huan.common.exception;

import java.io.Serializable;

/**
 * OCR异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class OcrException extends ServiceException {
    //1:系统繁忙 2：识别失败
    private int flag;

    public OcrException() {
        super();
    }

    public OcrException(String s, int flag) {
        super(s);
        this.flag = flag;
    }

    public OcrException(String s) {
        super(s);
    }

    public OcrException(Throwable e) {
        super(e);
    }

    public OcrException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}

