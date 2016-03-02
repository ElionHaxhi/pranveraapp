package com.pranveraapp.profile.service;

import com.pranveraapp.profile.domain.CustomerRole;

import java.util.List;

/**
 * Created by elion on 07/02/16.
 */
public interface RoleService {

    public List<CustomerRole> findCustomerRolesByCustomerId(Long customerId);
}
