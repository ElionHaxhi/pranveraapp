package com.pranveraapp.common.web;

import com.pranveraapp.common.locale.domain.Locale;
import com.pranveraapp.common.locale.service.LocaleService;
import com.pranveraapp.common.util.ELRequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by elion on 25/02/16.
 */
@Component("elLocaleResolver")
public class PranveraAppLocaleResolverImpl implements PranveraAppLocaleResolver {

    private final Log LOG = LogFactory.getLog(PranveraAppLocaleResolverImpl.class);

    /**
     * Parameter/Attribute name for the current language
     */
    public static String LOCALE_VAR = "elLocale";

    /**
     * Parameter/Attribute name for the current language
     */
    public static String LOCALE_CODE_PARAM = "elLocaleCode";

    /**
     * Attribute indicating that the LOCALE was pulled from session.   Other filters may want to
     * behave differently if this is the case.
     */
    public static String LOCALE_PULLED_FROM_SESSION = "elLocalePulledFromSession";

    @Resource(name = "elLocaleService")
    private LocaleService localeService;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return resolveLocale(new ServletWebRequest(request));
    }

    @Override
    public Locale resolveLocale(WebRequest request) {
        Locale locale = null;

        // First check for request attribute
        locale = (Locale) request.getAttribute(LOCALE_VAR, WebRequest.SCOPE_REQUEST);

        // Second, check for a request parameter
        if (locale == null && ELRequestUtils.getURLorHeaderParameter(request, LOCALE_CODE_PARAM) != null) {
            String localeCode = ELRequestUtils.getURLorHeaderParameter(request, LOCALE_CODE_PARAM);
            locale = localeService.findLocaleByCode(localeCode);
            if (LOG.isTraceEnabled()) {
                LOG.trace("Attempt to find locale by param " + localeCode + " resulted in " + locale);
            }
        }

        // Third, check the session
        if (locale == null && ELRequestUtils.isOKtoUseSession(request)) {
            locale = (Locale) request.getAttribute(LOCALE_VAR, WebRequest.SCOPE_GLOBAL_SESSION);
            if (LOG.isTraceEnabled()) {
                LOG.trace("Attempt to find locale from session resulted in " + locale);
            }

            if (locale != null) {
                request.setAttribute(LOCALE_PULLED_FROM_SESSION, Boolean.TRUE, WebRequest.SCOPE_REQUEST);
            }

        }

        // Finally, use the default
        if (locale == null) {
            locale = localeService.findDefaultLocale();
            if (LOG.isTraceEnabled()) {
                LOG.trace("Locale set to default locale " + locale);
            }
        }

        // Set the default locale to override Spring's cookie resolver
        request.setAttribute(LOCALE_VAR, locale, WebRequest.SCOPE_REQUEST);
        java.util.Locale javaLocale = PranveraAppRequestContext.convertLocaleToJavaLocale(locale);
        request.setAttribute(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME, javaLocale, WebRequest.SCOPE_REQUEST);

        if (ELRequestUtils.isOKtoUseSession(request)) {
            request.setAttribute(LOCALE_VAR, locale, WebRequest.SCOPE_GLOBAL_SESSION);
        }
        return locale;
    }
}
