package com.pranveraapp.common.i18n.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by elion on 18/02/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_translate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "elTranslateElements")
public class TranslateImpl implements Translate {

    @Id
    @GeneratedValue(generator = "TranslateId")
    @GenericGenerator(
            name = "TranslateId",
            strategy = "com.pranveraapp.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value",value="TranslateImpl"),
                    @org.hibernate.annotations.Parameter(name = "entity_name", value ="com.pranveraapp.core.translate.domain.TranslateImpl")
            }
    )
    @Column(name = "TRANSLATE_ID")
    protected Long id;

    @Column(name = "ENTITY_TYPE")
    protected String entityType;

    @Column(name = "ENTITY_ID")
    protected String entityId;

    @Column(name = "FIELD_NAME")
    protected String fieldName;

    @Column(name = "LOCALE_CODE")
    protected String localeCode;

    @Column(name = "TRANSLATED_VALUE", length = Integer.MAX_VALUE -1)
    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    protected String translatedValue;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String filedName) {
        this.fieldName = filedName;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getTranslatedValue() {
        return translatedValue;
    }

    public void setTranslatedValue(String translatedValue) {
        this.translatedValue = translatedValue;
    }

    // custom getters and setters
    @Override
    public TranslatedEntity getEntityType() {
        return TranslatedEntity.getInstanceFromFriendlyType(entityType);
    }

    @Override
    public void setEntityType(TranslatedEntity entityType) {
        this.entityType = entityType.getFriendlyType();
    }
}
