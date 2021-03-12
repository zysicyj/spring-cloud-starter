package com.huan.common.exception;


public class ClockGoBackException extends RuntimeException {
    private static final long serialVersionUID = 8521812810726030457L;

    public ClockGoBackException(String message) {
        super(message);
    }
}
