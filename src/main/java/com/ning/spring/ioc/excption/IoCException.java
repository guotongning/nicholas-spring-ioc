package com.ning.spring.ioc.excption;

/**
 * @Author: nicholas
 * @Date: 2021/2/6 15:07
 * @Descreption:
 */
public class IoCException extends RuntimeException {
    public IoCException() {
    }

    public IoCException(String message) {
        super(message);
    }

    public IoCException(String message, Throwable cause) {
        super(message, cause);
    }

    public IoCException(Throwable cause) {
        super(cause);
    }

    public IoCException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
