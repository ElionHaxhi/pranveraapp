package com.pranveraapp.profile.dao;

import com.pranveraapp.common.persistence.EntityConfiguration;
import com.pranveraapp.profile.domain.CustomerRole;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by elion on 07/02/16.
 */
@Repository("elRoleDao")
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext(unitName = "elPU")
    protected EntityManager em;

    @Resource(name = "elEntityConfiguration")
    protected EntityConfiguration entityConfiguration;


    @SuppressWarnings("unchecked")
    public List<CustomerRole> readCustomerRolesByCustomerId(Long customerId) {
        Query query = em.createNamedQuery("EL_READ_CUSTOMER_ROLES_BY_CUSTOMER_ID");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
}
