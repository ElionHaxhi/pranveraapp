package com.pranveraapp.core.post.domain;
import com.pranveraapp.common.admin.domain.AdminMainEntity;
import com.pranveraapp.common.i18n.service.DynamicTranslateProvider;
import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.author.domain.AuthorImpl;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.category.domain.CategoryImpl;
import com.pranveraapp.core.posttagxref.domain.PostTagXref;
import com.pranveraapp.core.posttagxref.domain.PostTagXrefImpl;
import com.pranveraapp.core.timetoread.domain.TimeToRead;
import com.pranveraapp.core.timetoread.domain.TimeToReadImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/* Author elion*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "elCoreElements")
public class PostImpl implements Post{

    private static final Log LOG = LogFactory.getLog(PostImpl.class);

    @Id
    @GeneratedValue(generator = "PostId")
    @GenericGenerator(
            name = "PostId",
            strategy = "com.pranveraapp.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name = "segment_value", value = "PostImpl"),
                    @Parameter(name = "entity_name", value =
                            "com.pranveraapp.core.post.domain.PostImpl")
            }
    )
    @Column(name = "POST_ID")
    protected Long id;

    @Column(name = "TITLE", nullable = false)
    protected String title;

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(name = "RENDEREDCONTENT", nullable = false)
    protected String renderedContent;

    @Column(name = "RENDEREDSUMMARY", nullable = false)
    @Type(type = "org.hibernate.type.StringClobType")
    protected String renderedSummary;

    @Column(name = "PUBLISH_AT",nullable = false)
    protected Date publishAt;

    @Column(name = "IMG_URL",nullable = false)
    protected String imgUrl;

    @Column(name = "URL",nullable = false)
    protected String url;

    @ManyToOne(targetEntity = CategoryImpl.class)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    @ManyToOne(targetEntity = AuthorImpl.class)
    @JoinColumn(name = "AUTHOR_ID")
    protected Author author;

    @ManyToOne(targetEntity = TimeToReadImpl.class)
    @JoinColumn(name = "TIME_TO_READ_ID")
    protected TimeToRead timeToRead;

    @OneToMany(targetEntity = PostTagXrefImpl.class, mappedBy = "post",
            cascade = {CascadeType.ALL})
    @Cascade(value = {
                    org.hibernate.annotations.CascadeType.ALL,
                    org.hibernate.annotations.CascadeType.DELETE_ORPHAN
            })
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "elCoreElements")
    @BatchSize(size = 50)
    protected List<PostTagXref> postTagXrefs = new ArrayList<PostTagXref>();




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        if(title == null)
        {
          return title;
        }
        return DynamicTranslateProvider.getValue(this, "title", title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRenderedContent() {
        if(renderedContent == null)
        {
           return renderedContent;
        }
        return DynamicTranslateProvider.getValue(this, "renderedContent", renderedContent);
    }

    public void setRenderedContent(String renderedContent) {
        this.renderedContent = renderedContent;
    }

    public String getRenderedSummary() {

        if(renderedSummary == null){
            return renderedSummary;
        }
        return DynamicTranslateProvider.getValue(this, "renderedSummary", renderedSummary);
    }

    public void setRenderedSummary(String renderedSummary) {
        this.renderedSummary = renderedSummary;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

     public TimeToRead getTimeToRead() {
        return timeToRead;
    }

    public void setTimeToRead(TimeToRead timeToRead) {
        this.timeToRead = timeToRead;
    }


    public List<PostTagXref> getPostTagXrefs() {
        return postTagXrefs;
    }

    public void setPostTagXrefs(List<PostTagXref> postTagXrefs) {
        this.postTagXrefs = postTagXrefs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostImpl)) return false;

        PostImpl post = (PostImpl) o;

        if (!getId().equals(post.getId())) return false;
        if (!getTitle().equals(post.getTitle())) return false;
        if (getRenderedContent() != null ? !getRenderedContent().equals(post.getRenderedContent()) : post.getRenderedContent() != null)
            return false;
        if (getRenderedSummary() != null ? !getRenderedSummary().equals(post.getRenderedSummary()) : post.getRenderedSummary() != null)
            return false;
        if (getPublishAt() != null ? !getPublishAt().equals(post.getPublishAt()) : post.getPublishAt() != null)
            return false;
        if (getImgUrl() != null ? !getImgUrl().equals(post.getImgUrl()) : post.getImgUrl() != null) return false;
        if (getUrl() != null ? !getUrl().equals(post.getUrl()) : post.getUrl() != null) return false;
        if (getCategory() != null ? !getCategory().equals(post.getCategory()) : post.getCategory() != null)
            return false;
        if (getAuthor() != null ? !getAuthor().equals(post.getAuthor()) : post.getAuthor() != null) return false;
        if (getTimeToRead() != null ? !getTimeToRead().equals(post.getTimeToRead()) : post.getTimeToRead() != null)
            return false;
        return !(getPostTagXrefs() != null ? !getPostTagXrefs().equals(post.getPostTagXrefs()) : post.getPostTagXrefs() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + (getRenderedContent() != null ? getRenderedContent().hashCode() : 0);
        result = 31 * result + (getRenderedSummary() != null ? getRenderedSummary().hashCode() : 0);
        result = 31 * result + (getPublishAt() != null ? getPublishAt().hashCode() : 0);
        result = 31 * result + (getImgUrl() != null ? getImgUrl().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getTimeToRead() != null ? getTimeToRead().hashCode() : 0);
        result = 31 * result + (getPostTagXrefs() != null ? getPostTagXrefs().hashCode() : 0);
        return result;
    }
}




























































	
