package com.pranveraapp.core.posttagxref.domain;

import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.domain.PostImpl;
import com.pranveraapp.core.tag.domain.Tag;
import com.pranveraapp.core.tag.domain.TagImpl;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by elion on 31/01/16.
 */
@Entity
@Inheritance
@Table(name = "el_post_tag_xref")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "elCoreElements")
public class PostTagXrefImpl implements PostTagXref{

    @Id
    @GeneratedValue(generator = "PostTagXrefId")
    @GenericGenerator(
            name = "PostTagXrefId",
            strategy="com.pranveraapp.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name = "segment_value", value="PostTagXrefImpl"),
            @Parameter(name = "entity_name", value="com.pranveraapp.core.posttagxref.domain.PostTagXrefImpl")
            }
    )
    @Column(name = "POST_TAG_XREF_ID")
    protected Long id;

    @ManyToOne(targetEntity = PostImpl.class)
    @JoinColumn(name = "POST_ID")
    protected Post post;


    @ManyToOne(targetEntity = TagImpl.class)
    @JoinColumn(name = "TAG_ID")
    protected Tag tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostTagXrefImpl)) return false;

        PostTagXrefImpl that = (PostTagXrefImpl) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getPost() != null ? !getPost().equals(that.getPost()) : that.getPost() != null) return false;
        return !(getTag() != null ? !getTag().equals(that.getTag()) : that.getTag() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        result = 31 * result + (getTag() != null ? getTag().hashCode() : 0);
        return result;
    }
}
