package com.pranveraapp.common.locale.util;

import com.pranveraapp.common.locale.domain.Locale;

/**
 * Created by elion on 24/02/16.
 */
public final class LocaleUtil {

    public static String findLanguageCode(Locale locale) {
        if (locale != null && locale.getLocaleCode() != null && locale.getLocaleCode().indexOf("_") > 0) {
            int endIndex = locale.getLocaleCode().indexOf("_");
            char[] localeCodeChars = locale.getLocaleCode().toCharArray();
            StringBuffer sb = new StringBuffer();
            for(int i=0; i < endIndex; i++){
                sb.append(localeCodeChars[i]);
            }
            return sb.toString();
        }
        return null;
    }
}
