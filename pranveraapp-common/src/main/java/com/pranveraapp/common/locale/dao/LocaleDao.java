package com.pranveraapp.common.locale.dao;

import com.pranveraapp.common.locale.domain.Locale;

import java.io.Serializable;
import java.util.List;

/**
 * Created by elion on 24/02/16.
 */
public interface LocaleDao{

    /**
     * @return The locale for the passed in code
     */
    public Locale findLocaleByCode(String localeCode);

    /**
     * Returns the page template with the passed in id.
     *
     * @return The default locale
     */
    public Locale findDefaultLocale();

    /**
     * Returns all supported locales.
     *
     * @return
     */
    public List<Locale> findAllLocales();
}
