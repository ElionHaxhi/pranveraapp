package com.pranveraapp.common.extensibility.context.merge.exceptions;

/**
 * Created by elion on 25/01/16.
 */
public class MergeException extends Exception{
    private static final long serialVersionUID = 1L;

    public MergeException() {
        super();
    }

    public MergeException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public MergeException(String arg0) {
        super(arg0);
    }

    public MergeException(Throwable arg0) {
        super(arg0);
    }
}
