package com.pranveraapp.common.i18n.domain;

import com.pranveraapp.common.PranveraAppEnumerationType;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by elion on 24/02/16.
 */
public class TranslatedEntity implements Serializable, PranveraAppEnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, TranslatedEntity> TYPES = new LinkedHashMap<String, TranslatedEntity>();

    public static final TranslatedEntity POST = new TranslatedEntity("com.pranveraapp.core.post.domain.Post", "Post");

    public static TranslatedEntity getInstance(final String type) {
        return TYPES.get(type);
    }

    public static TranslatedEntity getInstanceFromFriendlyType(final String friendlyType) {
        for (Map.Entry<String, TranslatedEntity> entry : TYPES.entrySet()) {
            if (entry.getValue().getFriendlyType().equals(friendlyType)) {
                return entry.getValue();
            }
        }

        return null;
    }

    private String type;
    private String friendlyType;

    public TranslatedEntity() {
        //do nothing
    }

    public TranslatedEntity(final String type, final String friendlyType) {
        this.friendlyType = friendlyType;
        setType(type);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getFriendlyType() {
        return friendlyType;
    }

    public static Map<String, TranslatedEntity> getTypes() {
        return TYPES;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        TranslatedEntity other = (TranslatedEntity) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
