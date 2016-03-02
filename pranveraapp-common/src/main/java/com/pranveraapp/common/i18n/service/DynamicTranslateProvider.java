package com.pranveraapp.common.i18n.service;

import com.pranveraapp.common.web.PranveraAppRequestContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * Created by elion on 25/02/16.
 */
public class DynamicTranslateProvider {

    public static String getValue(Object obj, String field, final String defaultValue) {
        String valueToReturn = defaultValue;

        if (TranslateConsiderationContext.hasTranslation()) {
            TranslateService translateService = TranslateConsiderationContext.getTranslateService();
            Locale locale = PranveraAppRequestContext.getPranveraAppRequestContext().getJavaLocale();
            String translatedValue = translateService.getTranslatedValue(obj, field, locale);

            if (StringUtils.isNotBlank(translatedValue)) {
                valueToReturn = translatedValue;
            } else {
                valueToReturn = translateService.getDefaultTranslateValue(obj, field, locale, defaultValue);
            }
        }

        return valueToReturn;
    }
}
