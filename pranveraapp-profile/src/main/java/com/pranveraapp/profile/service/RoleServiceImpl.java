package com.pranveraapp.profile.service;

import com.pranveraapp.profile.dao.RoleDao;
import com.pranveraapp.profile.domain.CustomerRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by elion on 07/02/16.
 */
@Service("elRoleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name="elRoleDao")
    protected RoleDao roleDao;

    @Override
    @Transactional("elTransactionManager")
    public List<CustomerRole> findCustomerRolesByCustomerId(Long customerId) {
        return roleDao.readCustomerRolesByCustomerId(customerId);
    }
}
