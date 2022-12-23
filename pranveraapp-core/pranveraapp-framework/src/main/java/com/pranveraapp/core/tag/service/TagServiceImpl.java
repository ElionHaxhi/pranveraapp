package com.pranveraapp.core.tag.service;

import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.tag.dao.TagDao;
import com.pranveraapp.core.tag.domain.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by elion on 10/02/16.
 */
@Service("elTagService")
public class TagServiceImpl implements TagService {

    @Resource(name = "elTagDao")
    protected TagDao tagDao;

    @Override
    public List<Tag> findAllTags(String all){
        return tagDao.readAllTags(all);
    }

    @Override
    public Tag findTagByURI(String uri){
        return tagDao.readTagByURI(uri);
    }

    @Override
    public List<Post> findFilteredActivePostsByTag(Long tagId,SearchCriteria searchCriteria){
            return tagDao.readFilteredActivePostsByTag(tagId, searchCriteria);
    }

    @Override
    public Integer findTotalResultsByTag(Long tagId){
        return tagDao.readTotalResultsByTag(tagId);
    }

    @Override
    public List<Tag> findFilteredActiveTagsByPost(Long postId){
        return tagDao.readFilteredActiveTagsByPost(postId);
    }
}
