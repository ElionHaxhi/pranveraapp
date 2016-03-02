package com.pranveraapp.core.lokale.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elion on 18/02/16.
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED)
@Table(name = "el_locale")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "elCoreElements")
public class LokaleImpl implements  Lokale{


    @Id
    @Column(name = "LOCALE_CODE")
    protected String id;

    @Column(name = "DEFAULT_FLAG")
    protected boolean defaultFlag;

    @Column(name = "FRIENDLY_NAME")
    protected String friendlyName;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LokaleImpl)) return false;

        LokaleImpl lokale = (LokaleImpl) o;

        if (isDefaultFlag() != lokale.isDefaultFlag()) return false;
        if (!getId().equals(lokale.getId())) return false;
        return !(getFriendlyName() != null ? !getFriendlyName().equals(lokale.getFriendlyName()) : lokale.getFriendlyName() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (isDefaultFlag() ? 1 : 0);
        result = 31 * result + (getFriendlyName() != null ? getFriendlyName().hashCode() : 0);
        return result;
    }
}
