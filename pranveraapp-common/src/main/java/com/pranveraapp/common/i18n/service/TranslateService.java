package com.pranveraapp.common.i18n.service;

import net.sf.ehcache.Cache;

import java.util.Locale;

/**
 * Created by elion on 25/02/16.
 */
public interface TranslateService {

    public String getTranslatedValue(Object entity, String property, Locale locale);

    String getDefaultTranslateValue(Object entity, String property, Locale locale, String requestedDefaultValue);

    public Cache getCache();

}
