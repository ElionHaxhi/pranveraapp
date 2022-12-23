package com.pranveraapp.common.i18n.domain;

import java.io.Serializable;

/**
 * Created by elion on 18/02/16.
 */
public interface Translate extends Serializable{

    public Long getId();

    public void setId(Long id);

    public TranslatedEntity getEntityType();

    public void setEntityType(TranslatedEntity entityType);

    public String getEntityId();

    public void setEntityId(String entityId);

    public String getFieldName();

    public void setFieldName(String fieldName);

    public String getLocaleCode();

    public void setLocaleCode(String localeCode);

    public String getTranslatedValue();

    public void setTranslatedValue(String translatedValue);

}
