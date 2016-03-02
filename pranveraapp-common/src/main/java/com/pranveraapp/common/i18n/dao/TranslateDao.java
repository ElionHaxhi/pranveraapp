package com.pranveraapp.common.i18n.dao;

import com.pranveraapp.common.extension.ResultType;
import com.pranveraapp.common.extension.StandardCacheItem;
import com.pranveraapp.common.i18n.domain.Translate;
import com.pranveraapp.common.i18n.domain.TranslatedEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by elion on 25/02/16.
 */
public interface TranslateDao extends Serializable{

    public Translate readTranslateById(Long translateId);

    public Map<String, Object> getIdPropertyMetadata(TranslatedEntity entity);

    public String getEntityId(TranslatedEntity entityType, Object entity);

    public Translate readTranslation(TranslatedEntity entityType, String entityId, String fieldName, String localeCode, String localeCountryCode, ResultType stage);

    public Long countTranslationEntries(TranslatedEntity entityType, ResultType stage);

    public List<Translate> readAllTranslationEntries(TranslatedEntity entityType, ResultType stage);

    public List<StandardCacheItem> readConvertedTranslationEntries(TranslatedEntity entityType, ResultType stage);
}
