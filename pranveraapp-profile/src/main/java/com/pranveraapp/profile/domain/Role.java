package com.pranveraapp.profile.domain;

import java.io.Serializable;

/**
 * Created by elion on 07/02/16.
 */
public interface Role extends Serializable {
    public Long getId();

    public void setId(Long id);

    public String getRoleName();

    public void setRoleName(String roleName);
}
