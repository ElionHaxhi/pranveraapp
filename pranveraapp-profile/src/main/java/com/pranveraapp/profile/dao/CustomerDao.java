package com.pranveraapp.profile.dao;

import com.pranveraapp.profile.domain.Customer;

import java.util.List;

/**
 * Created by elion on 07/02/16.
 */
public interface CustomerDao {

    public Customer readCustomerByUsername(String username, Boolean cacheable);


    public List<Customer> readCustomersByUsername(String username, Boolean cacheable);

}
