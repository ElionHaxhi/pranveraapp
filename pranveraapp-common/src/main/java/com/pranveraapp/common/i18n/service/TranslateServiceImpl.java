package com.pranveraapp.common.i18n.service;

import com.pranveraapp.common.cache.CacheStatType;
import com.pranveraapp.common.cache.StatisticsService;
import com.pranveraapp.common.locale.service.LocaleService;
import com.pranveraapp.common.locale.util.LocaleUtil;
import com.pranveraapp.common.extension.ItemStatus;
import com.pranveraapp.common.extension.ResultType;
import com.pranveraapp.common.extension.StandardCacheItem;
import com.pranveraapp.common.i18n.dao.TranslateDao;
import com.pranveraapp.common.i18n.domain.Translate;
import com.pranveraapp.common.i18n.domain.TranslatedEntity;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by elion on 25/02/16.
 */
@Service("elTranslateService")
public class TranslateServiceImpl implements TranslateService {

    @Resource(name = "elTranslateDao")
    TranslateDao translateDao;

    protected Cache cache;

    @Resource(name = "elStatisticsService")
    protected StatisticsService statisticsService;


    @Resource(name = "elLocaleService")
    protected LocaleService localeService;

    @Value("${returnBlankTranslationForNotDefaultLocale:false}")
    protected boolean returnBlankTranslationForNotDefaultLocale;

    //@Resource(name = "elTranslationExceptionProperties")
    //protected List<String> translationExceptionProperties = new ArrayList<String>();





    @Override
    public String getTranslatedValue(Object entity, String property, Locale locale) {

        TranslatedEntity entityType = getEntityType(entity);
        String entityId = translateDao.getEntityId(entityType, entity);

        String localeCode = locale.getLanguage();
        String localeCountryCode = localeCode;

        if (StringUtils.isNotBlank(locale.getCountry())) {
            localeCountryCode += "_" + locale.getCountry();
        }

        return getOverrideTranslatedValue(property, entityType, entityId, localeCode, localeCountryCode);

    }

    protected String getOverrideTranslatedValue(String property, TranslatedEntity entityType,
                                                String entityId, String localeCode, String localeCountryCode) {
        String specificPropertyKey = property + "_" + localeCountryCode;
        String generalPropertyKey = property + "_" + localeCode;
        String cacheKey = getCacheKey(ResultType.STANDARD, entityType);

        Element cacheResult = getCache().get(cacheKey);
        Element result = null;
        String response = null;

        if (cacheResult == null) {
            statisticsService.addCacheStat(CacheStatType.TRANSLATION_CACHE_HIT_RATE.toString(), false);
            if (translateDao.countTranslationEntries(entityType, ResultType.STANDARD_CACHE) < getThresholdForFullCache()) {
                Map<String, Map<String, StandardCacheItem>> propertyTranslationMap = new HashMap<String, Map<String, StandardCacheItem>>();
                List<StandardCacheItem> convertedList = translateDao.readConvertedTranslationEntries(entityType, ResultType.STANDARD_CACHE);
                if (!CollectionUtils.isEmpty(convertedList)) {
                    for (StandardCacheItem standardCache : convertedList) {
                        Translate translation = (Translate) standardCache.getCacheItem();
                        String key = translation.getFieldName() + "_" + translation.getLocaleCode();
                        if (!propertyTranslationMap.containsKey(key)) {
                            propertyTranslationMap.put(key, new HashMap<String, StandardCacheItem>());
                        }
                        propertyTranslationMap.get(key).put(translation.getEntityId(), standardCache);
                    }
                }
                Element newElement = new Element(cacheKey, propertyTranslationMap);
                getCache().put(newElement);
                result = newElement;
            } else {
                Translate translation = translateDao.readTranslation(entityType, entityId, property, localeCode, localeCountryCode, ResultType.IGNORE);
                if (translation != null) {
                    response = translation.getTranslatedValue();
                }
            }

        } else {

            result = cacheResult;
            statisticsService.addCacheStat(CacheStatType.TRANSLATION_CACHE_HIT_RATE.toString(), true);

        }

        if (result != null) {
            Map<String, Map<String, StandardCacheItem>> propertyTranslationMap =
                    (Map<String, Map<String, StandardCacheItem>>) result.getObjectValue();

            boolean specificTranslationDeleted = false;
            boolean generalTranslationDeleted = false;

            // Check For a Specific Standard Site Match (language and country)
            StandardCacheItem specificTranslation = lookupTranslationFromMap(specificPropertyKey, propertyTranslationMap,
                    entityId);
            if (specificTranslation != null) {
                if (ItemStatus.DELETED.equals(specificTranslation.getItemStatus())) {
                    specificTranslationDeleted = true;
                } else {
                    response = ((Translate) specificTranslation.getCacheItem()).getTranslatedValue();
                    return replaceEmptyWithNullResponse(response);
                }
            }

            // Check For a General Match (language and country)
            StandardCacheItem generalTranslation = lookupTranslationFromMap(generalPropertyKey, propertyTranslationMap,
                    entityId);
            if (generalTranslation != null) {
                if (ItemStatus.DELETED.equals(generalTranslation.getItemStatus())) {
                    generalTranslationDeleted = true;
                    if (specificTranslationDeleted) {
                        return null;
                    }
                }

                if (specificTranslationDeleted) {
                    response = ((Translate) generalTranslation.getCacheItem()).getTranslatedValue();
                    return replaceEmptyWithNullResponse(response);
                }
            }

            // Check for a Template Match
            if (specificTranslationDeleted) {
                // only check general properties since we explicitly deleted specific properties at standard (site) level
                specificPropertyKey = generalPropertyKey;
            } else if (generalTranslationDeleted) {
                // only check specific properties since we explicitly deleted general properties at standard (site) level
                generalPropertyKey = specificPropertyKey;
            }

           // response = getTemplateTranslatedValue(cacheKey, property, entityType, entityId, localeCode,
           //         localeCountryCode, specificPropertyKey, generalPropertyKey);
        }

        return replaceEmptyWithNullResponse(response);

    }

     protected StandardCacheItem lookupTranslationFromMap(String key,
            Map<String, Map<String, StandardCacheItem>> propertyTranslationMap, String entityId) {

        StandardCacheItem cacheItem = null;
        if (propertyTranslationMap.containsKey(key)) {
            Map<String, StandardCacheItem> byEntity = propertyTranslationMap.get(key);
            cacheItem = byEntity.get(entityId);
        }
        return cacheItem;
    }

    protected int getThresholdForFullCache() {
        /**if (PranveraAppRequestContext.getBroadleafRequestContext().isProductionSandBox()) {
            return thresholdForFullCache;
        } else {
            // don't cache when not in a SandBox
            return -1;
        }*/
        return -1;
    }

    public String getDefaultTranslateValue(Object entity, String property, Locale locale,
            String requestedDefaultValue) {

        if (returnBlankTranslationForNotDefaultLocale && !localeMatchesDefaultLocale(locale) && !propertyInDefaultLocaleExceptionList(entity, property)) {
            return "";
        }

        return requestedDefaultValue;
    }

    protected boolean propertyInDefaultLocaleExceptionList(Object entity, String property) {
        TranslatedEntity entityType = getEntityType(entity);
       /** if (entityType != null && entityType.getFriendlyType() != null) {
            for (String exceptionProperty : translationExceptionProperties) {
                if (property.matches(exceptionProperty)) {
                    return true;
                }
            }
        }*/
        return false;
    }

    protected boolean localeMatchesDefaultLocale(Locale locale) {
        String defaultLanguage = LocaleUtil.findLanguageCode(localeService.findDefaultLocale());

        if (defaultLanguage != null && locale != null) {
            return defaultLanguage.equals(locale.getLanguage());
        }
        return false;
    }

    protected String replaceEmptyWithNullResponse(String response) {
        if (!StringUtils.isEmpty(response)) {
            return response;
        }
        return null;
    }

    protected TranslatedEntity getEntityType(Class<?> entityClass) {
        for (Map.Entry<String, TranslatedEntity> entry : TranslatedEntity.getTypes().entrySet()) {
            try {
                Class<?> clazz = Class.forName(entry.getKey());
                if (clazz.isAssignableFrom(entityClass)) {
                    return entry.getValue();
                }
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("TranslatedEntity type was not set to a known class", e);
            }
        }
        throw new IllegalArgumentException(entityClass.getName() + " is not a known translatable class");
    }

    protected TranslatedEntity getEntityType(Object entity) {
        return getEntityType(entity.getClass());
    }

    @Override
    public Cache getCache() {
        if (cache == null) {
            cache = CacheManager.getInstance().getCache("elTranslateElements");
        }
        return cache;
    }

    protected String getCacheKey(ResultType resultType, TranslatedEntity entityType) {
        String cacheKey = StringUtils.join(new String[]{entityType.getFriendlyType()}, "|");

        return cacheKey;
    }
}
