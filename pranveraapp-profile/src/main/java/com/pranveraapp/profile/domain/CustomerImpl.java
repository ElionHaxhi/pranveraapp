package com.pranveraapp.profile.domain;

import org.apache.commons.lang.BooleanUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by elion on 07/02/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "elCustomerElements")
public class CustomerImpl  implements Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    protected Long id;

    @Column(name = "USER_NAME")
    protected String username;

    @Column(name = "PASSWORD")
    protected  String password;

    @Column(name = "PASSWORD_CHANGE_REQUIRED")
    protected Boolean passwordChangeRequired = false;

    @Column(name = "DEACTIVATED")
    protected Boolean deactivated = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isPasswordChangeRequired() {
        return BooleanUtils.toBoolean(passwordChangeRequired);
    }

    public void setPasswordChangeRequired(Boolean passwordChangeRequired) {
        this.passwordChangeRequired = passwordChangeRequired;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    @Override
    public boolean isDeactivated() {
        return BooleanUtils.toBoolean(deactivated);
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerImpl)) return false;

        CustomerImpl customer = (CustomerImpl) o;

        if (!getId().equals(customer.getId())) return false;
        if (getUsername() != null ? !getUsername().equals(customer.getUsername()) : customer.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(customer.getPassword()) : customer.getPassword() != null)
            return false;
        if (passwordChangeRequired != null ? !passwordChangeRequired.equals(customer.passwordChangeRequired) : customer.passwordChangeRequired != null)
            return false;
        return !(getDeactivated() != null ? !getDeactivated().equals(customer.getDeactivated()) : customer.getDeactivated() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (passwordChangeRequired != null ? passwordChangeRequired.hashCode() : 0);
        result = 31 * result + (getDeactivated() != null ? getDeactivated().hashCode() : 0);
        return result;
    }
}
