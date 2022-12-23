package com.pranveraapp.common.locale.service;

import com.pranveraapp.common.locale.dao.LocaleDao;
import com.pranveraapp.common.locale.domain.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by elion on 24/02/16.
 */
@Service("elLocaleService")
public class LocaleServiceImpl implements LocaleService {
    private static final Log LOG = LogFactory.getLog(LocaleServiceImpl.class);

    @Resource(name = "elLocaleDao")
    protected LocaleDao localeDao;

    @Override
    public Locale findLocaleByCode(String localeCode){
        return localeDao.findLocaleByCode(localeCode);
    }

    @Override
    public Locale findDefaultLocale(){
        return localeDao.findDefaultLocale();
    }

    @Override
    public List<Locale> findAllLocales(){
        return localeDao.findAllLocales();
    }
}
