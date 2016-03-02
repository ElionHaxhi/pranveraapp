package com.pranveraapp.common.extensibility.jpa;

import com.pranveraapp.common.classloader.release.ThreadLocalManager;

/**
 * Created by elion on 24/01/16.
 */
public class ConfigurationOnlyState {
    private static final ThreadLocal<ConfigurationOnlyState> CONFIGURATIONONLYSTATE = ThreadLocalManager.createThreadLocal(ConfigurationOnlyState.class);

    public static ConfigurationOnlyState getState() {
        return CONFIGURATIONONLYSTATE.get();
    }

    public static void setState(ConfigurationOnlyState state) {
        CONFIGURATIONONLYSTATE.set(state);
    }

    protected boolean isConfigurationOnly;

    public boolean isConfigurationOnly() {
        return isConfigurationOnly;
    }

    public void setConfigurationOnly(boolean configurationOnly) {
        isConfigurationOnly = configurationOnly;
    }
}
