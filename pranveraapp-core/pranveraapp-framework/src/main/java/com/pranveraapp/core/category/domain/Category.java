package com.pranveraapp.core.category.domain;

import java.io.Serializable;

/**
 * Created by elion on 28/01/16.
 */
public interface Category extends Serializable{
    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getUrl();

    public void setUrl(String url);
}
