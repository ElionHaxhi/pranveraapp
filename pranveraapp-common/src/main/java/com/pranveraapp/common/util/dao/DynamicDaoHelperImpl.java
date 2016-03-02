package com.pranveraapp.common.util.dao;

import com.pranveraapp.common.exception.ExceptionHelper;
import com.pranveraapp.common.exception.ProxyDetectionException;
import javassist.util.proxy.ProxyFactory;
import org.apache.commons.collections4.map.LRUMap;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by elion on 24/02/16.
 */
public class DynamicDaoHelperImpl implements DynamicDaoHelper{

    public static final Object LOCK_OBJECT = new Object();
    public static final Map<Class<?>, Class<?>[]> POLYMORPHIC_ENTITY_CACHE = new LRUMap<Class<?>, Class<?>[]>(1000);
    public static final Map<Class<?>, Class<?>[]> POLYMORPHIC_ENTITY_CACHE_WO_EXCLUSIONS = new LRUMap<Class<?>, Class<?>[]>(1000);
    public static final String JAVASSIST_PROXY_KEY_PHRASE = "_$$_";

    public static Class<?> getNonProxyImplementationClassIfNecessary(Class<?> candidate) {
        Class<?> response = candidate;
        //TODO Although unusual, there are other proxy types in Hibernate than just javassist proxies (Broadleaf defaults to javassist).
        //At some point, should try to account for those here in a performant way.
        if (ProxyFactory.isProxyClass(candidate)) {
            //TODO This is not ideal. While this works consistently, I don't think it's guaranteed that the proxy classname
            //will always have this format in the future. We'll at least throw an exception if our formatting detection fails
            //so that we're aware of it.
            if (!candidate.getName().contains(JAVASSIST_PROXY_KEY_PHRASE)) {
                throw new ProxyDetectionException(String.format("Cannot determine the original implementation class for " +
                        "the javassist proxy. Expected to find the keyphrase (%s) in the proxy classname (%s).",
                        JAVASSIST_PROXY_KEY_PHRASE, candidate.getName()));
            }
            String implName = candidate.getName().substring(0, candidate.getName().lastIndexOf(JAVASSIST_PROXY_KEY_PHRASE));
            try {
                response = Class.forName(implName);
            } catch (ClassNotFoundException e) {
                throw ExceptionHelper.refineException(e);
            }
        }
        return response;
    }

    @Override
    public Map<String, Object> getIdMetadata(Class<?> entityClass, HibernateEntityManager entityManager) {
        entityClass = getNonProxyImplementationClassIfNecessary(entityClass);
        Map<String, Object> response = new HashMap<String, Object>();
        SessionFactory sessionFactory = entityManager.getSession().getSessionFactory();

        ClassMetadata metadata = sessionFactory.getClassMetadata(entityClass);
        if (metadata == null) {
            return null;
        }

        String idProperty = metadata.getIdentifierPropertyName();
        response.put("name", idProperty);
        Type idType = metadata.getIdentifierType();
        response.put("type", idType);

        return response;
    }
}
