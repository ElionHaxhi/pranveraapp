package com.pranveraapp.common.locale.service;

import com.pranveraapp.common.locale.domain.Locale;

import java.util.List;

/**
 * Created by elion on 24/02/16.
 */
public interface LocaleService {

    public Locale findLocaleByCode(String localeCode);

    public Locale findDefaultLocale();

    public List<Locale> findAllLocales();
}
