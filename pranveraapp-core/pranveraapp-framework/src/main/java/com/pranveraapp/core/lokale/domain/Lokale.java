package com.pranveraapp.core.lokale.domain;

import java.io.Serializable;

/**
 * Created by elion on 18/02/16.
 */
public interface Lokale extends Serializable{

    public void setId(String id);

    public boolean isDefaultFlag();

    public void setDefaultFlag(boolean defaultFlag);

    public String getFriendlyName();

    public void setFriendlyName(String friendlyName);
}
