package com.pranveraapp.common.web.exception;

/**
 * Created by elion on 06/02/16.
 */
public class SiteNotFoundException extends RuntimeException {
      public SiteNotFoundException() {
        //do nothing
    }

    public SiteNotFoundException(Throwable cause) {
        super(cause);
    }

    public SiteNotFoundException(String message) {
        super(message);
    }

    public SiteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
