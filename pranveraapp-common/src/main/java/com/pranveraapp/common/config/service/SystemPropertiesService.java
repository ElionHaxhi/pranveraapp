package com.pranveraapp.common.config.service;

import java.io.Serializable;

/**
 * Created by elion on 03/02/16.
 */
public interface SystemPropertiesService extends Serializable{
    public int resolveIntSystemProperty(String name);

    public String resolveSystemProperty(String name, String defaultValue);

    public String resolveSystemProperty(String name);
}
