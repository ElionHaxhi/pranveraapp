package com.pranveraapp.core.posttagxref.domain;

import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.tag.domain.Tag;

import java.io.Serializable;

/**
 * Created by elion on 31/01/16.
 */
public interface PostTagXref extends Serializable {
    public Long getId();

    public void setId(Long id);


    public Post getPost();

    public void setPost(Post post);

    public Tag getTag();

    public void setTag(Tag tag);
}
