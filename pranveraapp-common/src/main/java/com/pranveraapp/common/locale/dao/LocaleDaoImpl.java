package com.pranveraapp.common.locale.dao;

import com.pranveraapp.common.locale.domain.Locale;
import com.pranveraapp.common.persistence.EntityConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by elion on 24/02/16.
 */

@Repository("elLocaleDao")
public class LocaleDaoImpl implements LocaleDao{
    private static final Log LOG = LogFactory.getLog(LocaleDaoImpl.class);

    @PersistenceContext(unitName = "elPU")
    protected EntityManager em;

    @Resource(name = "elEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    /**
     * Return the locale for the passed in Code
     *
     * @param localeCode
     * @return
     */
    @Override
    public Locale findLocaleByCode(String localeCode){
        Query query = em.createNamedQuery("EL_READ_LOCALE_BY_CODE");
        query.setParameter("localeCode", localeCode);
        query.setHint(QueryHints.HINT_CACHEABLE,true);

        List<Locale> localeList = (List<Locale>)query.getResultList();
        if(localeList.size() >= 1){
            if(localeList.size() > 1){
                LOG.warn("Locale code " + localeCode + " exsists for more than one locale");
            }
            return localeList.get(0);
        }
        return null;
    }

    /**
     * Returns the page template with the passed in id
     *
     * @return The default locale
     */
    @Override
    public Locale findDefaultLocale(){
        Query query = em.createNamedQuery("EL_READ_DEFAULT_LOCALE");
        query.setHint(QueryHints.HINT_CACHEABLE,true);

        List<Locale> localeList = (List<Locale>)query.getResultList();
        if(localeList.size() >= 1){
            if(localeList.size() > 1){
                LOG.warn("There is more than one default locale configured");
            }
            return localeList.get(0);
        }
        return null;
    }

    @Override
    public List<Locale> findAllLocales(){
        Query query = em.createNamedQuery("EL_READ_ALL_LOCALES");
        query.setHint(QueryHints.HINT_CACHEABLE,true);
        return (List<Locale>) query.getResultList();
    }


}




















