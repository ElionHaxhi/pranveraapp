package com.pranveraapp.profile.service;

import com.pranveraapp.profile.domain.Customer;

/**
 * Created by elion on 07/02/16.
 */
public interface CustomerService {

    public Customer readCustomerByUsername(String username, Boolean cacheable);
}
