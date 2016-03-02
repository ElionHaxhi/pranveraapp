package com.pranveraapp.common.extension;

/**
 * Created by elion on 27/02/16.
 */
public enum ExtensionResultStatusType {
    HANDLED,

    /**
     * The extension was handled by the {@link ExtensionHandler} and it recommends continuing with additional
     * {@link ExtensionHandler}s (if more are available).
     */
    HANDLED_CONTINUE,

    /**
     * An {@link ExtensionHandler} has handled this and it recommends that the {@link ExtensionManager} should not invoke
     * any more {@link ExtensionHandler}s that it may have to execute
     */
    HANDLED_STOP,

    /**
     * This was not handled by the {@link ExtensionHandler}. In the context of the result of an {@link ExtensionManager},
     * this indicates that it was not executed by any of the registered {@link ExtensionHandler}s, or that none were
     * registered in the first place
     */
    NOT_HANDLED
}
