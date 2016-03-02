package com.pranveraapp.core.tag.service;

import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.tag.domain.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * Created by elion on 10/02/16.
 */
public interface TagService extends Serializable {
    public List<Tag> findAllTags(String all);
    public Tag findTagByURI(String uri);
    public Integer findTotalResultsByTag(Long tagId);
    public List<Post> findFilteredActivePostsByTag(Long tagId,SearchCriteria searchCriteria);
    public List<Tag> findFilteredActiveTagsByPost(Long postId);
}
