package com.pranveraapp.profile.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import org.hibernate.annotations.Parameter;
import javax.persistence.*;

/**
 * Created by elion on 07/02/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_customer_role")
public class CustomerRoleImpl implements CustomerRole{

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ROLE_ID")
    protected Long id;

    @ManyToOne(targetEntity = CustomerImpl.class, optional = false)
    @JoinColumn(name = "CUSTOMER_ID")
    @Index(name="CUSTROLE_CUSTOMER_INDEX", columnNames={"CUSTOMER_ID"})
    protected Customer customer;

    @ManyToOne(targetEntity = RoleImpl.class, optional = false)
    @JoinColumn(name = "ROLE_ID")
    @Index(name="CUSTROLE_ROLE_INDEX", columnNames={"ROLE_ID"})
    protected Role role;

@Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getRoleName() {
        return role.getRoleName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        CustomerRoleImpl other = (CustomerRoleImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }

}
