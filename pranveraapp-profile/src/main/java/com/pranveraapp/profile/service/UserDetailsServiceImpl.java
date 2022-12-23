package com.pranveraapp.profile.service;

import com.pranveraapp.profile.domain.Customer;
import com.pranveraapp.profile.domain.CustomerRole;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elion on 07/02/16.
 */
@Service("elUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "elCustomerService")
    protected CustomerService customerService;

    @Resource(name = "elRoleService")
    protected RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Customer customer = customerService.readCustomerByUsername(username, false);
        if (customer == null) {
            throw new UsernameNotFoundException("The customer was not found");
        }

        List<GrantedAuthority> grantedAuthorities = createGrantedAuthorities(roleService.findCustomerRolesByCustomerId(customer.getId()));
        return new CustomerUserDetails(customer.getId(), username, customer.getPassword(), !customer.isDeactivated(), true, !customer.isPasswordChangeRequired(), true, grantedAuthorities);
    }

    protected List<GrantedAuthority> createGrantedAuthorities(List<CustomerRole> customerRoles) {
        boolean roleUserFound = false;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (CustomerRole role : customerRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            if (role.getRoleName().equals("ROLE_USER")) {
                roleUserFound = true;
            }
        }

        if (!roleUserFound) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return grantedAuthorities;
    }
}
