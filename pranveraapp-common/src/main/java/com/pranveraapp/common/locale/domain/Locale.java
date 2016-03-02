package com.pranveraapp.common.locale.domain;


import java.io.Serializable;

/**
 * Created by elion on 02/02/16.
 */
public interface Locale extends Serializable {

    public String getLocaleCode();

    public void setLocaleCode(String localeCode);

    public String getFriendlyName();

    public void setFriendlyName(String friendlyName);

    public Boolean getDefaultFlag();

    public void setDefaultFlag(Boolean defaultFlag);

}
