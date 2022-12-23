package com.pranveraapp.common.extensibility.jpa;

import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;


/**
 * Created by elion on 25/01/16.
 */
public class JPAPropertiesPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {

    protected Map<String, String> persistenceUnitProperties = new HashMap<String, String>();
    protected Map<String, String> overrideProperties = new HashMap<String, String>();

    @Value("${elPU.hibernate.hbm2ddl.auto}")
    protected String elPUHibernateHbm2ddlAuto;
    @Value("${elPU.hibernate.dialect}")
    protected String elPUHibernateDialect;
    @Value("${elPU.hibernate.show_sql}")
    protected String elPUHibernateShow_sql;
    @Value("${elPU.hibernate.cache.use_second_level_cache}")
    protected String elPUHibernateCacheUse_second_level_cache;
    @Value("${elPU.hibernate.cache.use_query_cache}")
    protected String elPUHibernateCacheUse_query_cache;
    @Value("${elPU.hibernate.hbm2ddl.import_files}")
    protected String elPUHibernateHbm2ddlImport_files;
    @Value("${elPU.hibernate.hbm2ddl.import_files_sql_extractor}")
    protected String elPUHibernateHbm2ddlImport_files_sql_extractor;



    @PostConstruct
    public void populatePresetProperties() {
        if (!elPUHibernateHbm2ddlAuto.startsWith("${")) persistenceUnitProperties.put("elPU.hibernate.hbm2ddl.auto", elPUHibernateHbm2ddlAuto);
        if (!elPUHibernateDialect.startsWith("${")) persistenceUnitProperties.put("elPU.hibernate.dialect", elPUHibernateDialect);
        if (!elPUHibernateShow_sql.startsWith("${")) persistenceUnitProperties.put("elPU.hibernate.show_sql", elPUHibernateShow_sql);
        if (!elPUHibernateCacheUse_second_level_cache.startsWith("${")) persistenceUnitProperties.put("elPU.hibernate.cache.use_second_level_cache", elPUHibernateCacheUse_second_level_cache);
        if (!elPUHibernateCacheUse_query_cache.startsWith("${")) persistenceUnitProperties.put("elPU.hibernate.cache.use_query_cache", elPUHibernateCacheUse_query_cache);
        if (!elPUHibernateHbm2ddlImport_files.startsWith("${")) persistenceUnitProperties.put("elPU.hibernate.hbm2ddl.import_files", elPUHibernateHbm2ddlImport_files);
        if (!elPUHibernateHbm2ddlImport_files_sql_extractor.startsWith("${")) persistenceUnitProperties.put("elPU.hibernate.hbm2ddl.import_files_sql_extractor", elPUHibernateHbm2ddlImport_files_sql_extractor);

       persistenceUnitProperties.putAll(overrideProperties);
    }

    @Override
    public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
        if (persistenceUnitProperties != null) {
            String puName = pui.getPersistenceUnitName() + ".";
            Set<String> keys = persistenceUnitProperties.keySet();
            Properties props = pui.getProperties();

            for (String key : keys) {
                if (key.startsWith(puName)){
                    String value = persistenceUnitProperties.get(key);
                    String newKey = key.substring(puName.length());
                    if ("null".equalsIgnoreCase(value)){
                        props.remove(newKey);
                    } else if (value != null && ! "".equals(value)) {
                        props.put(newKey, value);
                    }
                }
            }
            pui.setProperties(props);
        }
    }

    public void setPersistenceUnitProperties(Map<String, String> properties) {
        this.overrideProperties = properties;
    }

}
