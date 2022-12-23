package com.pranveraapp.common.web;

import com.pranveraapp.common.locale.domain.Locale;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by elion on 25/02/16.
 */
public interface PranveraAppLocaleResolver {


    @Deprecated
    public Locale resolveLocale(HttpServletRequest request);

    public Locale resolveLocale(WebRequest request);
}
