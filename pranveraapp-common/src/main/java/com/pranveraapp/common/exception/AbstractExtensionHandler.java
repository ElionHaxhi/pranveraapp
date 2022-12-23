package com.pranveraapp.common.exception;

import com.pranveraapp.common.extension.ExtensionHandler;

/**
 * Created by elion on 28/02/16.
 */
public class AbstractExtensionHandler implements ExtensionHandler{
     protected int priority;
    protected boolean enabled = true;

    /**
     * Determines the priority of this extension handler.
     * @return
     */
    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
