package com.pranveraapp.common.web;

import com.pranveraapp.common.locale.domain.Locale;
import com.pranveraapp.common.locale.service.LocaleService;
import org.apache.commons.logging.Log;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by elion on 27/02/16.
 */
public class PranveraAppCookieLocaleResolver extends CookieLocaleResolver {



    @Resource(name = "elLocaleService")
    private LocaleService localeService;

    @Override
    protected java.util.Locale determineDefaultLocale(HttpServletRequest request) {
        java.util.Locale defaultLocale = getDefaultLocale();
        if (defaultLocale == null) {
            Locale defaultPranveraAppLocale = localeService.findDefaultLocale();
            if (defaultPranveraAppLocale == null) {
                return super.determineDefaultLocale(request);
            } else {
                return PranveraAppRequestContext.convertLocaleToJavaLocale(defaultPranveraAppLocale);
            }
        }
        return defaultLocale;
    }
}
