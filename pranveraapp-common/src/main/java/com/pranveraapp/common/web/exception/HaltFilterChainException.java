package com.pranveraapp.common.web.exception;

/**
 * Created by elion on 06/02/16.
 */
public class HaltFilterChainException extends RuntimeException {

        // for serialization purposes
    protected HaltFilterChainException() {
        super();
    }

    public HaltFilterChainException(String message, Throwable cause) {
        super(message, cause);
    }

    public HaltFilterChainException(String message) {
        super(message);
    }

    public HaltFilterChainException(Throwable cause) {
        super(cause);
    }


}
