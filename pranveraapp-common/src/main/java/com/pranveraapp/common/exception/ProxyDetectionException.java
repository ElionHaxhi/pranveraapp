package com.pranveraapp.common.exception;

/**
 * Created by elion on 24/02/16.
 */
public class ProxyDetectionException extends RuntimeException {
    public ProxyDetectionException() {
    }

    public ProxyDetectionException(String message) {
        super(message);
    }

    public ProxyDetectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxyDetectionException(Throwable cause) {
        super(cause);
    }

    public ProxyDetectionException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
