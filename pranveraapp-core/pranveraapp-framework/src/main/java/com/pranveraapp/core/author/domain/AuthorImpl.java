package com.pranveraapp.core.author.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by elion on 31/01/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_author")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "elCoreElements")
public class AuthorImpl implements Author{

    @Id
    @GeneratedValue(generator = "AuthorId")
    @GenericGenerator(
            name = "AuthorId",
            strategy = "com.pranveraapp.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name="segment_value", value ="AuthorImpl"),
                    @org.hibernate.annotations.Parameter(name="entity_name", value ="com.pranveraapp.core.author.domain.AuthorImpl")
             }
    )
    @Column(name = "AUTHOR_ID")
    protected Long id;

    @Column(name = "FULL_NAME", nullable = false)
    protected String name;

    @Column(name = "IMG_URL", nullable = false)
    protected String imgUrl;

    @Column(name = "TWITTER_URL")
    protected String twitterUrl;

    @Column(name = "AUTHOR_URL", nullable = false)
    protected String authorUrl;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorImpl)) return false;

        AuthorImpl author = (AuthorImpl) o;

        if (!getId().equals(author.getId())) return false;
        if (!getName().equals(author.getName())) return false;
        if (!getImgUrl().equals(author.getImgUrl())) return false;
        return !(getTwitterUrl() != null ? !getTwitterUrl().equals(author.getTwitterUrl()) : author.getTwitterUrl() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getImgUrl().hashCode();
        result = 31 * result + (getTwitterUrl() != null ? getTwitterUrl().hashCode() : 0);
        return result;
    }
}
