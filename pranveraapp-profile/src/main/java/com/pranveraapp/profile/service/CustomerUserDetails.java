package com.pranveraapp.profile.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by elion on 07/02/16.
 */
public class CustomerUserDetails extends User {
     private static final long serialVersionUID = 1L;

        protected Long id;

    public CustomerUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(id, username, password, true, true, true, true, authorities);
    }

    public CustomerUserDetails(Long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    public CustomerUserDetails withId(Long id) {
        setId(id);
        return this;
    }

    /**
     * @return the primary key of the Customer
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the primary key of the Customer
     */
    public void setId(Long id) {
        this.id = id;
    }
}
