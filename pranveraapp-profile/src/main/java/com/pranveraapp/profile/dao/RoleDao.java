package com.pranveraapp.profile.dao;

import com.pranveraapp.profile.domain.CustomerRole;

import java.util.List;

/**
 * Created by elion on 07/02/16.
 */
public interface RoleDao {



    public List<CustomerRole> readCustomerRolesByCustomerId(Long customerId);
}
