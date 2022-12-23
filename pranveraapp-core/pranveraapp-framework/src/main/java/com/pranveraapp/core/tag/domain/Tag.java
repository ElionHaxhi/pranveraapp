package com.pranveraapp.core.tag.domain;

import com.pranveraapp.core.posttagxref.domain.PostTagXref;

import java.io.Serializable;
import java.util.List;

/**
 * Created by elion on 31/01/16.
 */
public interface Tag extends Serializable {
    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public List<PostTagXref> getPostTagXrefList();

    public void setPostTagXrefList(List<PostTagXref> postTagXrefList);

}
