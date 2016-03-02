package com.pranveraapp.common.util.dao;

import org.hibernate.ejb.HibernateEntityManager;

import java.util.Map;

/**
 * Created by elion on 24/02/16.
 */
public interface DynamicDaoHelper {

    public Map<String, Object> getIdMetadata(Class<?> entityClass, HibernateEntityManager entityManager);
}
