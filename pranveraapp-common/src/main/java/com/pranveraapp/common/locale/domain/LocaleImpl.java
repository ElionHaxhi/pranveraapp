package com.pranveraapp.common.locale.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by elion on 02/02/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_locale")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "elCoreElements")
public class LocaleImpl implements Locale {

    @Id
    @Column(name = "LOCALE_CODE")
    protected String localeCode;

    @Column(name = "FRIENDLY_NAME")
    protected String friendlyName;

    @Column(name = "DEFAULT_FLAG")
    protected Boolean defaultFlag = false;


    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocaleImpl)) return false;

        LocaleImpl locale = (LocaleImpl) o;

        if (!getLocaleCode().equals(locale.getLocaleCode())) return false;
        if (getFriendlyName() != null ? !getFriendlyName().equals(locale.getFriendlyName()) : locale.getFriendlyName() != null)
            return false;
        return !(getDefaultFlag() != null ? !getDefaultFlag().equals(locale.getDefaultFlag()) : locale.getDefaultFlag() != null);

    }

    @Override
    public int hashCode() {
        int result = getLocaleCode().hashCode();
        result = 31 * result + (getFriendlyName() != null ? getFriendlyName().hashCode() : 0);
        result = 31 * result + (getDefaultFlag() != null ? getDefaultFlag().hashCode() : 0);
        return result;
    }
}
