package com.huan.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Key is none")
public class NoKeyException extends RuntimeException {
    private static final long serialVersionUID = 6251746041938095849L;
}
