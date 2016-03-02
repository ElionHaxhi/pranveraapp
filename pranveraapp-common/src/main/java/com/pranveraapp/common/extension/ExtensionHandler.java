package com.pranveraapp.common.extension;

/**
 * Created by elion on 27/02/16.
 */
public interface ExtensionHandler {
    /**
     * Determines the priority of this extension handler.
     * @return
     */
    public int getPriority();

    /**
     * If false, the ExtensionManager should skip this Handler.
     * @return
     */
    public boolean isEnabled();
}
