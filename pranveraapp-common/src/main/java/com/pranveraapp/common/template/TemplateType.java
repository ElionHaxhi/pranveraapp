package com.pranveraapp.common.template;

import com.pranveraapp.common.PranveraAppEnumerationType;

import java.io.Serializable;

/**
 * Created by elion on 20/01/16.
 */
public class TemplateType implements Serializable,PranveraAppEnumerationType{

    private String friendlyType;

    private String type;

    public String getType() {
        return type;
    }

    public String getFriendlyType() {
        return friendlyType;
    }
}
