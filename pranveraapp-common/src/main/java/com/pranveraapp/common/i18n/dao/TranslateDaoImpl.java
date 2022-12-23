package com.pranveraapp.common.i18n.dao;

import com.pranveraapp.common.extension.ExtensionResultHolder;
import com.pranveraapp.common.extension.ResultType;
import com.pranveraapp.common.extension.StandardCacheItem;
import com.pranveraapp.common.i18n.domain.Translate;
import com.pranveraapp.common.i18n.domain.TranslateImpl;
import com.pranveraapp.common.i18n.domain.TranslatedEntity;
import com.pranveraapp.common.persistence.EntityConfiguration;
import com.pranveraapp.common.util.dao.DynamicDaoHelper;
import com.pranveraapp.common.util.dao.DynamicDaoHelperImpl;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.ejb.QueryHints;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by elion on 24/02/16.
 */
@Repository("elTranslateDao")
public class TranslateDaoImpl implements TranslateDao {

    @PersistenceContext(unitName = "elPU")
    protected EntityManager em;

    @Resource(name = "elEntityConfiguration")
    protected EntityConfiguration entityConfiguration;


    protected DynamicDaoHelper dynamicDaoHelper = new DynamicDaoHelperImpl();

    @Override
    public Translate readTranslateById(Long translateId) {
        return em.find(TranslateImpl.class, translateId);
    }

    @Override
    public Map<String, Object> getIdPropertyMetadata(TranslatedEntity entity) {
        Class<?> implClass = entityConfiguration.lookupEntityClass(entity.getType());
        return dynamicDaoHelper.getIdMetadata(implClass, (HibernateEntityManager) em);
    }

    @Override
    public Long countTranslationEntries(TranslatedEntity entityType, ResultType stage) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<TranslateImpl> root = criteria.from(TranslateImpl.class);
        criteria.select(builder.count(root));
        List<Predicate> restrictions = new ArrayList<Predicate>();
        restrictions.add(builder.equal(root.get("entityType"), entityType.getFriendlyType()));
        criteria.where(restrictions.toArray(new Predicate[restrictions.size()]));

        TypedQuery<Long> query = em.createQuery(criteria);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getSingleResult();
    }

    @Override
    public List<StandardCacheItem> readConvertedTranslationEntries(TranslatedEntity entityType, ResultType stage) {
        List<Translate> results = readAllTranslationEntries(entityType, stage);
        ExtensionResultHolder<List<StandardCacheItem>> response = new ExtensionResultHolder<List<StandardCacheItem>>();
        return response.getResult();
    }

    @Override
    public List<Translate> readAllTranslationEntries(TranslatedEntity entityType, ResultType stage) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Translate> criteria = builder.createQuery(Translate.class);
        Root<TranslateImpl> root = criteria.from(TranslateImpl.class);
        criteria.select(root);
        List<Predicate> restrictions = new ArrayList<Predicate>();
        restrictions.add(builder.equal(root.get("entityType"), entityType.getFriendlyType()));
            criteria.where(restrictions.toArray(new Predicate[restrictions.size()]));

            TypedQuery<Translate> query = em.createQuery(criteria);
            query.setHint(QueryHints.HINT_CACHEABLE, true);
            return query.getResultList();
    }

    @Override
    public String getEntityId(TranslatedEntity entityType, Object entity) {
        Map<String, Object> idMetadata = getIdPropertyMetadata(entityType);
        String idProperty = (String) idMetadata.get("name");
        Type idType = (Type) idMetadata.get("type");

        if (!(idType instanceof LongType || idType instanceof StringType)) {
            throw new UnsupportedOperationException("Only ID types of String and Long are currently supported");
        }

        Object idValue;
        try {
            idValue = PropertyUtils.getProperty(entity, idProperty);
        } catch (Exception e) {
            throw new RuntimeException("Error reading id property", e);
        }

        if (idType instanceof StringType) {
            return (String) idValue;
        } else if (idType instanceof LongType) {
            return getUpdatedEntityId(entityType, (Long) idValue);
        }

        throw new IllegalArgumentException(String.format("Could not retrieve value for id property. Object: [%s], " +
                "ID Property: [%s], ID Type: [%s]", entity, idProperty, idType));
    }

    protected String getUpdatedEntityId(TranslatedEntity entityType, Long idValue) {

        return String.valueOf(idValue);
    }

    @Override
    public Translate readTranslation(TranslatedEntity entityType, String entityId, String fieldName, String localeCode, String localeCountryCode, ResultType stage) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Translate> criteria = builder.createQuery(Translate.class);
        Root<TranslateImpl> root = criteria.from(TranslateImpl.class);
        criteria.select(root);
        List<Predicate> restrictions = new ArrayList<Predicate>();
        restrictions.add(builder.equal(root.get("entityType"), entityType.getFriendlyType()));
        restrictions.add(builder.equal(root.get("entityId"), entityId));
        restrictions.add(builder.equal(root.get("fieldName"), fieldName));
        restrictions.add(builder.like(root.get("localeCode").as(String.class), localeCode + "%"));
        criteria.where(restrictions.toArray(new Predicate[restrictions.size()]));

        TypedQuery<Translate> query = em.createQuery(criteria);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        List<Translate> translations = query.getResultList();

        if (!translations.isEmpty()) {
            return findBestTranslation(localeCountryCode, translations);
        } else {
            return null;
        }
    }

    protected Translate findBestTranslation(String specificLocale, List<Translate> translations) {
        for (Translate translate : translations) {
            if (translate.getLocaleCode().equals(specificLocale)) {
                return translate;
            }
        }
        return translations.get(0);
    }


}
