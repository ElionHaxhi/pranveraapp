package com.pranveraapp.profile.domain;

import java.io.Serializable;

/**
 * Created by elion on 07/02/16.
 */
public interface Customer extends Serializable {


    public Long getId();

    public void setId(Long id);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public Boolean isPasswordChangeRequired();

    public void setPasswordChangeRequired(Boolean passwordChangeRequired);

    public boolean isDeactivated();

    public void setDeactivated(Boolean deactivated);
}
