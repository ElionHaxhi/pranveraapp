package com.pranveraapp.profile.domain;

import java.io.Serializable;

/**
 * Created by elion on 07/02/16.
 */
public interface CustomerRole extends Serializable{
    public Long getId();

    public void setId(Long id);

    public Customer getCustomer();

    public void setCustomer(Customer customer);

    public Role getRole();

    public void setRole(Role role);

    public String getRoleName();
}
