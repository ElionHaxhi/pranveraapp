package com.pranveraapp.profile.service;

import com.pranveraapp.profile.dao.CustomerDao;
import com.pranveraapp.profile.domain.Customer;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by elion on 07/02/16.
 */
@Service("elCustomerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource(name = "elCustomerDao")
    protected CustomerDao customerDao;

    @Deprecated
    protected org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder;


    protected PasswordEncoder passwordEncoderNew;


    @Resource(name="elPasswordEncoder")
    protected Object passwordEncoderBean;

    @PostConstruct
    protected void setupPasswordEncoder() {
        passwordEncoderNew = null;
        passwordEncoder = null;
        if (passwordEncoderBean instanceof PasswordEncoder) {
            passwordEncoderNew = (PasswordEncoder) passwordEncoderBean;
        } else if (passwordEncoderBean instanceof org.springframework.security.authentication.encoding.PasswordEncoder) {
            passwordEncoder = (org.springframework.security.authentication.encoding.PasswordEncoder) passwordEncoderBean;
        } else {
            throw new NoSuchBeanDefinitionException("No PasswordEncoder bean is defined");
        }
    }

    public void setPasswordEncoder(Object passwordEncoder) {
        this.passwordEncoderBean = passwordEncoder;
        setupPasswordEncoder();
    }


    @Override
    public Customer readCustomerByUsername(String username, Boolean cacheable) {
        return customerDao.readCustomerByUsername(username, cacheable);
    }
}
