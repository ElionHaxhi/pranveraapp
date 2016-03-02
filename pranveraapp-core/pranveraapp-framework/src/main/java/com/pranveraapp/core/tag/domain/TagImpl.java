package com.pranveraapp.core.tag.domain;

import com.pranveraapp.core.posttagxref.domain.PostTagXref;
import com.pranveraapp.core.posttagxref.domain.PostTagXrefImpl;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by elion on 31/01/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "elCoreElements")
public class TagImpl implements Tag {

    @Id
    @GeneratedValue(generator = "TagId")
    @GenericGenerator(
            name = "TagId",
            strategy = "com.pranveraapp.common.persistence.IdOverrideTableGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "segment_value", value="TagImpl"),
            @org.hibernate.annotations.Parameter(name = "segment_value", value="com.pranveraapp.core.tag.domain.TagImpl")
            }
    )
    @Column(name = "TAG_ID")
    protected Long id;

    @Column(name = "NAME",nullable = false )
    protected String name;

    @Column(name = "TAG_URL" ,nullable = false)
    protected String tagUrl;

    @OneToMany(mappedBy = "tag", targetEntity = PostTagXrefImpl.class,cascade = {CascadeType.ALL})
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region  = "elCoreElements")
    protected List<PostTagXref> postTagXrefList = new ArrayList<PostTagXref>();

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

    public List<PostTagXref> getPostTagXrefList() {
        return postTagXrefList;
    }

    public void setPostTagXrefList(List<PostTagXref> postTagXrefList) {
        this.postTagXrefList = postTagXrefList;
    }

    public String getTagUrl() {
        return tagUrl;
    }

    public void setTagUrl(String tagUrl) {
        this.tagUrl = tagUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagImpl)) return false;

        TagImpl tag = (TagImpl) o;

        if (!getId().equals(tag.getId())) return false;
        if (!getName().equals(tag.getName())) return false;
        return !(getPostTagXrefList() != null ? !getPostTagXrefList().equals(tag.getPostTagXrefList()) : tag.getPostTagXrefList() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getPostTagXrefList() != null ? getPostTagXrefList().hashCode() : 0);
        return result;
    }
}
