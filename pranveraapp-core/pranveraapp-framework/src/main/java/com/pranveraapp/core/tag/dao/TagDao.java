package com.pranveraapp.core.tag.dao;

import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.tag.domain.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * Created by elion on 10/02/16.
 */
public interface TagDao extends Serializable{
    public List<Tag> readAllTags(String all);
    public Tag readTagByURI(String uri);
    public Integer readTotalResultsByTag(Long tagId);
    public List<Post> readFilteredActivePostsByTag(Long tagId,SearchCriteria searchCriteria);
    public List<Tag> readFilteredActiveTagsByPost(Long postId);
}
