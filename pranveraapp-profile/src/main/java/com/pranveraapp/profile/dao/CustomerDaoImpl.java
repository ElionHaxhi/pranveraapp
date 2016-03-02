package com.pranveraapp.profile.dao;

import com.pranveraapp.common.persistence.EntityConfiguration;
import com.pranveraapp.profile.domain.Customer;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by elion on 07/02/16.
 */
@Repository("elCustomerDao")
public class CustomerDaoImpl implements  CustomerDao{

    @PersistenceContext(unitName="elPU")
    protected EntityManager em;

    @Resource(name="elEntityConfiguration")
    protected EntityConfiguration entityConfiguration;




    @Override
    public Customer readCustomerByUsername(String username, Boolean cacheable) {
        List<Customer> customers = readCustomersByUsername(username, cacheable);
        return CollectionUtils.isEmpty(customers) ? null : customers.get(0);
    }

    @Override
    public List<Customer> readCustomersByUsername(String username, Boolean cacheable) {
        TypedQuery<Customer> query = em.createNamedQuery("EL_READ_CUSTOMER_BY_USER_NAME", Customer.class);
        query.setParameter("username", username);
        query.setHint(QueryHints.HINT_CACHEABLE, cacheable);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Order");
        return query.getResultList();
    }
}
