package com.pranveraapp.core.category.domain;

import com.pranveraapp.common.admin.domain.AdminMainEntity;
import com.pranveraapp.common.extensibility.jpa.copy.DirectCopyTransform;
import com.pranveraapp.common.extensibility.jpa.copy.DirectCopyTransformMember;
import com.pranveraapp.common.presentation.AdminPresentation;
import com.pranveraapp.common.presentation.AdminPresentationClass;
import com.pranveraapp.common.presentation.client.VisibilityEnum;
import com.pranveraapp.core.post.domain.Post;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elion on 28/01/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="elCoreElements")
public class CategoryImpl implements Category {

    @Id
    @GeneratedValue(generator = "CategoryId")
    @GenericGenerator(
            name = "CategoryId",
            strategy = "com.pranveraapp.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name = "segment_value", value="CategoryImpl"),
                    @Parameter(name = "entity_name",
                    value = "com.pranveraapp.core.category.domain.CategoryImpl")
            }
    )
    @Column(name = "CATEGORY_ID")
    protected Long id;

    @Column(name = "NAME", nullable = false)
    @Index(name="CATEGORY_NAME_INDEX",columnNames = {"NAME"})
    protected String name;

    @Column(name = "URL")
    @Index(name = "CATEGORY_URL_INDEX",columnNames = {"URL"})
    protected String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryImpl)) return false;

        CategoryImpl category = (CategoryImpl) o;

        if (!getId().equals(category.getId())) return false;
        if (!getName().equals(category.getName())) return false;
        return getUrl().equals(category.getUrl());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getUrl().hashCode();
        return result;
    }
}
