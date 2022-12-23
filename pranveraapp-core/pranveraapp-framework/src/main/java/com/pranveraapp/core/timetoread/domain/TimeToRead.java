package com.pranveraapp.core.timetoread.domain;

import java.io.Serializable;

/**
 * Created by elion on 31/01/16.
 */
public interface TimeToRead extends Serializable{
    public Long getId();

    public void setId(Long id);

    public Integer getValue();

    public void setValue(Integer value);
}
